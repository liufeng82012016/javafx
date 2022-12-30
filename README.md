# javafx
后端只有数据，尝试用javafx写一些好玩的东西。

### 问题记录
1. SpringBoot 死活获取不到ApplicationContext，或者获取bean很卡顿。
   1. 尝试：ApplicationContextAware(获取不到ApplicationContext)
   2. 尝试：BeanFactoryPostProcessor(获取bean很卡顿)
   3. 解决：利用Autowired注入bean，手动set到工具类
   4. 真正原因：springboot启动netty服务器后，服务器监听导致主线程阻塞
   5. 解决：修改ServerApplication，实现CommandRunner，在run方法启动netty服务器
2. netty日志未打印
   1. 原因：netty只有






