package com.my.liufeng.chat.model;

import com.my.liufeng.ui.model.UiMessage;
import com.my.liufeng.ui.model.UiRelation;

import java.util.List;

public class MessageObject {
    private List<UiMessage> messageList;
    private UiRelation relation;

    public List<UiMessage> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<UiMessage> messageList) {
        this.messageList = messageList;
    }

    public UiRelation getRelation() {
        return relation;
    }

    public void setRelation(UiRelation relation) {
        this.relation = relation;
    }
}
