package com.my.liufeng.chat.provider;

import com.my.liufeng.chat.entity.Heart;
import com.my.liufeng.chat.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 模拟服务提供者
 */
@Component
public class HelloService {
    @Autowired
    private UserInfoService userInfoService;


    public Heart ping() {
//        UserInfo userInfo = new UserInfo();
//        userInfo.setId(1);
//        userInfo.setNickname("one");
//        userInfoService.saveOrUpdate(userInfo);
        return new Heart();
    }
}
