package com.hwy.secretchat.exception;

import com.hwy.secretchat.enums.ResultEnum;
import com.hwy.secretchat.enums.SearchFriendStatusEnum;

/**
 * @program: secret-chat
 * @author: huangwenyu
 * @create: 2020-03-15
 */
public class ReturnException extends RuntimeException{

    private Integer code;

    public ReturnException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public ReturnException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ReturnException(SearchFriendStatusEnum searchFriendStatusEnum) {
        super(searchFriendStatusEnum.getMsg());
        this.code = searchFriendStatusEnum.getCode();
    }
}
