package com.my.liufeng.chat.uipj;

import com.my.liufeng.chat.entity.UserInfo;
import com.my.liufeng.ui.model.Relation;

/**
 * 我的信息
 *
 * @author liufeng
 */
public class Mine implements Relation {
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
        return getInstance().userInfo.getNickname();
    }

    @Override
    public boolean group() {
        return false;
    }

    @Override
    public Integer getId() {
        return getInstance().userInfo.getId();
    }

    @Override
    public Long getTimeMills() {
        return null;
    }

    public static void setUserInfo(UserInfo userInfo) {
        getInstance().userInfo = userInfo;
    }

    private Mine() {
        System.out.println("我的信息初始化");
    }

    public static Mine getInstance() {
        return MineInstance.mine;
    }

    private static class MineInstance {
        private static final Mine mine = new Mine();
    }
}
