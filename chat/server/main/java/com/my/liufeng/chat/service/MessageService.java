package com.my.liufeng.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.my.liufeng.chat.entity.Message;
import com.my.liufeng.chat.vo.MessageQueryVO;
import com.my.liufeng.chat.vo.NewMessageVO;

public interface MessageService extends IService<Message> {
    /**
     * 查询未读信息列表
     */
    NewMessageVO messageList(MessageQueryVO messageQueryVO);

    /**
     * 标记信息已读
     */
    void updateMaxMsgId(Long maxMsgId);

    /**
     * ð消息
     * @param message
     */
    void receiveMsg(Message message);
}
