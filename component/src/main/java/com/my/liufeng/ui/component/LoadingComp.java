package com.my.liufeng.ui.component;

import com.jfoenix.controls.JFXSpinner;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class LoadingComp extends VBox {
    public LoadingComp(Stage stage) {
        double width = stage.getWidth();
        double height = stage.getHeight();
        this.setHeight(height);
        this.setWidth(width);
        JFXSpinner spinner = new JFXSpinner();
        Label msg = new Label();
        this.getChildren().addAll(spinner, msg);
    }
}
