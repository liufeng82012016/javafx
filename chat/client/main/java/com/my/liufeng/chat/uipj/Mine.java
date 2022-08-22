package com.my.liufeng.chat.uipj;

import com.my.liufeng.chat.entity.UserInfo;
import com.my.liufeng.chat.event.AbstractChatEvent;
import com.my.liufeng.chat.event.ChatEventHandler;
import com.my.liufeng.chat.event.EventManager;
import com.my.liufeng.chat.event.LoginEvent;
import com.my.liufeng.ui.model.UiRelation;

/**
 * 我的信息
 *
 * @author liufeng
 */
public class Mine implements UiRelation, ChatEventHandler {
    private UserInfo userInfo;

    @Override
    public String getIcon() {
        return "img/bird.png";
    }

    @Override
    public String getContent() {
        return null;
    }

    @Override
    public String getTime() {
        return "";
    }

    @Override
    public String getTitle() {
        UserInfo userInfo = getInstance().userInfo;
        if (userInfo == null) {
            throw new RuntimeException("未登陆");
        }
        return userInfo.getNickname();
    }

    @Override
    public boolean group() {
        return false;
    }

    @Override
    public Integer getId() {
        UserInfo userInfo = getInstance().userInfo;
        if (userInfo == null) {
            throw new RuntimeException("未登陆");
        }
        return userInfo.getId();
    }

    @Override
    public Long getTimeMills() {
        return null;
    }


    private Mine() {
        System.out.println("我的信息初始化");
        EventManager.registerHandler(LoginEvent.class, this);
    }

    public static Mine getInstance() {
        return MineInstance.mine;
    }

    @Override
    public void handle(AbstractChatEvent event) {
        getInstance().userInfo = (UserInfo) event.getData();
    }

    private static class MineInstance {
        /**
         * 我的信息
         */
        private static final Mine mine = new Mine();
    }
}
