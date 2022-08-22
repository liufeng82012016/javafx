package com.my.liufeng.chat.event;

/**
 * chat 客户端事件处理器
 *
 * @author liufeng
 */
public interface ChatEventHandler {

    /**
     * 处理事件
     *
     * @param event 事件
     */
    void handle(AbstractChatEvent event);
}
