package com.my.liufeng.chat;

import com.my.liufeng.chat.handler.SimpleHandler;
import com.my.liufeng.chat.provider.HelloService;
import com.my.liufeng.chat.provider.UserService;
import com.my.liufeng.chat.util.ContextUtil;
import com.my.liufeng.rpc.netty.NettyServer;
import com.my.liufeng.rpc.netty.codec.CustomDecoder;
import com.my.liufeng.rpc.netty.codec.CustomEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@MapperScan(value = "com.my.liufeng.chat.mapper")
@SpringBootApplication
@EnableTransactionManagement
public class ServerApplication {
    @Value("${server.port}")
    private int port;
    @Autowired
    private HelloService helloService;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private TransactionManager transactionManager;


    @PostConstruct
    public void start() {
        ContextUtil.setApplicationContext(applicationContext);
        UserService bean = ContextUtil.getBean(UserService.class);
        System.out.println("ping: " + bean.ping());
        System.out.println("dataSource: " + dataSource);
        System.out.println("transactionManager: " + transactionManager);
        try {
            // 设置channel handler链路
            ChannelInitializer<SocketChannel> channelInitializer = new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new CustomDecoder());
                    ch.pipeline().addLast(new CustomEncoder());
                    ch.pipeline().addLast(new SimpleHandler());
                }
            };
            // 开启netty服务端
            NettyServer nettyServer = new NettyServer(port);
            nettyServer.setChannelInitializer(channelInitializer);
            nettyServer.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

//    @Bean(name = "transactionManager")
//    public DataSourceTransactionManager transactionManager() {
//        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
//        return transactionManager;
//    }


}
