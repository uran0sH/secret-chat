package com.hwy.secretchat.exception;

import com.hwy.secretchat.enums.ResultEnum;

/**
 * @program: secret-chat
 * @author: huangwenyu
 * @create: 2020-03-15
 */
public class UserException extends RuntimeException{

    private Integer code;

    public UserException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public UserException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
