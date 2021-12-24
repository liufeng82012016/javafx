package com.my.liufeng.ui.component;

import com.my.liufeng.ui.util.FxUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import javax.swing.text.html.ImageView;

/**
 * 工具栏+输入框
 */
public class InputComp extends VBox {
    @FXML
    private ImageView emoj;

    @FXML
    private TextArea textArea;

    public InputComp() {
        FxUtils.load(this, "fxml/InputComp.fxml");
    }
}
