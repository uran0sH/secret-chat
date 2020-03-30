package com.hwy.secretchat;

import com.hwy.secretchat.netty.WsServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author huangwenyu
 * @program secret-chat
 * @create 2020-03-29
 */
@Component
public class NettyBoot implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //判断是否是RootApplicationContext，因为RootApplicationContext没有父容器
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            try {
                WsServer.getInstance().start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
