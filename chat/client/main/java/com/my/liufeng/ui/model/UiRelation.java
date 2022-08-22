package com.my.liufeng.ui.model;

/**
 * 好友实体类
 *
 * @author liufeng
 */
public interface UiRelation {

    /**
     * 头像
     */
    String getIcon();

    /**
     * 最新一条消息内容
     */
    String getContent();

    /**
     * 最新一条消息时间
     */
    String getTime();

    /**
     * 好友名
     */
    String getTitle();

    /**
     * 是否群组
     */
    boolean group();

    /**
     * 好友id
     */
    Integer getId();

    /**
     * 最新消息时间戳
     */
    Long getTimeMills();
}
