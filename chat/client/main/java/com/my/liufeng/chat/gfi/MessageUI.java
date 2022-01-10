package com.my.liufeng.chat.gfi;

import com.my.liufeng.chat.manager.DataManager;
import com.my.liufeng.ui.component.TalkPaneComp;
import com.my.liufeng.ui.model.Message;
import javafx.collections.FXCollections;

import java.util.List;

public class MessageUI extends TalkPaneComp {


    public MessageUI(List<Message> messages) {
        super(messages);
    }

    // 更新数据
    public void update() {
        List<Message> messages = DataManager.getMessageList(DataManager.getSelectedSession());
        messageList.setItems(FXCollections.observableList(messages));
    }

}
