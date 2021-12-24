package com.my.liufeng.chat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
//        Scene scene = SceneFactory.getScene(SceneConstants.INDEX);
        primaryStage.setTitle("音乐播放器");
        Scene scene = new Scene(new ChatPane());
        System.out.println(scene.getWidth());
        System.out.println(scene.getHeight());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        System.out.println(primaryStage.getWidth());
        System.out.println(primaryStage.getHeight());
        Screen screen = Screen.getPrimary();
        primaryStage.setX((screen.getBounds().getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((screen.getBounds().getHeight() - primaryStage.getHeight()) / 2);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
