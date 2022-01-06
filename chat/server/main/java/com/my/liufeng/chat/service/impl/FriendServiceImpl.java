package com.my.liufeng.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.liufeng.chat.entity.Friend;
import com.my.liufeng.chat.mapper.FriendMapper;
import com.my.liufeng.chat.service.FriendService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements FriendService {

    @Override
    public List<Friend> select(Set<Integer> friendIdList) {
        QueryWrapper<Friend> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", friendIdList);
        return list(queryWrapper);
    }
}
