package com.my.liufeng.chat.ui;

import com.my.liufeng.chat.model.Message1;
import com.my.liufeng.ui.component.TalkPaneComp;
import com.my.liufeng.ui.model.Message;
import javafx.collections.FXCollections;

import java.util.LinkedList;
import java.util.List;

public class MessageUI extends TalkPaneComp {


    public MessageUI(List<Message> messages) {
        super(messages);
    }

    // 更新数据
    public void update() {
        List<Message> messages = new LinkedList<>();
        for (int i = 0; i < 31; i++) {
            messages.add(new Message1());
        }
        messageList.setItems(FXCollections.observableList(messages));
    }
}
