package com.my.liufeng.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.my.liufeng.chat.entity.UserInfo;
import com.my.liufeng.chat.vo.LoginVO;

import java.util.List;
import java.util.Set;

public interface UserInfoService extends IService<UserInfo> {
    UserInfo login(LoginVO loginVO);

    UserInfo register(LoginVO loginVO);

    List<UserInfo> select(Set<Integer> friendIdList);
}
