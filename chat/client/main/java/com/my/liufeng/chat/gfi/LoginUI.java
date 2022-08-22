package com.my.liufeng.chat.gfi;

import com.my.liufeng.chat.gfi.handler.EventHandlerFactory;
import com.my.liufeng.chat.vo.LoginVO;
import com.my.liufeng.ui.component.LoginComp;
import com.my.liufeng.util.Conditions;
import com.my.liufeng.util.StringUtil;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * 登陆页面ui实现
 *
 * @author liufeng
 */
public class LoginUI extends LoginComp {

    public LoginUI() {
        this.setLoginAction(EventHandlerFactory.getLoginEventHandler(this));
    }

    /**
     * 计数器，用于切换按钮文案状态
     */
    private int counter;

    /**
     * 设置状态为初始状态
     */
    public void setEnd() {
        counter = 0;
    }

    /**
     * 设置状态为登录中，防止并发
     */
    public synchronized boolean setBegin() {
        if (counter == 0) {
            counter = 1;
            return true;
        }
        return false;
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

    /**
     * 暴露按钮点击事件
     */
    public void setLoginAction(EventHandler<Event> eventHandler) {
        login.setOnMouseClicked(eventHandler);
        login.setOnKeyPressed(eventHandler);
    }

    public void addEnterHandler(EventHandler<Event> eventHandler) {
        login.setOnKeyPressed(eventHandler);
    }
}
