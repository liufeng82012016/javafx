package com.my.liufeng.chat.provider;

/**
 * @author liufeng
 * 提供心跳服务
 */
public abstract class HeartService {
    public HeartService() {
        System.out.println(this.getClass().getSimpleName() + ": init");
    }

    public String ping() {
        return "hello";
    }

}
