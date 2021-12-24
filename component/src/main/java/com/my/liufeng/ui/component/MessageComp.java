package com.my.liufeng.ui.component;

import com.my.liufeng.ui.model.Message;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * 聊天消息
 */
public class MessageComp extends HBox {
    private ImageView avatar;
    private Label message;
    private Label nickname;
    private VBox vBox;

    public MessageComp(Message item) {
        // 添加css
        this.getStylesheets().add("css/Message.css");
        this.setId("root");

        vBox = new VBox();
        vBox.setId("box");

        if (item.group()) {
            // 群组内才添加昵称
            nickname = new Label(item.getNickname());
            nickname.setId("nickname");
            vBox.getChildren().add(nickname);
        }
        // 处理头像
        avatar = new ImageView();
        avatar.setImage(new Image(item.getAvatar(), 30, 30, true, true));
        // 处理消息内容
        message = new Label(item.getMessage());
        // 组装
        vBox.getChildren().add(message);
        if (item.mine()) {
            message.setId("message-right");
            this.getChildren().addAll(vBox, avatar);
            this.setPadding(new Insets(10, 10, 0, 50));
        } else {
            message.setId("message-left");
            this.getChildren().addAll(avatar, vBox);
            this.setPadding(new Insets(10, 40, 0, 10));
        }
        this.setSpacing(10);
    }
}
