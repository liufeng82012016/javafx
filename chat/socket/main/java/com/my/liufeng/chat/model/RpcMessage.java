package com.my.liufeng.chat.model;

/**
 * rpc消息，包含心跳，请求，响应
 */
public abstract class RpcMessage {
    public byte type;

    public byte getType() {
        return type;
    }
}
