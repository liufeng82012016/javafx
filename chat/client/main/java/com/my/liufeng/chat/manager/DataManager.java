package com.my.liufeng.chat.manager;

import com.my.liufeng.chat.uipj.GroupMessage;
import com.my.liufeng.chat.uipj.Message1;
import com.my.liufeng.chat.uipj.Mine;
import com.my.liufeng.chat.uipj.DefaultFriend;
import com.my.liufeng.ui.model.Message;
import com.my.liufeng.ui.model.Relation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 管理客户端数据，提供crud方法
 */
public class DataManager {
    /**
     * 会话信息
     */
    public static ObservableList<Relation> sessions;
    /**
     * 会话消息
     */
    public static Map<Relation, ObservableList<Message>> messageList;
    /**
     * 好友列表
     */
    public static ObservableList<Relation> friend;
    /**
     * 我的信息
     */
    public static Mine myInfo;
    /**
     * 当前选中的联系人
     */
    public static Relation selectedRelation;
    /**
     * 当前页面id -- 待定义
     */
    public static Integer viewId;

    public static ObservableList<Relation> getSessions() {
        return sessions;
    }

    public static void setSessions(ObservableList<Relation> sessions) {
        DataManager.sessions = sessions;
    }

    public static Map<Relation, ObservableList<Message>> getMessageList() {
        return messageList;
    }

    public static void setMessageList(Map<Relation, ObservableList<Message>> messageList) {
        DataManager.messageList = messageList;
    }

    public static ObservableList<Relation> getFriend() {
        return friend;
    }

    public static void setFriend(ObservableList<Relation> friend) {
        DataManager.friend = friend;
    }

    public static Mine getMyInfo() {
        return myInfo;
    }

    public static void setMyInfo(Mine myInfo) {
        DataManager.myInfo = myInfo;
    }

    static {
        LinkedList<Relation> relations = new LinkedList<>();
        for (int i = 0; i < 16; i++) {
            relations.add(new DefaultFriend());
        }
        sessions = FXCollections.observableList(relations);
        sessions.add(new DefaultFriend());
        messageList = new HashMap<>(sessions.size());
        for (Relation relation : sessions) {
            LinkedList<Message> messages = new LinkedList<>();
            for (int i = 0; i < 15; i++) {
                messages.add(relation.group() ? new GroupMessage() : new Message1());
            }
            messageList.put(relation, FXCollections.observableList(messages));
        }
    }

    public static Relation getSelectedRelation() {
        return selectedRelation;
    }

    public static void setSelectedRelation(Relation selectedRelation) {
        DataManager.selectedRelation = selectedRelation;
    }

    public static Integer getViewId() {
        return viewId;
    }

    public static void setViewId(Integer viewId) {
        DataManager.viewId = viewId;
    }
}
