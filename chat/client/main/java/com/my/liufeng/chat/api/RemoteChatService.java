package com.my.liufeng.chat.api;

import com.my.liufeng.chat.entity.Friend;
import com.my.liufeng.chat.entity.Message;
import com.my.liufeng.chat.vo.MessageQueryVO;
import com.my.liufeng.chat.vo.NewMessageVO;
import com.my.liufeng.rpc.annotation.MethodStub;

import java.util.List;

@MethodStub(serverAddresses = {"127.0.0.1:10030"}, className = "com.my.liufeng.chat.provider.ChatService", serverName = "A")
public interface RemoteChatService {

    /**
     * 好友列表
     *
     * @return 好友列表
     */
    List<Friend> friendList();

    /**
     * 发送消息
     *
     * @param message 消息内容
     */
    void sendMsg(Message message);

    /**
     * 更新接受到的最大消息Id
     *
     * @param maxMsgId 接受到的最大消息Id
     */
    void read(Long maxMsgId);

    /**
     * 查询最新消息
     *
     * @param messageQueryVO 消息查询条件
     * @return 消息列表
     */
    NewMessageVO messageList(MessageQueryVO messageQueryVO);
}
