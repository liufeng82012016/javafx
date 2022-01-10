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
    private Integer userId;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public String getNickname1() {
        return nickname1;
    }

    public void setNickname1(String nickname1) {
        this.nickname1 = nickname1;
    }

    public String getNickname2() {
        return nickname2;
    }

    public void setNickname2(String nickname2) {
        this.nickname2 = nickname2;
    }
}
