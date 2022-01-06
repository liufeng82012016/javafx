package com.my.liufeng.chat.vo;

public class MessageQueryVO {
    /**
     * 最大消息id
     */
    private Integer maxMsgId;
    /**
     * 客户端最后一次收到消息的时间
     */
    private Long lastMsgTime;

    public Integer getMaxMsgId() {
        return maxMsgId;
    }

    public void setMaxMsgId(Integer maxMsgId) {
        this.maxMsgId = maxMsgId;
    }

    public Long getLastMsgTime() {
        return lastMsgTime;
    }

    public void setLastMsgTime(Long lastMsgTime) {
        this.lastMsgTime = lastMsgTime;
    }
}
