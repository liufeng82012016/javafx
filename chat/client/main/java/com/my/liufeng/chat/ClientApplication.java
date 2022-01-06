package com.my.liufeng.chat;

import com.my.liufeng.chat.api.RemoteUserService;
import com.my.liufeng.chat.gfi.LoginUI;
import com.my.liufeng.rpc.context.MethodProxyRepository;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.concurrent.CompletableFuture;

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
        loginUI.getLogin().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                long startTime = System.currentTimeMillis();
                System.out.println("click：" + startTime);
                boolean begin = loginUI.setBegin();
                if (!begin) {
                    System.out.println("return");
                    return;
                }
                RemoteUserService userService = MethodProxyRepository.getProxy(RemoteUserService.class);
                CompletableFuture<Boolean> remoteInvoke;
                boolean success;
                try {
                    if (loginUI.isRegister()) {
                        success = userService.register(loginUI.getText());
                    } else if (loginUI.isLogin()) {
                        success = userService.login(loginUI.getText());
                    } else {
                        return;
                    }
                } catch (Exception e) {
//                    e.printStackTrace();
                } finally {
                    System.out.println("end: " + (System.currentTimeMillis() - startTime));
                    loginUI.setEnd();
                }
            }
        });
        primaryStage.setScene(new Scene(loginUI));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
