package com.my.liufeng.chat.netty;

import com.my.liufeng.chat.exception.InnerException;
import com.my.liufeng.chat.model.RpcRequest;
import com.my.liufeng.chat.utils.IpUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.util.concurrent.CompletableFuture;


/**
 * @author liufeng
 * @since 2021/5/27 19:48
 */
public abstract class NettyClient {
    private static InternalLogger logger = InternalLoggerFactory.getInstance(NettyClient.class);

    /**
     * 服务器ip
     */
    private String serverHost;
    /**
     * 服务器端口
     */
    private Integer serverPort;
    /**
     * 连接
     */
    private Channel channel;
    /**
     * 处理逻辑相关的handler
     */
    private ChannelInitializer<SocketChannel> channelInitializer;

    public NettyClient() {
        // 不建立连接，等待请求打过来，走注册中心获取端口和ip
    }

    public NettyClient(String serverAddress, ChannelInitializer<SocketChannel> channelInitializer) {
        String[] strings = IpUtil.splitAddress(serverAddress);
        this.serverHost = strings[0];
        this.serverPort = Integer.parseInt(strings[1]);
        this.channelInitializer = channelInitializer;
        connect();
    }


    public NettyClient(Integer serverPort, String serverHost, ChannelInitializer<SocketChannel> channelInitializer) {
        this.serverPort = serverPort;
        this.serverHost = serverHost;
        this.channelInitializer = channelInitializer;
        // 建立服务端连接
        connect();
    }

    /**
     * 连接服务器
     */
    private void connect() {
        if (serverHost == null || serverPort == null || channelInitializer == null) {
            throw new RuntimeException(String.format("connect fail. parameter %s:%s", serverHost, serverPort, channelInitializer));
        }
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(channelInitializer);
        try {
            ChannelFuture connectFuture = bootstrap.connect(serverHost, serverPort).sync();
            connectFuture.addListener((ChannelFutureListener) channelFuture -> {
                if (channelFuture.isSuccess()) {
                    channel = connectFuture.channel();
                    logger.info("connect success.host: {},port:{}");
                } else {
                    throw new InnerException(serverHost + ":" + serverPort + " connect failed");
                }
            });
        } catch (InterruptedException e) {
            throw new RuntimeException(String.format("connect interrupted. parameter %s:%s", serverHost, serverPort));
        }
    }

    public abstract void setChannelInitializer();


    public CompletableFuture<?> sendMsg(RpcRequest request) {
        if (channel == null || !channel.isActive()) {
            // todo 状态判定用哪个？
            connect();
        }
        // 序列化不需要自己做，netty有附带
        ChannelFuture channelFuture = channel.writeAndFlush(request);
        CompletableFuture<?> responseFuture = new CompletableFuture<>();

        channelFuture.addListener((ChannelFutureListener) future -> {
            if (channelFuture.isSuccess()) {
                AttributeKey<Object> key = AttributeKey.newInstance(request.getRequestId());
                channel.attr(key).set(responseFuture);
            } else {
                Throwable cause = channelFuture.cause();
                if (cause != null) {
                    cause.printStackTrace();
                }
                logger.warn("Error writing message to host:{} port:{}", serverHost, serverPort);
                responseFuture.completeExceptionally(future.cause());
            }
        });
        return responseFuture;
    }
}
