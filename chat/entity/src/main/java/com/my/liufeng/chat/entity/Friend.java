package com.my.liufeng.chat.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 好友列表
 */
@TableName(value = "tb_friend")
public class Friend {
    @TableId
    private Integer id;
    /**
     * userId
     */
    private Integer uid;
    /**
     * 好友id
     */
    private Integer friendId;
    /**
     * 当前用户给好友加的昵称
     */
    private String nickname1;
    /**
     * 好友给当前用户加的昵称
     */
    private String nickname2;


}
