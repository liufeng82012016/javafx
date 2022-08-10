package com.my.liufeng.chat.enums;

/**
 * 用于接口全县控制
 *
 * @author liufeng
 */

public enum Permission {
    /**
     * 免登录访问
     */
    PUBLIC(),
    /**
     * 登录访问
     */
    LOGIN(),
    /**
     * 授权访问
     */
    AUTH(),
    /**
     * 超级管理员访问
     */
    SUPER_MANAGER(),
    /**
     * 无权访问
     */
    NO_PERMISSION();
}
