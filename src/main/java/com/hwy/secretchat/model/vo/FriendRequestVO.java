package com.hwy.secretchat.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author huangwenyu
 * @program secret-chat
 * @create 2020-04-22
 */
@Data
public class FriendRequestVO {
    private String id;

    private String sendUserId;

    private String username;

    private String receiveUserId;

    private Integer isAccept;

    private LocalDateTime requestDateTime;
}
