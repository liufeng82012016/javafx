package com.my.liufeng.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.my.liufeng.chat.entity.UserInfo;
import com.my.liufeng.chat.vo.LoginVO;

public interface UserInfoService extends IService<UserInfo> {
    void login(LoginVO loginVO);

    void register(LoginVO loginVO);
}
