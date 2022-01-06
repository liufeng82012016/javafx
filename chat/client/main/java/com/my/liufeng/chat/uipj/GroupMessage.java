package com.my.liufeng.chat.uipj;
import com.my.liufeng.ui.model.Message;
import com.my.liufeng.ui.util.RandomUtil;

public class GroupMessage implements Message {
    @Override
    public String getNickname() {
        return "null昵称";
    }

    @Override
    public String getMessage() {
        return RandomUtil.randomStr(RandomUtil.randomInt(120));
    }

    @Override
    public String getAvatar() {
        return "img/bird.png";
    }

    @Override
    public boolean mine() {
        return (int) (Math.random() * 10) < 5;
    }

    @Override
    public boolean group() {
        return true;
    }
}
