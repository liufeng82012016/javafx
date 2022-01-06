package com.my.liufeng.chat.gfi;

import com.my.liufeng.chat.manager.DataManager;
import com.my.liufeng.chat.uipj.DefaultFriend;
import com.my.liufeng.ui.component.RelationListComp;
import com.my.liufeng.ui.model.Relation;
import javafx.collections.ObservableList;

public class RelationUI extends RelationListComp {
    public RelationUI(ObservableList<Relation> relations) {
        super(relations);
    }

    /**
     * 自定义点击事件
     */
    public void setOnSelect(MessageUI right) {
        // 列表选中事件
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // 逻辑处理
            DataManager.setSelectedRelation(newValue);
            right.update();
        });
    }

    public void setOnAdd() {
        plus.setOnMouseClicked(event -> {
//            ObservableList<Relation> sessions = listView.getItems();
//            sessions.add(new DefaultFriend());
//            System.out.println(sessions.size());
            DataManager.getSessions().add(new DefaultFriend());
        });
    }
}
