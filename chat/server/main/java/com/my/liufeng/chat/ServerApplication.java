package com.my.liufeng.chat;

import com.my.liufeng.chat.handler.SimpleHandler;
import com.my.liufeng.rpc.netty.NettyServer;
import com.my.liufeng.rpc.netty.codec.CustomDecoder;
import com.my.liufeng.rpc.netty.codec.CustomEncoder;
import com.my.liufeng.rpc.netty.handler.beat.ServerIdleStateTrigger;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 服务端启动
 *
 * @author liufeng
 */
@MapperScan(value = "com.my.liufeng.chat.mapper")
@SpringBootApplication(scanBasePackages = {"com.my.liufeng.chat"})
@EnableTransactionManagement
public class ServerApplication implements CommandLineRunner {
    @Value("${netty.port}")
    private int port;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private DataSource dataSource;

//    @PostConstruct
//    public void start() {
//
//        // 新建线程用于netty监听端口，否则可能主应用阻塞
//        new Thread(() -> {
//            try {
//                // 设置channel handler链路
//                ChannelInitializer<SocketChannel> channelInitializer = new ChannelInitializer<SocketChannel>() {
//                    @Override
//                    protected void initChannel(SocketChannel ch) throws Exception {
//                        ch.pipeline().addLast(new CustomDecoder());
//                        ch.pipeline().addLast(new CustomEncoder());
//                        ch.pipeline().addLast(new SimpleHandler());
//                    }
//                };
//                // 开启netty服务端
//                NettyServer nettyServer = new NettyServer(port);
//                nettyServer.setChannelInitializer(channelInitializer);
//                nettyServer.start();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }).start();
//
//    }


    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);

    }


    @Override
    public void run(String... args) throws Exception {
        // 设置channel handler链路
        ChannelInitializer<SocketChannel> channelInitializer = new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new CustomDecoder());
                ch.pipeline().addLast(new CustomEncoder());
                ch.pipeline().addLast(new SimpleHandler());
                ch.pipeline().addLast(new ServerIdleStateTrigger());
            }
        };
        // 开启netty服务端
        NettyServer nettyServer = new NettyServer(port);
        nettyServer.setChannelInitializer(channelInitializer);
        nettyServer.start();
    }
}
