# javafx

封装自己用的组件

### 遇到的问题

| 问题 | 解决方式 | 时间 |
| ---- | ---- | ---- |
|mac getClass.getResource()获取不到文件|使用classLoader获取|2021-12-22|
|fx自定义组件不要fx:controller属性，否则子节点可能无法注入成功|去除该属性|2021-12-22|
|不定长文案展示问题|label的wrapText会自动扩充，只需要定义宽度就好|2021-12-23|
|FXCollections.observableList()方法会根据不同的参数生成不同的实例对象|对于要求可变对象，传入的list也可变就好|2021-12-31|
|ListView 数据绑定|listView.itemsProperty().bindBidirectional(new SimpleListProperty<>(relations))|2021-12-32|
