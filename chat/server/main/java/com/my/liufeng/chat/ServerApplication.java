package com.my.liufeng.chat;

import com.my.liufeng.chat.handler.SimpleHandler;
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
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@MapperScan(value = "com.my.liufeng.chat.mapper")
@SpringBootApplication(scanBasePackages = {"com.my.liufeng.chat"})
@EnableTransactionManagement
public class ServerApplication {
    @Value("${netty.port}")
    private int port;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void start() {

        // 新建线程用于netty监听端口，否则可能主应用阻塞
        new Thread(() -> {
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
        }).start();

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
