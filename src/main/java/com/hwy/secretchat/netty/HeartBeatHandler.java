package com.hwy.secretchat.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author huangwenyu
 * @program secret-chat
 * @create 2020-03-29
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent)evt;

            if (idleStateEvent == IdleStateEvent.READER_IDLE_STATE_EVENT) {
                System.out.println("进入读空闲");
            } else if (idleStateEvent == IdleStateEvent.WRITER_IDLE_STATE_EVENT) {
                System.out.println("进入写空间");
            } else if (idleStateEvent == IdleStateEvent.ALL_IDLE_STATE_EVENT) {
                System.out.println("channel关闭前，users的数量为：" + ChatHandler.users.size());
                Channel channel = ctx.channel();
                // 关闭无用的channel，以防资源浪费
                channel.close();

                System.out.println("channel关闭后，users的数量为：" + ChatHandler.users.size());
            }
        }
    }
}
