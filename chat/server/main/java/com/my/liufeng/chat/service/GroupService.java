package com.my.liufeng.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.my.liufeng.chat.entity.Group;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface GroupService extends IService<Group> {
    /**
     * 根据组id查询组列表
     */
    List<Group> select(Collection<Integer> groupIdList);


}
