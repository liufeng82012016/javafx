package com.my.liufeng.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.my.liufeng.chat.entity.GroupMember;

import java.util.Set;

public interface GroupMemberService extends IService<GroupMember> {
    /**
     * 根据组id查询成员
     */
    Set<Integer> queryMembers(Integer groupId);
}
