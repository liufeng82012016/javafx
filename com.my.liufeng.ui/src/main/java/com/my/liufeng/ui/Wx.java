package com.my.liufeng.ui;

import com.my.liufeng.ui.component.MessageContainerComp;
import com.my.liufeng.ui.component.RelationListComp;
import com.my.liufeng.ui.model.mock.DefaultFriend;
import com.my.liufeng.ui.model.mock.DefaultMessage;
import com.my.liufeng.ui.model.Message;
import com.my.liufeng.ui.model.Relation;
import javafx.scene.layout.BorderPane;

import java.util.Arrays;
import java.util.List;

public class Wx extends BorderPane {
    RelationListComp center;
    MessageContainerComp right;

    public Wx() {
        List<Message> messages = Arrays.asList(new DefaultMessage(), new DefaultMessage(), new DefaultMessage(),
                new DefaultMessage(), new DefaultMessage(), new DefaultMessage(), new DefaultMessage(), new DefaultMessage(), new DefaultMessage(),
                new DefaultMessage(), new DefaultMessage(), new DefaultMessage(), new DefaultMessage(), new DefaultMessage(), new DefaultMessage());
        List<Relation> defaultFriends = Arrays.asList(new DefaultFriend(), new DefaultFriend(), new DefaultFriend(),
                new DefaultFriend(), new DefaultFriend(), new DefaultFriend(), new DefaultFriend(), new DefaultFriend(), new DefaultFriend(),
                new DefaultFriend(), new DefaultFriend(), new DefaultFriend(), new DefaultFriend(), new DefaultFriend(), new DefaultFriend());
        center = new RelationListComp(defaultFriends);
        right = new MessageContainerComp(messages);
        center.setOnSelect(right);
        this.setCenter(center);
        this.setRight(right);
    }
}
