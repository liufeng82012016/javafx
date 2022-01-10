package com.my.liufeng.chat.provider;

import com.my.liufeng.chat.entity.UserInfo;
import com.my.liufeng.chat.service.UserInfoService;
import com.my.liufeng.chat.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class UserService extends HeartService {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 登录账号
     */
    public UserInfo login(@Valid LoginVO loginVO) {
        return userInfoService.login(loginVO);
    }

    public UserInfo register(@Valid LoginVO loginVO) {
        return userInfoService.register(loginVO);
    }

}
