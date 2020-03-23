package com.hwy.secretchat.enums;

import lombok.Getter;

/**
 * @program secret-chat
 * @author huangwenyu
 * @create 2020-03-23
 */
@Getter
public enum FriendRequestStatusEnum {
    /**
     * 消息处理状态
     */
    UNPROCESSED(0, "This request hasn't been processed"),
    ACCEPTED(1, "This request has been accepted"),
    REFUSED(2, "This request has been refused");

    private Integer code;

    private String msg;

    FriendRequestStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
