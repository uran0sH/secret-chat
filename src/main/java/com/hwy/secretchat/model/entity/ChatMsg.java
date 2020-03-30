package com.hwy.secretchat.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author huangwenyu
 * @program secret-chat
 * @create 2020-03-24
 */
@Data
public class ChatMsg {

    private String id;

    private String sendUserId;

    private String receiveUserId;

    private String msg;

    private Integer signFlag;

    private LocalDateTime createTime;
}
