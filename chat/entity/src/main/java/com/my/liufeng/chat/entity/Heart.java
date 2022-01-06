package com.my.liufeng.chat.entity;

public class Heart {
    private Long time;
    private String pong;

    public Heart() {
        this.time = System.currentTimeMillis();
        this.pong = "pong";
    }

    public Long getTime() {
        return time;
    }

    public String getPong() {
        return pong;
    }

    @Override
    public String toString() {
        return "Heart{" +
                "time=" + time +
                ", pong='" + pong + '\'' +
                '}';
    }
}
