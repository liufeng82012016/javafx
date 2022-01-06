package com.my.liufeng.chat.gfi;

import com.my.liufeng.chat.vo.LoginVO;
import com.my.liufeng.ui.component.LoginComp;
import com.my.liufeng.util.Conditions;
import com.my.liufeng.util.StringUtil;
import javafx.scene.control.Button;

public class LoginUI extends LoginComp {
    private int counter;

    public void setEnd() {
        counter = 0;
    }

    public synchronized boolean setBegin() {
        if (counter == 0) {
            counter = 1;
//            FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000),login);
//            fadeTransition.setFromValue(1.0);
//            fadeTransition.setToValue(0.3);
//            fadeTransition.setCycleCount(4);
//            fadeTransition.setAutoReverse(true);
//            fadeTransition.play();
            return true;
        }
        return false;
    }

    public Button getLogin() {
        return login;
    }

    /**
     * 是否注册
     */
    public boolean isRegister() {
        return "注册".equals(login.getText());
    }

    /**
     * 是否登录
     */
    public boolean isLogin() {
        return "登录".equals(login.getText());
    }

    /**
     * 获取文本内容
     */
    public LoginVO getText() {
        LoginVO loginVO = new LoginVO();
        loginVO.setUsername(username.getText());
        Conditions.expectFalse(StringUtil.isBlank(loginVO.getUsername()), "请输入账号");
        loginVO.setPassword(pwd.getText());
        Conditions.expectFalse(StringUtil.isBlank(loginVO.getPassword()), "请输入密码");
        return loginVO;
    }
}
