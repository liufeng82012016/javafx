package com.my.liufeng.chat.uipj;

import com.my.liufeng.ui.model.Message;

public class RealMessage implements Message {
    private com.my.liufeng.chat.entity.Message message;
    private String nickname;
    private String avatar;


    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public String getMessage() {
        return message.getMsg();
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    @Override
    public boolean mine() {
        return message.getFromUserId() == Mine.getInstance().getId().intValue();
    }

    @Override
    public boolean group() {
        return message.getGroupId() > 0;
    }

    @Override
    public long time() {
        return message.getCreateTime().getTime();
    }


    public RealMessage(com.my.liufeng.chat.entity.Message message) {
        this.message = message;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
