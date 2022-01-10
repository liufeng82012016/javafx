package com.my.liufeng.chat.manager;

import com.my.liufeng.chat.ClientApplication;
import com.my.liufeng.chat.api.RemoteChatService;
import com.my.liufeng.chat.uipj.DefaultFriend;
import com.my.liufeng.chat.uipj.Mine;
import com.my.liufeng.chat.uipj.RealMessage;
import com.my.liufeng.chat.util.ThreadPoolUtil;
import com.my.liufeng.chat.vo.NewMessageVO;
import com.my.liufeng.rpc.context.MethodProxyRepository;
import com.my.liufeng.rpc.utils.CollectionUtil;
import com.my.liufeng.ui.model.Message;
import com.my.liufeng.ui.model.Relation;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 管理客户端数据，提供crud方法
 */
public class DataManager {
    private static InternalLogger log = InternalLoggerFactory.getInstance(ClientApplication.class);

    /**
     * 会话信息
     */
    private static ObservableList<Relation> sessions = FXCollections.observableList(new LinkedList<>());
    /**
     * 会话消息
     */
    private static Map<Relation, ObservableList<Message>> messageListMap = new HashMap<>();
    /**
     * 好友列表
     */
    private static ObservableList<Relation> friend;
    /**
     * 我的信息
     */
    public static Mine myInfo;
    /**
     * 当前选中的联系人
     */
    private static DefaultFriend selectedDefaultFriend;
    /**
     * 当前页面id -- 待定义
     */
    public static Integer viewId;

    public static ObservableList<Relation> getSessions() {
        return sessions;
    }

    public static ObservableList<Relation> getFriend() {
        return friend;
    }

    public static Mine getMyInfo() {
        return myInfo;
    }

    public static void setMyInfo(Mine myInfo) {
        DataManager.myInfo = myInfo;
    }

    public static DefaultFriend getSelectedSession() {
        return selectedDefaultFriend;
    }

    public static void setSelectedSession(Relation relation) {
        DataManager.selectedDefaultFriend = (DefaultFriend) relation;
    }

    public static Integer getViewId() {
        return viewId;
    }

    public static void setViewId(Integer viewId) {
        DataManager.viewId = viewId;
    }

    public static ObservableList<Message> getMessageList(Relation defaultFriend) {
        return messageListMap.computeIfAbsent(defaultFriend, k -> FXCollections.observableList(new LinkedList<>()));
    }

    public static void addSessions(Collection<Relation> defaultFriend) {
        sessions.addAll(defaultFriend);
    }

    public static void addSession(Relation defaultFriend) {
        sessions.addAll(defaultFriend);
    }

    public static void addMessages(Relation defaultFriend, Collection<Message> messages) {
        getMessageList(defaultFriend).addAll(messages);
    }

    public static void addMessage(Relation relation, Message message) {
        getMessageList(relation).add(message);
        DefaultFriend defaultFriend = (DefaultFriend) relation;
        Long mills = defaultFriend.getMills();
        if (mills == null || mills < message.time()) {
            defaultFriend.setTime(message.time());
            defaultFriend.setContent(message.getMessage());
        }
    }

    public static void sortSession() {
        sessions.sort((a, b) -> b.getTimeMills().compareTo(a.getTimeMills()));
    }


    private static AtomicBoolean refreshSession = new AtomicBoolean(false);

    /**
     * 刷新会话列表
     */
    public static void refreshSession() {
        ThreadPoolUtil.submit(() -> {
            boolean refreshWork = refreshSession.compareAndSet(false, true);
            if (!refreshWork) {
                // 如果正在刷新 停止下一次操作
                return;
            }
            try {
                RemoteChatService chatService = MethodProxyRepository.getProxy(RemoteChatService.class);
                // 获取新消息
                NewMessageVO newMessageVO = chatService.messageList(null);
                if (CollectionUtil.isEmpty(newMessageVO.getFriendList())) {
                    return;
                }
                Map<Integer, Relation> friendMap = new HashMap<>();
                Map<Integer, Relation> groupMap = new HashMap<>();
                DataManager.getSessions().forEach(relation -> {
                    if (relation.group()) {
                        groupMap.put(relation.getId(), relation);
                    } else {
                        friendMap.put(relation.getId(), relation);
                    }
                });
                // 处理会话列表
                newMessageVO.getFriendList().forEach(friendVO -> {
                    DefaultFriend defaultFriend = new DefaultFriend(friendVO);
                    DataManager.addSession(defaultFriend);
                    if (friendMap.get(defaultFriend.getId()) != null) {
                        // 新增会话
                        DataManager.addSession(defaultFriend);
                    }
                    friendMap.put(defaultFriend.getId(), defaultFriend);
                });
                newMessageVO.getGroupList().forEach(group -> {
                    DefaultFriend defaultFriend = new DefaultFriend(group);
                    DataManager.addSession(defaultFriend);
                    if (groupMap.get(defaultFriend.getId()) != null) {
                        // 新增会话
                        DataManager.addSession(defaultFriend);
                    }
                    groupMap.put(defaultFriend.getId(), defaultFriend);
                });
                // 处理新消息
                List<com.my.liufeng.chat.entity.Message> messageList = newMessageVO.getMessageList();
                messageList.forEach(message -> {
                    Integer groupId = message.getGroupId();
                    Relation from = friendMap.get(message.getFromUserId());
                    Relation relation = groupId > 0 ? groupMap.get(groupId) : from;
                    if (relation == null || from == null) {
                        log.warn("message from lost. groupId=[{}] from=[{}]", groupId, message.getFromUserId());
                        return;
                    }
                    RealMessage realMessage = new RealMessage(message);
                    realMessage.setAvatar(from.getIcon());
                    realMessage.setNickname(from.getTitle());
                    DataManager.addMessage(relation, realMessage);
                });
                DataManager.sortSession();
            } finally {
                refreshSession.set(false);
            }
        });
    }
}
