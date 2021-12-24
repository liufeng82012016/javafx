package com.my.liufeng.ui.util;

import com.my.liufeng.ui.component.RelationListComp;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;

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

    public static void setKeyPressed(Node node, EventHandler<KeyEvent> handler) {
        node.setOnKeyPressed(handler);
    }
}
