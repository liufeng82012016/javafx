package com.my.liufeng.chat.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 用户会话表
 * 每个用户一条，userId作为主键
 */
@TableName(value = "tb_session")
public class Session {
    @TableId
    private Integer userId;
    /**
     * 接受到的最大一条信息的id
     */
    private Long maxMsgId;
}
