package com.my.liufeng.chat.model;

import com.my.liufeng.chat.enums.RpcMessageType;

/**
 * 心跳包
 */
public class RpcHeartMsg extends RpcMessage{
    public RpcHeartMsg() {
        this.type = RpcMessageType.TYPE_REQUEST.getType();
    }


}
