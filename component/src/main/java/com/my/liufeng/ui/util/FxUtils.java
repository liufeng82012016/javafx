package com.my.liufeng.ui.util;

import com.my.liufeng.ui.component.RelationListComp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;

public class FxUtils {
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
}
