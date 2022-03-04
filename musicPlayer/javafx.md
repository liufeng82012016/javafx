## JavaFX实践

#### 自定义组件
1. 新建fxml文件
```text
// 指定组件类型，各种属性
<fx:root id="root" type="javafx.scene.layout.BorderPane" prefHeight="600" prefWidth="800">
    // 组件添加子节点
    <center>
        <JFXButton fx:id="startGame" text="开始游戏"></JFXButton>
    </center>
    // 指定组件css
    <stylesheets>
        <URL value="@/css/chess/Index.css"/>
    </stylesheets>
</fx:root>
```
2. 生成对应的Java文件，当作一个普通的节点使用即可
```java
public class IndexView extends BorderPane {
    @FXML
    private Button startGame;

    public IndexView() {
        FxUtils.load(this, "fxml/chess/Index.fxml");
    }

    /**
     * 设置button点击事件
     *
     * @param eventEventHandler 事件处理器
     */
    public void setOnClick(EventHandler<ActionEvent> eventEventHandler) {
        startGame.setOnAction(eventEventHandler);
    }
}
```