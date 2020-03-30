package com.hwy.secretchat.netty;

import lombok.Data;

import java.io.Serializable;

/**
 * @author huangwenyu
 * @program secret-chat
 * @create 2020-03-29
 */
@Data
public class Message implements Serializable {

    private static final long serialVersionUID = -4187414793591372349L;

    /**
     * 发送者的用户id
     */
    private String sendUserId;

    /**
     * 接受者的用户id
     */
    private String receiveUserId;

    /**
     * 聊天内容
     */
    private String msg;

    /**
     * 用于消息的签收
     */
    private String msgId;


}
