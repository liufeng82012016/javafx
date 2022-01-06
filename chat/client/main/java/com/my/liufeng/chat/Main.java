package com.my.liufeng.chat;

import com.my.liufeng.ui.component.LoginComp;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("聊天吧");
        Scene scene = new Scene(new LoginComp());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        Screen screen = Screen.getPrimary();
        primaryStage.setX((screen.getBounds().getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((screen.getBounds().getHeight() - primaryStage.getHeight()) / 2);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
