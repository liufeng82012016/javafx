package com.my.liufeng.ui.model.mock;

import com.my.liufeng.ui.model.Relation;
import com.my.liufeng.ui.util.RandomUtil;

import java.util.List;

public class DefaultFriend implements Relation {
    private String title;

    @Override
    public List<Relation> search(List<Relation> list, String keyword) {
        return list;
    }

    @Override
    public String getIcon() {
        return "img/bird.png";
    }

    @Override
    public String getContent() {
        return "content";
    }

    @Override
    public String getTime() {
        return "2021/12/20";
    }

    @Override
    public String getTitle() {
        if (title == null) {
            title = RandomUtil.randomStr(8);
        }
        return title;
    }
}
