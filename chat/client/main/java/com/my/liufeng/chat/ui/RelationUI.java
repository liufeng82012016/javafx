package com.my.liufeng.chat.ui;

import com.my.liufeng.ui.component.RelationListComp;
import com.my.liufeng.ui.model.Relation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.List;

public class RelationUI extends RelationListComp {
    public RelationUI(List<Relation> relations) {
        super(relations);
    }

    /**
     * 自定义点击事件
     */
    public void setOnSelect(MessageUI right) {
        // 列表选中事件
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Relation>() {
            @Override
            public void changed(ObservableValue<? extends Relation> observable, Relation oldValue, Relation newValue) {
                // 逻辑处理
                right.update();
            }
        });
    }
}
