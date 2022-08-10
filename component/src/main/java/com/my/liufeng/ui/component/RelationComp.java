package com.my.liufeng.ui.component;

import com.my.liufeng.ui.model.Relation;
import com.my.liufeng.ui.util.FxUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * 联系人
 *
 * @author liufeng
 */
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
        FxUtils.load(this, "fxml/Relation.fxml");
        // 填充数据
        title.setText(item.getTitle());
        time.setText(item.getTime());
        content.setText(item.getContent());
        System.out.println("new RelationComp:" + item.getIcon());
        avatar.setImage(new Image(item.getIcon(), avatar.getFitWidth(), avatar.getFitHeight(), true, true));

        hBox.widthProperty().addListener((observable, oldValue, newValue) -> title.setPrefWidth(newValue.doubleValue() - 85));
    }
}
