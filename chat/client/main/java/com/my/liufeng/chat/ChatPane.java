package com.my.liufeng.chat;

import com.my.liufeng.chat.model.DefaultFriend;
import com.my.liufeng.chat.model.DefaultMessage;
import com.my.liufeng.chat.model.Mine;
import com.my.liufeng.chat.ui.MessageUI;
import com.my.liufeng.chat.ui.RelationUI;
import com.my.liufeng.chat.ui.ToolUI;
import com.my.liufeng.ui.model.Message;
import com.my.liufeng.ui.model.Relation;
import com.my.liufeng.ui.util.ObjectUtils;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import java.util.Arrays;
import java.util.List;

public class ChatPane extends HBox {
    // 好友列表
    RelationUI relationUI;
    // 信息窗
    MessageUI messageUI;


    public ChatPane() {
        List<Message> messages = Arrays.asList(new DefaultMessage(), new DefaultMessage(), new DefaultMessage(),
                new DefaultMessage(), new DefaultMessage(), new DefaultMessage(), new DefaultMessage(), new DefaultMessage(), new DefaultMessage(),
                new DefaultMessage(), new DefaultMessage(), new DefaultMessage(), new DefaultMessage(), new DefaultMessage(), new DefaultMessage());
        List<Relation> defaultFriends = Arrays.asList(new DefaultFriend(), new DefaultFriend(), new DefaultFriend(),
                new DefaultFriend(), new DefaultFriend(), new DefaultFriend(), new DefaultFriend(), new DefaultFriend(), new DefaultFriend(),
                new DefaultFriend(), new DefaultFriend(), new DefaultFriend(), new DefaultFriend(), new DefaultFriend(), new DefaultFriend());
        relationUI = new RelationUI(defaultFriends);
        messageUI = new MessageUI(messages);
        relationUI.setOnSelect(messageUI);

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
