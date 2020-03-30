package com.hwy.secretchat.enums;

import lombok.Getter;

/**
 * @author huangwenyu
 * @program secret-chat
 * @create 2020-03-29
 */
@Getter
public enum MsgActionEnum {
    /**
     * 消息类型
     */
    CONNECT(1, "First connection"),
    CHAT(2, "chat message"),
    SIGN(3, "message sign"),
    KEEP_ALIVE(4, "keep alive");

    private Integer type;

    private String content;

    MsgActionEnum(Integer type, String content) {
        this.type = type;
        this.content = content;
    }
}
