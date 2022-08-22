package com.my.liufeng.ui.component;

import com.jfoenix.controls.JFXButton;
import com.my.liufeng.ui.util.FxUtils;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * 登录窗口
 *
 * @author liufeng
 */
public class LoginComp extends VBox {
    @FXML
    protected JFXButton login;
    @FXML
    protected JFXButton change;
    @FXML
    protected TextField username;
    @FXML
    protected PasswordField pwd;
    @FXML
    protected HBox buttonBox;


    public LoginComp() {
        FxUtils.load(this, "fxml/Login.fxml");
        username.setFocusTraversable(false);
        pwd.setFocusTraversable(false);

        change.setOnMouseClicked(event -> {
            // 切换按钮
            if ("登录".equals(login.getText())) {
                // 调用接口，获取谷歌验证器
                login.setText("注册");
            } else {
                login.setText("登录");
            }
        });

        buttonBox.widthProperty().addListener((observable, oldValue, newValue) -> login.setPrefWidth(newValue.doubleValue() - 60));
    }


}
