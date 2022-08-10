package com.my.liufeng.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.liufeng.chat.constants.Constants;
import com.my.liufeng.chat.context.UserManager;
import com.my.liufeng.chat.entity.Friend;
import com.my.liufeng.chat.entity.Group;
import com.my.liufeng.chat.entity.Message;
import com.my.liufeng.chat.entity.UserInfo;
import com.my.liufeng.chat.mapper.MessageMapper;
import com.my.liufeng.chat.service.*;
import com.my.liufeng.chat.util.ContextUtil;
import com.my.liufeng.chat.vo.FriendVO;
import com.my.liufeng.chat.vo.MessageQueryVO;
import com.my.liufeng.chat.vo.NewMessageVO;
import com.my.liufeng.util.CollectionUtil;
import com.my.liufeng.util.Conditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    private GroupService groupService;
    @Autowired
    private FriendService friendService;
    @Autowired
    private GroupMemberService groupMemberService;
    @Autowired
    private UserInfoService userInfoService;

    @Override
    public NewMessageVO messageList(MessageQueryVO messageQueryVO) {
        Integer userId = ContextUtil.getUserId();

        NewMessageVO newMessageVO = new NewMessageVO();
        // 查询最新列表
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        Integer maxMessageId = messageQueryVO != null ? messageQueryVO.getMaxMsgId() : null;
        queryWrapper.eq("user_id", userId)
                .gt(maxMessageId != null, "id", maxMessageId)
                .last(" limit 100");
        // 组装好友列表和群组列表
        List<Message> messageList = list(queryWrapper);
        if (CollectionUtil.isEmpty(messageList)) {
            System.out.println("user[{" + userId + "}]消息为空");
            return newMessageVO;
        }
        Set<Integer> groupIdList = new HashSet<>();
        Set<Integer> friendIdList = new HashSet<>();
        messageList.forEach(message -> {
            if (message.getGroupId() != null) {
                groupIdList.add(message.getGroupId());
            }
            friendIdList.add(message.getFromUserId());
        });
        List<Friend> friends = friendService.select(friendIdList);
        Map<Integer, String> idAndNicknameMap = new HashMap<>(friends.size());
        friends.forEach(friend -> {
            if (userId.equals(friend.getUserId())) {
                idAndNicknameMap.put(friend.getFriendId(), friend.getNickname1());
            } else {
                idAndNicknameMap.put(friend.getUserId(), friend.getNickname2());
            }
        });
        // 查询好友列表
        List<UserInfo> userInfos = userInfoService.select(friendIdList);
        List<FriendVO> friendVOS = userInfos.stream().map(userInfo -> {
            FriendVO friendVO = new FriendVO();
            friendVO.setAvatar(userInfo.getAvatar());
            friendVO.setNickname(idAndNicknameMap.get(userInfo.getId()));
            friendVO.setId(userInfo.getId());
            return friendVO;
        }).collect(Collectors.toList());
        newMessageVO.setFriendList(friendVOS);
        List<Group> groupList = CollectionUtil.isEmpty(groupIdList) ? Collections.emptyList() : groupService.select(groupIdList);
        newMessageVO.setGroupList(groupList);
        newMessageVO.setMessageList(messageList);
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
