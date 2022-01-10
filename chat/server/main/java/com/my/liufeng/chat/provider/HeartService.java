package com.my.liufeng.chat.provider;

public abstract class HeartService {
    public HeartService() {
        System.out.println(this.getClass().getSimpleName() + ": init");
    }

    public String ping() {
        return "hello";
    }

}
