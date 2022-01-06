package com.my.liufeng.chat;

import com.my.liufeng.chat.gfi.MessageUI;
import com.my.liufeng.chat.gfi.RelationUI;
import com.my.liufeng.chat.gfi.ToolUI;
import com.my.liufeng.chat.manager.DataManager;
import com.my.liufeng.chat.uipj.Mine;
import com.my.liufeng.ui.model.Message;
import com.my.liufeng.ui.model.Relation;
import com.my.liufeng.ui.util.ObjectUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class ChatPane extends HBox {
    // 好友列表
    RelationUI relationUI;
    // 信息窗
    MessageUI messageUI;


    public ChatPane() {
        ObservableList<Relation> defaultFriends = DataManager.getSessions();
        ObservableList<Message> messages = defaultFriends == null || defaultFriends.isEmpty() ? FXCollections.emptyObservableList() :
                DataManager.messageList.get(defaultFriends.get(0));
        relationUI = new RelationUI(defaultFriends);
        messageUI = new MessageUI(messages);
        relationUI.setOnSelect(messageUI);
        relationUI.setOnAdd();

        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        separator.setMinWidth(1);
        separator.setMaxWidth(1);
        separator.setOpacity(0.5);

        this.getChildren().addAll(new ToolUI(this), relationUI, separator, messageUI);
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("enter press");
                if (event.getCode() != KeyCode.ENTER) {
                    // 非此按键
                    return;
                }
                String msg = messageUI.getText();
                if (ObjectUtils.isBlank(msg)) {
                    // 空消息
                    return;
                }
                if (event.getCode() == KeyCode.ENTER) {
                    Relation relation = Mine.getInstance();
                    if (ObjectUtils.isNull(relation)) {
                        return;
                    }
                    Message message = new Message() {
                        @Override
                        public String getNickname() {
                            return relation.getTitle();
                        }

                        @Override
                        public String getMessage() {
                            return msg;
                        }

                        @Override
                        public String getAvatar() {
                            return relation.getIcon();
                        }

                        @Override
                        public boolean mine() {
                            return true;
                        }

                        @Override
                        public boolean group() {
                            return false;
                        }
                    };
                    messageUI.addMessage(message);
                }
            }
        });
    }
}
