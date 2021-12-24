package com.my.liufeng.chat.ui;

import com.jfoenix.controls.JFXColorPicker;
import com.my.liufeng.ui.util.FxUtils;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;


public class ToolUI extends VBox {
    private Node parent;
    @FXML
    private Label bgManager;

    public ToolUI(Region parent) {
        this.parent = parent;
        FxUtils.load(this, "fxml/Tool.fxml");
        bgManager.setOnMouseClicked(event -> {
            Stage dialog = new Stage();
            ColorPicker colorPicker = new JFXColorPicker();
            colorPicker.setValue(Color.ORANGE);
            dialog.setScene(new Scene(colorPicker));
            colorPicker.setOnAction(event1 -> {
                String style = colorPicker.getValue().toString().replaceAll("0x", "#");
                parent.setBackground(new Background(new BackgroundFill(Paint.valueOf(style), new CornerRadii(0), Insets.EMPTY)));
                dialog.close();
            });
            dialog.show();
        });
    }
}
