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
        loginUI.getLogin().setOnMouseClicked(event -> {
            // 登录
            boolean begin = loginUI.setBegin();
            if (!begin) {
                return;
            }
            RemoteUserService userService = MethodProxyRepository.getProxy(RemoteUserService.class);
            UserInfo userInfo;
            try {
                if (loginUI.isRegister()) {
                    userInfo = userService.register(loginUI.getText());
                } else if (loginUI.isLogin()) {
                    userInfo = userService.login(loginUI.getText());
                } else {
                    return;
                }
                if (userInfo != null) {
                    Mine.setUserInfo(userInfo);

                    ChatPane chatPane = new ChatPane();
                    primaryStage.setScene(new Scene(chatPane));

                    DataManager.refreshSession();
                }
            } catch (Exception e) {
//                    e.printStackTrace();
            } finally {
                loginUI.setEnd();
            }
        });
        primaryStage.setScene(new Scene(loginUI));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
