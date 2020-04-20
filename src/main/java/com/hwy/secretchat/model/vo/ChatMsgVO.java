package com.hwy.secretchat.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author huangwenyu
 * @program secret-chat
 * @create 2020-04-20
 */
@Data
public class ChatMsgVO {

    private String sendUserId;

    private String receiveUserId;

    private String msg;

    private LocalDateTime createTime;
}
