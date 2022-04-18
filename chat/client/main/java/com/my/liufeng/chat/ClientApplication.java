package com.my.liufeng.chat;

import com.my.liufeng.chat.api.RemoteUserService;
import com.my.liufeng.chat.entity.UserInfo;
import com.my.liufeng.chat.gfi.LoginUI;
import com.my.liufeng.chat.manager.DataManager;
import com.my.liufeng.chat.uipj.Mine;
import com.my.liufeng.rpc.context.MethodProxyRepository;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientApplication extends Application {
    private static InternalLogger log = InternalLoggerFactory.getInstance(ClientApplication.class);


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 默认扫描本包
        String[] packages = new String[]{ClientApplication.class.getPackage().getName()};
        MethodProxyRepository.scan(packages);
        // 生成UI
        LoginUI loginUI = new LoginUI();
        EventHandler<Event> eventHandler = event -> {
            // 登录
            boolean begin = loginUI.setBegin();
            if (!begin) {
                return;
            }
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
                    // 其他事件，被篡改，什么也不做
                    return;
                }
                if (userInfo != null) {
                    Mine.setUserInfo(userInfo);
                    // 初始化聊天窗口
                    ChatPane chatPane = new ChatPane();
                    primaryStage.setScene(new Scene(chatPane));
                    // 刷新会话列表
                    DataManager.refreshSession();
                }
            } catch (Exception e) {
                 e.printStackTrace();
            } finally {
                loginUI.setEnd();
            }
        };

        loginUI.setLoginAction(eventHandler);

        // 展示主面板
        primaryStage.setScene(new Scene(loginUI));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
