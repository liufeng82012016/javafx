package com.my.liufeng.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.liufeng.chat.entity.Friend;
import com.my.liufeng.chat.mapper.FriendMapper;
import com.my.liufeng.chat.service.FriendService;
import com.my.liufeng.chat.util.ContextUtil;
import com.my.liufeng.chat.util.Wrappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements FriendService {

    @Override
    public List<Friend> select(Set<Integer> friendIdList) {
        Integer userId = ContextUtil.getUserId();
        List<Friend> list = list(Wrappers.create(Friend.class)
                .eq("user_id", userId)
                .in("friend_id", friendIdList));
        list.addAll(list(Wrappers.create(Friend.class)
                .eq("friend_id", userId)
                .in("user_id", friendIdList)));
        return list;
    }
}
