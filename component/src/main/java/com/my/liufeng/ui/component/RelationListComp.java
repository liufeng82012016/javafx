package com.my.liufeng.ui.component;

import com.my.liufeng.ui.model.Relation;
import com.my.liufeng.ui.util.FxUtils;
import com.my.liufeng.ui.util.ObjectUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 列表栏+顶部搜索栏
 */
public class RelationListComp extends VBox {
    @FXML
    private ListView<Relation> listView;
    @FXML
    private TextField searchField;

    private final List<Relation> relations;


    public RelationListComp(List<Relation> relations) {
        this.relations = relations;
        // 加在fxml
        FxUtils.load(this, "fxml/FriendList.fxml");
        // 添加底部列表
        listView.setItems(FXCollections.observableList(relations));
        // 设置渲染
        listView.setCellFactory(new Callback<ListView<Relation>, ListCell<Relation>>() {
            @Override
            public ListCell<Relation> call(ListView<Relation> param) {
                ListCell<Relation> item = new ListCell<Relation>() {
                    @Override
                    protected void updateItem(Relation item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(new RelationComp(item));
                        }
                    }
                };
                return item;
            }
        });
        // 搜索事件
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Relation> collect;
            if (ObjectUtils.isBlank(newValue)) {
                collect = relations;
            } else {
                collect = relations.stream().filter(relation -> {
                    String title = relation.getTitle();
                    return title.contains(newValue);
                }).collect(Collectors.toList());
            }
            listView.setItems(FXCollections.observableList(collect));
        });
    }

    // todo 这个方法留出去给子类重写
    public void setOnSelect(MessageContainerComp right) {
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
