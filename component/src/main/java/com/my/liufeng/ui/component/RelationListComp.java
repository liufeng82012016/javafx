package com.my.liufeng.ui.component;

import com.my.liufeng.ui.model.Relation;
import com.my.liufeng.ui.util.FxUtils;
import com.my.liufeng.ui.util.ObjectUtils;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
public abstract class RelationListComp extends VBox {
    @FXML
    protected ListView<Relation> listView;
    @FXML
    protected TextField searchField;
    @FXML
    protected Label plus;

    private Relation selected;


    public RelationListComp(ObservableList<Relation> relations) {
        // 加在fxml
        FxUtils.load(this, "fxml/RelationList.fxml");
        // 添加底部列表
//        listView.getItems().addAll(relations);
        listView.itemsProperty().bindBidirectional(new SimpleListProperty<>(relations));
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
        // 设置首次打开界面，不默认聚焦到此组件
        searchField.setFocusTraversable(false);
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
        // 监听选项
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Relation>() {
            @Override
            public void changed(ObservableValue<? extends Relation> observable, Relation oldValue, Relation newValue) {
                selected = newValue;
            }
        });
    }

    /**
     * 获取所选项
     */
    public Relation getSelected() {
        return selected;
    }
}
