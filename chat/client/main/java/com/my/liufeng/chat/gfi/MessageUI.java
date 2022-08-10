package com.my.liufeng.chat.gfi;

import com.my.liufeng.chat.manager.DataManager;
import com.my.liufeng.chat.model.MessageObject;
import com.my.liufeng.ui.component.TalkPaneComp;
import com.my.liufeng.ui.model.Message;
import com.my.liufeng.ui.util.ObjectUtils;
import javafx.collections.FXCollections;

import java.util.Collections;
import java.util.List;

/**
 * 消息框实现
 *
 * @author liufeng
 */
public class MessageUI extends TalkPaneComp {
    // todo 消息也许可以使用flowPane实现


    public MessageUI(MessageObject messageObject) {
        super(messageObject == null ? Collections.emptyList() : messageObject.getMessageList(),
                messageObject != null ? messageObject.getRelation().getTitle() : null);
        if (ObjectUtils.isBlank(name.getText())) {
            name.setVisible(false);
            sep.setVisible(false);
            input.setVisible(false);
            messageList.setVisible(false);
        }
    }

    /**
     * 更新数据
     */
    public void update() {
        List<Message> messages = DataManager.getMessageList(DataManager.getSelectedSession());
        messageList.setItems(FXCollections.observableList(messages));
    }

}
