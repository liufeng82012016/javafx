package com.my.liufeng.chat.vo;

import javax.validation.constraints.NotNull;

/**
 * 用户登录参数
 */
public class LoginVO {
    @NotNull
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @NotNull
    public void setPassword(String password) {
        this.password = password;
    }
}
