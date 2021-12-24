package com.my.liufeng.chat.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * @Author liufeng
 * @Description: 管理服务器长链接
 * @since 2021/5/27 19:49
 */
public abstract class NettyServer {
    private static InternalLogger logger = InternalLoggerFactory.getInstance(NettyServer.class);

    /**
     * 处理业务逻辑的线程数
     */
    private int workers;
    /**
     * 处理socket连接的线程数
     */
    private int bosses;
    /**
     * 绑定端口
     */
    private final int port;
    /**
     * 处理逻辑相关的handler
     */
    private ChannelInitializer<SocketChannel> channelInitializer;


    public NettyServer(int port) {
        this.port = port;
        try {
            init();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void init() throws Exception {
        if (workers == 0) {
            workers = Runtime.getRuntime().availableProcessors();
        }
        if (bosses == 0) {
            bosses = Runtime.getRuntime().availableProcessors() / 2;
        }
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(bosses);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(workers);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(channelInitializer);
            ChannelFuture bindFuture = serverBootstrap.bind(port).sync();
            logger.info("open server ,port :{} ", port);
            bindFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
        logger.info(" server init end,port:{}", port);
    }


}