package com.my.liufeng.chat.provider;

import com.my.liufeng.chat.service.UserInfoService;
import com.my.liufeng.chat.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class UserService {
    @Autowired
    private UserInfoService userInfoService;

    public UserService() {
        System.out.println("UserService: init");
    }

    /**
     * 登录账号
     */
    public boolean login(@Valid LoginVO loginVO) {
        userInfoService.login(loginVO);
        return true;
    }

    public boolean register(@Valid LoginVO loginVO) {
        userInfoService.register(loginVO);
        return true;
    }

    public String ping() {
        return "hello";
    }
}
