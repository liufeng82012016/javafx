package com.my.liufeng.chat.event;

/**
 * chat 客户端事件
 *
 * @author liufeng
 */
public abstract class AbstractChatEvent {
    public Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
