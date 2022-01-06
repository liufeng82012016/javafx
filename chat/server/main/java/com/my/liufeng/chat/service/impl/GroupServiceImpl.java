package com.my.liufeng.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.liufeng.chat.entity.Group;
import com.my.liufeng.chat.mapper.GroupMapper;
import com.my.liufeng.chat.service.GroupService;
import com.my.liufeng.chat.util.QueryUtil;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;


@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {


    @Override
    public List<Group> select(Collection<Integer> groupIdList) {
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", groupIdList);
        return list(queryWrapper);
    }
}
