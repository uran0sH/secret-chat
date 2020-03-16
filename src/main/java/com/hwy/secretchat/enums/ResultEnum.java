package com.hwy.secretchat.enums;

import lombok.Getter;

/**
 * @program: secret-chat
 * @author: huangwenyu
 * @create: 2020-03-15
 * 返回结果状态码和状态信息的枚举类
 */
@Getter
public enum ResultEnum {

    /**
     * 状态码和状态信息枚举
     */
    SUCCESS(200, "success"),
    PARAM_ERROR(10, "parameter error"),
    LOGIN_FAILED(11, "password or username error"),
    REGISTER_FAILED(12, "register failed"),
    USERNAME_DUPLICATE(13, "username duplicate");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
