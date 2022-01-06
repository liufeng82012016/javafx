package com.my.liufeng.chat.context;

import com.my.liufeng.chat.constants.StaticConstants;
import com.my.liufeng.chat.entity.Message;
import com.my.liufeng.rpc.model.RpcResponse;
import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

public class UserManager {
    private static ConcurrentHashMap<Integer, Channel> userChannelMap = new ConcurrentHashMap<>();

    /**
     * 添加用户到分组
     */
    public static void addUser(Integer userId, Channel channel) {
        userChannelMap.put(userId, channel);
    }

    /**
     * 移除用户
     */
    public static void removeUser(Integer userId) {
        userChannelMap.remove(userId);
    }

    /**
     * 获取用户channel
     */
    public static Channel getChannel(Integer userId) {
        return userChannelMap.get(userId);
    }

    /**
     * 推送消息
     */
    public static void sendMsg(Message message) {
        // 单机，内存获取
        Channel channel = getChannel(message.getUserId());
        if (channel == null) {
            // 不在线
            return;
        }
        RpcResponse<Message> rpcResponse = new RpcResponse<>();
        rpcResponse.setData(message);
        rpcResponse.setType(StaticConstants.RPC_TYPE_MSG);
        channel.writeAndFlush(rpcResponse);
    }
}
