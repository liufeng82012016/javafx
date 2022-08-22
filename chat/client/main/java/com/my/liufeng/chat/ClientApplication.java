package com.my.liufeng.chat;

import com.my.liufeng.chat.event.AbstractChatEvent;
import com.my.liufeng.chat.event.ChatEventHandler;
import com.my.liufeng.chat.event.EventManager;
import com.my.liufeng.chat.event.LoginEvent;
import com.my.liufeng.chat.gfi.ChatUI;
import com.my.liufeng.chat.gfi.LoginUI;
import com.my.liufeng.chat.manager.DataManager;
import com.my.liufeng.rpc.context.MethodProxyRepository;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 客户端启动入口，继承自JavaFX application，打开一个图形界面
 *
 * @author liufeng
 */
public class ClientApplication extends Application implements ChatEventHandler {
    private static final InternalLogger LOG = InternalLoggerFactory.getInstance(ClientApplication.class);

    /**
     * JavaFx主页
     */
    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ClientApplication.primaryStage = primaryStage;
        EventManager.registerHandler(LoginEvent.class, this);
        // 默认扫描本包，生成远程调用代理类
        String[] packages = new String[]{ClientApplication.class.getPackage().getName()};
        MethodProxyRepository.scan(packages);
        // 生成UI
        LoginUI loginUI = new LoginUI();
        // 展示主面板
        primaryStage.setScene(new Scene(loginUI));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void handle(AbstractChatEvent event) {
        if (LoginEvent.class == event.getClass()) {
            // 初始化聊天窗口
            ChatUI chatPane = new ChatUI();
            primaryStage.setScene(new Scene(chatPane));
            // 刷新会话列表
            DataManager.refreshSession();
        }
    }
}
