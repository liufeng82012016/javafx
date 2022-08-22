package com.my.liufeng.chat.gfi;

import com.my.liufeng.chat.manager.DataManager;
import com.my.liufeng.ui.component.RelationListComp;
import com.my.liufeng.ui.model.UiRelation;
import javafx.collections.ObservableList;

/**
 * 好友列表实现
 *
 * @author liufeng
 */
public class RelationUI extends RelationListComp {

    public RelationUI(ObservableList<UiRelation> relations) {
        super(relations);
    }

    /**
     * 自定义点击事件
     */
    public void setOnSelect(MessageUI right) {
        // 列表选中事件
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // 逻辑处理
            DataManager.setSelectedSession(newValue);
            // todo 改造，使用数据驱动。right组件注册一个类到DataManager，DataManager数据更新，right通过数据绑定做UI更新
            right.update();
        });
    }

    public void setOnAdd() {
        plus.setOnMouseClicked(event -> {
            DataManager.refreshSession();
        });
    }
}
