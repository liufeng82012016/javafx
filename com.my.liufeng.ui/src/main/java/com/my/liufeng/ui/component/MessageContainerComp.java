package com.my.liufeng.ui.component;

import com.my.liufeng.ui.model.Message;
import com.my.liufeng.ui.model.mock.Message1;
import com.my.liufeng.ui.util.FxUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

import java.util.LinkedList;
import java.util.List;


/**
 * 聊天窗
 * 顶部的聊天人信息，对话框，输入框，以及右上角的...
 */
public class MessageContainerComp extends BorderPane {
    @FXML
    private ListView<Message> messageList;
    @FXML
    private TextArea input;

    public MessageContainerComp(List<Message> messages) {
        FxUtils.load(this, "fxml/TalkPane.fxml");
        messageList.setItems(FXCollections.observableList(messages));
        // 设置渲染
        messageList.setCellFactory(new Callback<ListView<Message>, ListCell<Message>>() {
            @Override
            public ListCell<Message> call(ListView<Message> param) {
                return new ListCell<Message>() {
                    @Override
                    protected void updateItem(Message item1, boolean empty) {
                        super.updateItem(item1, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            // setGraphic可以放入一个节点
                            setGraphic(new MessageComp(item1));
                        }
                    }
                };
            }
        });
    }

    // 留给子类重写
    public void update() {
        List<Message> messages = new LinkedList<>();
        for (int i = 0; i < 31; i++) {
            messages.add(new Message1());
        }
        messageList.setItems(FXCollections.observableList(messages));
    }
}
