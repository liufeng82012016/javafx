package com.my.liufeng.ui.component;

import com.my.liufeng.ui.model.Message;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class MessageComp extends HBox {
    private ImageView avatar;
    private Label message;
    private Label nickname;
    private VBox vBox;

    public MessageComp(Message item) {
        this.getStylesheets().add("css/Message.css");
        nickname = new Label(item.getNickname());
        avatar = new ImageView();
        nickname = new Label(item.getNickname());
        avatar.setImage(new Image(item.getAvatar(), 30, 30, true, true));
        message = new Label(item.getMessage());
        message.setWrapText(true);
        message.setMaxWidth(520);


        vBox = new VBox();
        vBox.getChildren().addAll(nickname, message);
        if (item.mine()) {
            this.getChildren().addAll(vBox, avatar);
        } else {
            this.getChildren().addAll(avatar, vBox);
        }
        this.setPadding(new Insets(10, 10, 0, 10));
        this.setSpacing(10);
    }
}
