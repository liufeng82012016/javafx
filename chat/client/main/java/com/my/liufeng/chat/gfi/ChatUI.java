package com.my.liufeng.chat.gfi;

import com.my.liufeng.chat.manager.DataManager;
import com.my.liufeng.chat.uipj.Mine;
import com.my.liufeng.ui.model.Message;
import com.my.liufeng.ui.model.Relation;
import com.my.liufeng.ui.util.ObjectUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;

/**
 * 聊天窗口
 *
 * @author liufeng
 */
public class ChatUI extends HBox {
    // 好友列表
    RelationUI relationUI;
    // 信息窗
    MessageUI messageUI;


    public ChatUI() {
        ObservableList<Relation> defaultFriends = DataManager.getSessions();
        ObservableList<Message> messages = defaultFriends == null || defaultFriends.isEmpty() ? FXCollections.emptyObservableList() :
                DataManager.getMessageList(defaultFriends.get(0));
        relationUI = new RelationUI(defaultFriends);
        messageUI = new MessageUI(null);
        relationUI.setOnSelect(messageUI);
        relationUI.setOnAdd();

        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        separator.setMinWidth(1);
        separator.setMaxWidth(1);
        separator.setOpacity(0.5);

        Separator separator2 = new Separator();
        separator2.setOrientation(Orientation.VERTICAL);
        separator2.setMinWidth(1);
        separator2.setMaxWidth(1);
        separator2.setOpacity(0.5);


        this.getChildren().addAll(new ToolUI(this), separator2, relationUI, separator, messageUI);
        this.setOnKeyPressed(event -> {
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

                    @Override
                    public long time() {
                        return System.currentTimeMillis();
                    }
                };
                messageUI.addMessage(message);
            }
        });
    }
}
