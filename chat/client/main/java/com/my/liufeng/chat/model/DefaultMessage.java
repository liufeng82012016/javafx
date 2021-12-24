package com.my.liufeng.chat.model;

import com.my.liufeng.ui.model.Message;

public class DefaultMessage implements Message {
    @Override
    public String getNickname() {
        return "null昵称";
    }

    @Override
    public String getMessage() {
        return "嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄嚣张狂妄";
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
        return (int) (Math.random() * 10) < 5;
    }
}
