package com.hwy.secretchat.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author huangwenyu
 * @program secret-chat
 * @create 2020-03-29
 */
public class WsServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline = socketChannel.pipeline();

        // 用于支持http支持
        // http编码器
        channelPipeline.addLast(new HttpServerCodec());
        // 对写大数据流的支持
        channelPipeline.addLast(new ChunkedWriteHandler());
        // 对httpMessage进行聚合，聚合成FullHttpRequest或FullHttpResponse
        channelPipeline.addLast(new HttpObjectAggregator(1024 * 64));

        // 增加心跳机制
        // 如果是读空闲或者写空闲，不处理
        channelPipeline.addLast(new IdleStateHandler(8, 10, 12));
        channelPipeline.addLast(new HeartBeatHandler());

        channelPipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        channelPipeline.addLast(new ChatHandler());
    }
}
