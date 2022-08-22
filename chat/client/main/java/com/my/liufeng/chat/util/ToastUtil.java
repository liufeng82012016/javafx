package com.my.liufeng.chat.util;

import com.my.liufeng.chat.manager.TimeLineManager;
import javafx.animation.KeyFrame;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


/**
 * 展示一个toast
 *
 * @author liufeng
 */
public class ToastUtil {
    /**
     * 舞台
     */
    private static Stage stage;
    /**
     * 标签
     */
    private static Label label;

    static {
        stage = new Stage(StageStyle.TRANSPARENT);
        label = new Label();
        label.setStyle("-fx-background: rgba(3,2,2,0.5);-fx-border-radius:5;-fx-background-radius: 5");
        label.setPrefHeight(30);
        label.setPadding(new Insets(5));
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font(10));
        Scene scene = new Scene(label);
        scene.setFill(null);
        stage.setScene(scene);
    }

    /**
     * 展示toast
     *
     * @param msg   消息内容
     * @param time  toast事件
     * @param color 文本颜色
     */
    public static void toast(String msg, int time, Color color) {
        // todo 计算位置
        label.setText(msg);
        label.setTextFill(color);
        System.out.println("toast " + stage.getHeight() + ":" + stage.getWidth());
        System.out.println("toast " + label.getLayoutX() + ":" + label.getLayoutY());
        stage.show();
        KeyFrame frame = new KeyFrame(
                Duration.millis(time),
                ae -> stage.close());
        TimeLineManager.addKeyFrame(frame);
    }

}
