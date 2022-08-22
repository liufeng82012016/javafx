package com.my.liufeng.ui.util;

import com.my.liufeng.ui.component.RelationListComp;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;

/**
 * 自定义JavaFX工具类
 *
 * @author liufeng
 */
public class FxUtils {
    /**
     * 加载fxml
     *
     * @param node fxml对应的controller
     * @param path fxml路径
     */
    public static void load(Node node, String path) {
        URL resource = RelationListComp.class.getClassLoader().getResource(path);
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        fxmlLoader.setRoot(node);
        fxmlLoader.setController(node);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 设置某个节点点击事件
     *
     * @param node    节点
     * @param handler 事件处理器
     */
    public static void setKeyPressed(Node node, EventHandler<KeyEvent> handler) {
        node.setOnKeyPressed(handler);
    }

    public static void setMousePressed(Node node, EventHandler<MouseEvent> handler) {
        node.setOnMouseClicked(handler);
    }
}
