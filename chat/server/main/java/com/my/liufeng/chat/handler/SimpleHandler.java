package com.my.liufeng.chat.handler;

import com.my.liufeng.chat.annotation.RequirePermission;
import com.my.liufeng.chat.constants.Constants;
import com.my.liufeng.chat.enums.Permission;
import com.my.liufeng.chat.util.ContextUtil;
import com.my.liufeng.rpc.model.RpcRequest;
import com.my.liufeng.rpc.model.RpcResponse;
import com.my.liufeng.rpc.utils.SerialUtil;
import com.my.liufeng.util.Conditions;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;

import java.lang.reflect.Method;

/**
 * 登录注册校验
 */
public class SimpleHandler extends ChannelInboundHandlerAdapter {
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        // 解析请求参数
        RpcRequest rpcRequest;
        if (msg instanceof RpcRequest) {
            rpcRequest = (RpcRequest) msg;
        } else {
            rpcRequest = SerialUtil.deserialize(String.valueOf(msg), RpcRequest.class);
        }
        long startTime = System.currentTimeMillis();
        System.out.println(String.format("receive:%s \n time:%s", rpcRequest.toString(), startTime));
        // 初始化返回值
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setRequestId(rpcRequest.getRequestId());

        try {
            // 根据参数的className获取对应的实例
            Class<?> clazz = Class.forName(rpcRequest.getServiceClass());
            Object o = ContextUtil.getBean(clazz);
            Conditions.expectNonNull(o, "instance not found");
            // 根据方法名和参数类型，获取对应的方法
            Method method = clazz.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
            // 统一做权限判定
            validPermission(method, ctx);

            // 设置身份（注意，这里设置在线程变量内，如果invoke方法使用线程池跑，需要在线程池方法去set）
            ContextUtil.setChannel(ctx.channel());

            Object data = method.invoke(o, rpcRequest.getParams());
            rpcResponse.setData(data);
        } catch (ClassNotFoundException var13) {
            rpcResponse.setCode(1);
            rpcResponse.setData("class " + rpcRequest.getServiceClass() + " not found");

            var13.printStackTrace();
        } catch (Exception var14) {
            var14.printStackTrace();
            rpcResponse.setMsg(var14.getMessage());
            rpcResponse.setCode(1);

            var14.printStackTrace();
        } finally {
            ContextUtil.removeChannel();
            ctx.channel().writeAndFlush(rpcResponse);
            System.out.println(String.format("receive:%s \n response:%s time:%s", rpcRequest.toString(),
                    rpcResponse.toString(), (System.currentTimeMillis() - startTime)));
        }

    }

    /**
     * 校验用户权限
     *
     * @param method 方法
     * @param ctx    上下文
     */
    private void validPermission(Method method, ChannelHandlerContext ctx) {
        RequirePermission requestPermission = method.getAnnotation(RequirePermission.class);
        if (requestPermission == null || Permission.PUBLIC == requestPermission.permission()) {
            return;
        }
        if (Permission.LOGIN == requestPermission.permission()
                && ctx.channel().attr(AttributeKey.valueOf(Constants.ATTR_USER_ID)) != null) {
            return;
        }
        // todo 其他权限校验
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel connect");
    }


}
