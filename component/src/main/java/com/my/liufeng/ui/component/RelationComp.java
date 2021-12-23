package com.my.liufeng.ui.component;

import com.my.liufeng.ui.model.Relation;
import com.my.liufeng.ui.util.FxUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class RelationComp extends HBox {
    @FXML
    private ImageView avatar;
    @FXML
    private Label title;
    @FXML
    private Label time;
    @FXML
    private Label content;
    @FXML
    private HBox hBox;
    @FXML
    private VBox vBox;


    public RelationComp(Relation item) {
        // 加载fxml
        FxUtils.load(this, "fxml/Friend.fxml");
        // 填充数据
        title.setText(item.getTitle());
        time.setText(item.getTime());
        content.setText(item.getContent());
        avatar.setImage(new Image(item.getIcon(), avatar.getFitWidth(), avatar.getFitHeight(), true, true));

        hBox.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                System.out.println("hbox.width:" + newValue);
                title.setPrefWidth(newValue.doubleValue() - 85);
            }
        });

//        hBox.heightProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                System.out.println("hbox.height:" + newValue);
//            }
//        });
//        vBox.widthProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                System.out.println("vbox.width:" + newValue);
//            }
//        });
//        vBox.heightProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                System.out.println("vbox.height:" + newValue);
//            }
//        });
//
//        this.widthProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                System.out.println("box.width:" + newValue);
//            }
//        });
//
//        this.heightProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                System.out.println("box.height:" + newValue);
//            }
//        });
    }
}
