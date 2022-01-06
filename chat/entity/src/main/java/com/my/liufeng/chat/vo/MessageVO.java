package com.my.liufeng.chat.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 消息表
 */
@TableName(value = "tb_message")
public class MessageVO {
    @TableId
    private Long id;
    /**
     * 是否由我发送
     */
    private boolean mine;
    /**
     * 接收人
     */
    private Integer to;
    /**
     * 发送群组
     */
    private boolean group;
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
    /**
     * 对方昵称
     */
    private String nickname;
    /**
     * 对方头像
     */
    private String avatar;
}
