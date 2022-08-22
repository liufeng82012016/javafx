package com.my.liufeng.chat.uipj;

import com.my.liufeng.chat.entity.Group;
import com.my.liufeng.chat.vo.FriendVO;
import com.my.liufeng.ui.model.UiRelation;
import com.my.liufeng.util.DateUtil;

/**
 * 好友列表
 * @author liufeng
 */
public class DefaultFriend implements UiRelation {
    private FriendVO friend;
    private Group group;

    private Long time;
    private String content;

    @Override
    public String getIcon() {
        return group != null ? group.getNickname() : friend.getNickname();
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getTime() {
        return time == null ? null : DateUtil.formatYmd(time);
    }

    @Override
    public String getTitle() {
        return group != null ? group.getNickname() : friend.getNickname();
    }

    @Override
    public boolean group() {
        return group != null;
    }

    @Override
    public Integer getId() {
        return group != null ? group.getId() : friend.getId();
    }

    @Override
    public Long getTimeMills() {
        return time;
    }

    public DefaultFriend(FriendVO friend) {
        this.friend = friend;
    }

    public DefaultFriend(Group group) {
        this.group = group;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getMills() {
        return time;
    }
}
