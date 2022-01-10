package com.my.liufeng.ui.model;

public interface Message {
    /**
     * 获取昵称
     */
    String getNickname();

    /**
     * 获取消息内容
     */
    String getMessage();

    /**
     * 获取头像
     */
    String getAvatar();

    /**
     * 是否我发出的消息
     */
    boolean mine();

    /**
     * 是否在群里
     */
    boolean group();

    long time();
}
