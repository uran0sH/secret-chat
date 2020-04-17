package com.hwy.secretchat.model.vo;

import lombok.Data;

/**
 * @program secret-chat
 * @author huangwenyu
 * @create 2020-03-17
 */
@Data
public class FriendVO {

    private String id;

    private String username;

    private String faceImage;

    private String description;

    private String publicKey;
}
