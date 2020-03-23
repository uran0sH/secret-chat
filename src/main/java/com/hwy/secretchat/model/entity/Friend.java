package com.hwy.secretchat.model.entity;

import lombok.Data;

/**
 * @program secret-chat
 * @author huangwenyu
 * @create 2020-03-16
 */
@Data
public class Friend {

    private String id;

    private String myId;

    private String friendId;
}
