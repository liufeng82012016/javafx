package com.my.liufeng.chat.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 消息表
 */
@TableName(value = "tb_message")
public class Message {
    @TableId
    private Long id;
    /**
     * 发送人
     */
    private Integer fromUserId;
    /**
     * 接受人
     */
    private Integer userId;
    /**
     * 发送群组
     */
    private Integer groupId;
    /**
     * 消息内容
     */
    private String msg;
    /**
     * 消息类型
     */
    private Byte msgType;
    /**
     * 发送时间
     */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Byte getMsgType() {
        return msgType;
    }

    public void setMsgType(Byte msgType) {
        this.msgType = msgType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
