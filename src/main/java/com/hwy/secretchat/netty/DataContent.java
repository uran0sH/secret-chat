package com.hwy.secretchat.netty;

import lombok.Data;

import java.io.Serializable;

/**
 * @author huangwenyu
 * @program secret-chat
 * @create 2020-03-29
 */
@Data
public class DataContent implements Serializable {

    private static final long serialVersionUID = 2585654942220355049L;

    /**
     * 动作类型
     */
    private Integer action;

    /**
     * 聊天内容
     */
    private Message message;

    /**
     * 扩展字段
     */
    private String extend;
}
