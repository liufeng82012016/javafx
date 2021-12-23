package com.my.liufeng.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
//        Scene scene = SceneFactory.getScene(SceneConstants.INDEX);
        primaryStage.setTitle("音乐播放器");
        primaryStage.show();
        Scene scene = new Scene(new Wx());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
