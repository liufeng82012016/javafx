package com.my.liufeng.chat.model;

import com.my.liufeng.ui.model.Relation;

public class Mine implements Relation {

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
        return "流风";
    }

    private Mine() {
        System.out.println("我的信息初始化");
    }

    public static Mine getInstance() {
        return MineInstance.mine;
    }

    private static class MineInstance {
        private static Mine mine = new Mine();
    }
}
