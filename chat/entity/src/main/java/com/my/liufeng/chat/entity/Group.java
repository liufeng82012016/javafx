package com.my.liufeng.chat.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 群组表
 */
@TableName(value = "tb_group")
public class Group {
    @TableId
    private Integer id;
    /**
     * 创建人
     */
    private Integer owner;
    /**
     * 群昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 解散时间
     */
    private Date destroyTime;

}
