package com.my.liufeng.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.my.liufeng.chat.entity.Friend;

import java.util.List;
import java.util.Set;

public interface FriendService extends IService<Friend> {
    List<Friend> select(Set<Integer> friendIdList);

    /**
     * 添加好友
     *
     * @param id 好友id
     */
    void add(Integer id);
}
