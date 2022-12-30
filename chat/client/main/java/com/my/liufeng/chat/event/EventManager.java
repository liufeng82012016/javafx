package com.my.liufeng.chat.event;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 自定义事件处理器
 *
 * @author liufeng
 */
public class EventManager {

    private static Map<Class<? extends AbstractChatEvent>, List<ChatEventHandler>> registerEventHandlerMap;


    /**
     * 注册handler
     *
     * @param eventClass   事件类型
     * @param eventHandler 事件处理器
     */
    public synchronized static void registerHandler(Class<? extends AbstractChatEvent> eventClass, ChatEventHandler eventHandler) {
        if (registerEventHandlerMap == null) {
            registerEventHandlerMap = new HashMap<>();
        }
        List<ChatEventHandler> chatEventHandlers = registerEventHandlerMap.computeIfAbsent(eventClass, k -> new LinkedList<>());
        chatEventHandlers.add(eventHandler);
    }


    public static void publishEvent(AbstractChatEvent event) {
        if (registerEventHandlerMap == null) {
            return;
        }
        List<ChatEventHandler> chatEventHandlers = registerEventHandlerMap.get(event.getClass());
        for (ChatEventHandler chatEventHandler : chatEventHandlers) {
            // todo 判断是否关联UI事件，UI事件需要JavaFX线程处理
            chatEventHandler.handle(event);
        }
    }
}
