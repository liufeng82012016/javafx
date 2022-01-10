package com.my.liufeng.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.liufeng.chat.constants.Constants;
import com.my.liufeng.chat.context.UserManager;
import com.my.liufeng.chat.entity.UserInfo;
import com.my.liufeng.chat.mapper.UserInfoMapper;
import com.my.liufeng.chat.service.UserInfoService;
import com.my.liufeng.chat.util.ContextUtil;
import com.my.liufeng.chat.util.SecretUtil;
import com.my.liufeng.chat.util.Wrappers;
import com.my.liufeng.chat.vo.LoginVO;
import com.my.liufeng.util.Conditions;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Override
    public UserInfo login(LoginVO loginVO) {
        assetUnLogin();
        // 查询数据
        UserInfo userInfo = getOne(Wrappers.create(UserInfo.class)
                .eq("username", loginVO.getUsername()));

        // 密码校验
        Conditions.expectNonNull(userInfo, "用户名不存在");
        Conditions.expectTrue(userInfo.getPwd().equals(SecretUtil.encryptPwd(loginVO.getPassword())), "密码错误");

        bindUserId(userInfo.getId());
        return userInfo;
    }

    @Override
    public UserInfo register(LoginVO loginVO) {
        assetUnLogin();
        // 校验是否已注册
        UserInfo userInfo = getOne(Wrappers.create(UserInfo.class)
                .eq("username", loginVO.getUsername()));
        Conditions.expectNull(userInfo, "该用户名已注册");
        // 写入数据
        userInfo = new UserInfo();
        userInfo.setUsername(loginVO.getUsername());
        userInfo.setPwd(SecretUtil.encryptPwd(loginVO.getPassword()));
        save(userInfo);
        bindUserId(userInfo.getId());
        return userInfo;
    }

    @Override
    public List<UserInfo> select(Set<Integer> friendIdList) {
        return list(Wrappers.create(UserInfo.class)
                .in("id", friendIdList));
    }

    private void assetUnLogin() {
        // 判断是否重复登录
        Channel channel = ContextUtil.getChannel();
        Object userIdObj = channel.attr(AttributeKey.valueOf(Constants.ATTR_USER_ID)).get();
        Conditions.expectNull(userIdObj, Constants.CHANNEL_NULL);
    }

    private void bindUserId(Integer userId) {
        Channel channel = ContextUtil.getChannel();
        // channel绑定userId
        channel.attr(AttributeKey.valueOf(Constants.ATTR_USER_ID)).set(userId);
        UserManager.addUser(userId, channel);
    }
}
