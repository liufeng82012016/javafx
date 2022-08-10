package com.my.liufeng.chat.context;

import com.my.liufeng.chat.entity.UserInfo;

import java.nio.channels.Channel;

/**
 * 用户上下文
 *
 * @author liufeng
 */
public class UserContext {
    /**
     * tcp链接对应的netty channel
     */
    private Channel channel;

    /**
     * 用户信息
     */
    private UserInfo userInfo;


}
