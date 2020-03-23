package com.hwy.secretchat.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program secret-chat
 * @author huangwenyu
 * @create 2020-03-22
 */
@Data
public class FriendRequest {

    private String id;

    private String sendUserId;

    private String receiveUserId;

    private Integer isAccept;

    private LocalDateTime requestDateTime;
}
