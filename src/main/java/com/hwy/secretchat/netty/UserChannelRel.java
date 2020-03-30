package com.hwy.secretchat.netty;

import io.netty.channel.Channel;

import java.util.HashMap;

/**
 * @Description UserId和Channel关联管理类
 * @author huangwenyu
 * @program secret-chat
 * @create 2020-03-30
 */
public class UserChannelRel {

    private static HashMap<String, Channel>  channelHashMap = new HashMap<>();

    /**
     * 添加userId和channel的关联
     * @param userId
     * @param channel
     */
    public static void put(String userId, Channel channel) {
        channelHashMap.put(userId, channel);
    }

    /**
     * 获得
     * @param userId
     * @return
     */
    public static Channel get(String userId) {
        return channelHashMap.get(userId);
    }

    /**
     * 测试用的
     */
    public static void output() {
        for (HashMap.Entry<String, Channel> entry : channelHashMap.entrySet()) {
            System.out.println("UserId: " + entry.getKey()
                    + ", ChannelId: " + entry.getValue().id().asLongText());
        }
    }
}
