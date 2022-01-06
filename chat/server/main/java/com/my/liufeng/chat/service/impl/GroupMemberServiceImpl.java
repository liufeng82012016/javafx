package com.my.liufeng.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.liufeng.chat.entity.GroupMember;
import com.my.liufeng.chat.mapper.GroupMemberMapper;
import com.my.liufeng.chat.service.GroupMemberService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


@Service
public class GroupMemberServiceImpl extends ServiceImpl<GroupMemberMapper, GroupMember> implements GroupMemberService {

    /**
     * 根据组id查询成员
     */
    @Override
    public Set<Integer> queryMembers(Integer groupId) {
        QueryWrapper<GroupMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", groupId);
        return list(queryWrapper).stream().map(GroupMember::getGroupId).collect(Collectors.toSet());
    }


}
