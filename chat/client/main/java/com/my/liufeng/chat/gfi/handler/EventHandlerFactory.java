package com.my.liufeng.chat.gfi.handler;

import com.my.liufeng.chat.api.RemoteUserService;
import com.my.liufeng.chat.entity.UserInfo;
import com.my.liufeng.chat.event.EventManager;
import com.my.liufeng.chat.event.LoginEvent;
import com.my.liufeng.chat.gfi.LoginUI;
import com.my.liufeng.chat.util.ToastUtil;
import com.my.liufeng.rpc.context.MethodProxyRepository;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

/**
 * JavaFX UI界面 时间处理器静态工厂类
 *
 * @author liufeng
 */
public class EventHandlerFactory {
    private static final InternalLogger LOG = InternalLoggerFactory.getInstance(EventHandlerFactory.class);

    /**
     * 登陆页面事件处理器
     *
     * @param loginUI 登陆UI界面
     * @return 登陆页面事件处理器
     */
    public static EventHandler<Event> getLoginEventHandler(LoginUI loginUI) {
        return event -> {
            // 登录
            boolean begin = loginUI.setBegin();
            if (!begin) {
                return;
            }
            System.out.println("lg");
            // 获取代理类
            RemoteUserService userService = MethodProxyRepository.getProxy(RemoteUserService.class);
            UserInfo userInfo;
            try {
                if (loginUI.isRegister()) {
                    // 如果是注册
                    userInfo = userService.register(loginUI.getText());
                } else if (loginUI.isLogin()) {
                    // 如果是登录
                    userInfo = userService.login(loginUI.getText());
                } else {
                    LOG.info("unknown click event");
                    // 其他事件，被篡改，什么也不做
                    return;
                }
                System.out.println("userInfo:" + userInfo);
                if (userInfo != null) {
                    LoginEvent loginEvent = new LoginEvent();
                    loginEvent.setData(userInfo);
                    EventManager.publishEvent(loginEvent);
                } else {
                    // 报错提示
                    ToastUtil.toast("登陆失败", 2000, Color.WHITE);
                }
            } catch (Exception e) {
                LOG.info("login failed.", e);
                ToastUtil.toast("登陆失败", 2000, Color.WHITE);
            } finally {
                loginUI.setEnd();
            }
        };
    }
}
