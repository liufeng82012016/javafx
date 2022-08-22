package com.my.liufeng.ui.component;

import com.my.liufeng.ui.model.UiMessage;
import com.my.liufeng.ui.util.FxUtils;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.List;


/**
 * 聊天窗
 * 顶部的聊天人信息，对话框，输入框，以及右上角的...
 */
public abstract class TalkPaneComp extends VBox {
    @FXML
    protected ListView<UiMessage> messageList;
    @FXML
    protected InputComp input;
    @FXML
    protected Label name;
    @FXML
    protected Separator sep;


    public TalkPaneComp(List<UiMessage> messages, String friendName) {
        FxUtils.load(this, "fxml/TalkPane.fxml");
        messageList.getItems().addAll(messages);
        messageList.scrollTo(messageList.getItems().size());
        name.setText(friendName);
        // 设置渲染
        messageList.setCellFactory(new Callback<ListView<UiMessage>, ListCell<UiMessage>>() {
            @Override
            public ListCell<UiMessage> call(ListView<UiMessage> param) {
                return new ListCell<UiMessage>() {
                    @Override
                    protected void updateItem(UiMessage item1, boolean empty) {
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

    /**
     * 获取输入文本
     */
    public String getText() {
        return input.getText();
    }

    /**
     * 添加信息
     */
    public void addMessage(UiMessage message) {
        ObservableList<UiMessage> items = messageList.getItems();
        items.add(message);
        // 自动滚动到底部
        messageList.scrollTo(items.size());
    }
}
