package com.my.liufeng.chat.provider;

import com.my.liufeng.chat.entity.Friend;
import com.my.liufeng.chat.entity.Message;
import com.my.liufeng.chat.service.MessageService;
import com.my.liufeng.chat.vo.MessageQueryVO;
import com.my.liufeng.chat.vo.NewMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChatService extends HeartService {
    @Autowired
    private MessageService messageService;

    /**
     * 好友列表
     *
     * @return 好友列表
     */
    public List<Friend> friendList() {
        return null;
    }

    /**
     * 发送消息
     *
     * @param message 消息内容
     */
    public void sendMsg(Message message) {
        messageService.receiveMsg(message);
    }

    /**
     * 更新接受到的最大消息Id
     *
     * @param maxMsgId 接受到的最大消息Id
     */
    public void read(Long maxMsgId) {
        messageService.updateMaxMsgId(maxMsgId);
    }

    /**
     * 查询最新消息
     *
     * @param messageQueryVO 消息查询条件
     * @return 消息列表
     */
    public NewMessageVO messageList(MessageQueryVO messageQueryVO) {
        return messageService.messageList(messageQueryVO);
    }
}
