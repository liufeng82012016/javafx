package com.my.liufeng.chat.netty.codec;

import com.my.liufeng.chat.constants.RpcConstants;
import com.my.liufeng.chat.exception.InnerException;
import com.my.liufeng.chat.model.RpcMessage;
import com.my.liufeng.chat.utils.SerialUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码器，负责序列化请求
 * todo MessageToByteEncoder 加了泛型会报错 netty不支持byte[]，只支持ByteBuf或FileRegion，编码器增加ByteArrayEncoder可以解决
 * -----------------------------------------------------------
 * | length | type | separator |           body           |
 * -----------------------------------------------------------
 * | int    | byte |  byte[]   |           byte[]         |
 * -----------------------------------------------------------
 * | 4byte  | 1byte |  nbyte  |           byte[]         |
 * -----------------------------------------------------------
 */
public class CustomEncoder extends MessageToByteEncoder {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object obj, ByteBuf out) throws Exception {
        if (!(obj instanceof RpcMessage)) {
            throw new InnerException("UnSupport type: " + obj.getClass().getSimpleName());
        }
        RpcMessage rpcMessage = (RpcMessage) obj;
        byte type = rpcMessage.getType();
        byte[] msgBytes = SerialUtil.serialize(obj);
        // 计算消息长度，单位为byte
        int length = msgBytes.length + RpcConstants.MSG_EXTRA_LENGTH;
        if (length > RpcConstants.MAX_LENGTH) {
            throw new InnerException("RpcMessage maxBodyLength is " + RpcConstants.MAX_LENGTH);
        }
        // 按照既定顺序写入数据
        out.writeBytes(RpcConstants.SEPARATOR);
        out.writeInt(length);
        out.writeByte(type);
        out.writeBytes(msgBytes);
        out.writeBytes(RpcConstants.SEPARATOR);
    }


}