package com.my.liufeng.chat.provider;

import com.my.liufeng.chat.entity.Heart;
import com.my.liufeng.chat.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 模拟服务提供者
 *
 * @author liufeng
 */
@Component
public class HelloService {
    @Autowired
    private UserInfoService userInfoService;


    public Heart ping() {
        return new Heart();
    }
}
