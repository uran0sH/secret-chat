package com.hwy.secretchat.enums;

import lombok.Getter;

/**
 * 查询好友结果
 */
@Getter
public enum SearchFriendStatusEnum {

    SUCCESS(0, "success"),
    USER_NOT_EXIST(1, "User doesn't exist."),
    NOT_YOURSELF(2, "You can't add yourself as a friend."),
    ALREADY_FRIEND(3, "This user has been your friend.");

    private Integer code;

    private String msg;

    SearchFriendStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
