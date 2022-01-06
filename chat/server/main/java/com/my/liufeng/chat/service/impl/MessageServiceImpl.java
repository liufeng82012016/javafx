package com.my.liufeng.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.liufeng.chat.constants.Constants;
import com.my.liufeng.chat.context.UserManager;
import com.my.liufeng.chat.entity.Message;
import com.my.liufeng.chat.mapper.MessageMapper;
import com.my.liufeng.chat.service.FriendService;
import com.my.liufeng.chat.service.GroupMemberService;
import com.my.liufeng.chat.service.GroupService;
import com.my.liufeng.chat.service.MessageService;
import com.my.liufeng.chat.util.ContextUtil;
import com.my.liufeng.chat.vo.MessageQueryVO;
import com.my.liufeng.chat.vo.NewMessageVO;
import com.my.liufeng.util.CollectionUtil;
import com.my.liufeng.util.Conditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    private GroupService groupService;
    @Autowired
    private FriendService friendService;
    @Autowired
    private GroupMemberService groupMemberService;

    @Override
    public NewMessageVO messageList(MessageQueryVO messageQueryVO) {
        Integer userId = ContextUtil.getUserId();

        NewMessageVO newMessageVO = new NewMessageVO();
        // 查询最新列表
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                .gt("id", messageQueryVO.getMaxMsgId())
                .last(" limit 1");
        // 组装好友列表和群组列表
        List<Message> messageList = list(queryWrapper);
        if (CollectionUtil.isNotEmpty(messageList)) {
            Set<Integer> groupIdList = new HashSet<>();
            Set<Integer> friendIdList = new HashSet<>();
            messageList.forEach(message -> {
                if (message.getGroupId() != null) {
                    groupIdList.add(message.getGroupId());
                }
                friendIdList.add(message.getFrom());
            });
            newMessageVO.setFriendList(friendService.select(friendIdList));
            newMessageVO.setGroupList(groupService.select(groupIdList));
        }
        return newMessageVO;
    }

    @Override
    public void updateMaxMsgId(Long maxMsgId) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", ContextUtil.getUserId())
                .le("id", maxMsgId);
        remove(queryWrapper);
    }

    @Override
    public void receiveMsg(Message message) {
        message.setId(null);
        Set<Integer> memberIds;
        // 分组查询成员，每人写入1条
        if (message.getGroupId() != null) {
            memberIds = groupMemberService.queryMembers(message.getGroupId());
            Conditions.expectTrue(memberIds.contains(ContextUtil.getUserId()), Constants.CHANNEL_NULL);
        } else {
            memberIds = Collections.singleton(message.getUserId());
        }
        for (Integer userId : memberIds) {
            if (ContextUtil.getUserId().equals(userId)) {
                continue;
            }
            message.setUserId(userId);
            // 存储并转发
            save(message);
            // todo 消息转发
            UserManager.sendMsg(message);
        }
    }
}
