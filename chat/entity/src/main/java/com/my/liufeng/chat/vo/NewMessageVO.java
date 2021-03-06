package com.my.liufeng.chat.vo;

import com.my.liufeng.chat.entity.Group;
import com.my.liufeng.chat.entity.Message;

import java.util.List;

public class NewMessageVO {
    private List<FriendVO> friendList;
    private List<Group> groupList;
    private List<Message> messageList;

    public List<FriendVO> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<FriendVO> friendList) {
        this.friendList = friendList;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }
}
