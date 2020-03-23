package com.hwy.secretchat.model.entity;

import lombok.Data;

/**
 * @program secret-chat
 * @author huangwenyu
 * @create 2020-03-13
 */
@Data
public class User {

    private String id;

    private String username;

    private String password;

    private Integer gender;

    private String description;

    private String faceImage;

    private String faceImageBig;
}
