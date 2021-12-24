package com.my.liufeng.chat;

import com.my.liufeng.chat.model.DefaultFriend;
import com.my.liufeng.chat.model.DefaultMessage;
import com.my.liufeng.chat.ui.MessageUI;
import com.my.liufeng.chat.ui.RelationUI;
import com.my.liufeng.chat.ui.ToolUI;
import com.my.liufeng.ui.model.Message;
import com.my.liufeng.ui.model.Relation;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;

import java.util.Arrays;
import java.util.List;

public class ChatPane extends HBox {
    RelationUI center;
    MessageUI right;


    public ChatPane() {
        List<Message> messages = Arrays.asList(new DefaultMessage(), new DefaultMessage(), new DefaultMessage(),
                new DefaultMessage(), new DefaultMessage(), new DefaultMessage(), new DefaultMessage(), new DefaultMessage(), new DefaultMessage(),
                new DefaultMessage(), new DefaultMessage(), new DefaultMessage(), new DefaultMessage(), new DefaultMessage(), new DefaultMessage());
        List<Relation> defaultFriends = Arrays.asList(new DefaultFriend(), new DefaultFriend(), new DefaultFriend(),
                new DefaultFriend(), new DefaultFriend(), new DefaultFriend(), new DefaultFriend(), new DefaultFriend(), new DefaultFriend(),
                new DefaultFriend(), new DefaultFriend(), new DefaultFriend(), new DefaultFriend(), new DefaultFriend(), new DefaultFriend());
        center = new RelationUI(defaultFriends);
        right = new MessageUI(messages);
        center.setOnSelect(right);

        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        separator.setMinWidth(1);
        separator.setMaxWidth(1);
        separator.setOpacity(0.5);

        this.getChildren().addAll(new ToolUI(this), center, separator, right);
    }
}
