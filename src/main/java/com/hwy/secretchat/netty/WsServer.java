package com.hwy.secretchat.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author huangwenyu
 * @program secret-chat
 * @create 2020-03-28
 */
public class WsServer {

    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;

    private static class SingletonWeServer {
        static final WsServer instance = new WsServer();
    }

    public static WsServer getInstance() {
        return SingletonWeServer.instance;
    }

    public WsServer() {
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WsServerInitializer());
    }

    public void start() {
        this.future = server.bind(8081);
        System.out.println("Netty Server start.");
    }
}
