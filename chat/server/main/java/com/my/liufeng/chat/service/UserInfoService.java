package com.my.liufeng.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.my.liufeng.chat.entity.UserInfo;
import com.my.liufeng.chat.vo.LoginVO;

import java.util.List;
import java.util.Set;

/**
 * @author liufeng
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 登录
     */
    UserInfo login(LoginVO loginVO);

    /**
     * 注册
     */
    UserInfo register(LoginVO loginVO);

    List<UserInfo> select(Set<Integer> friendIdList);
}
