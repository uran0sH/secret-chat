package com.hwy.secretchat.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @author huangwenyu
 * @program secret-chat
 * @create 2020-03-30
 */
@Getter
public enum MsgSignFlagEnum {

    /**
     * 签收类型
     */
    UNSIGNED(0, "unsigned"),
    SIGNED(1, "signed");

    public final Integer type;
    public final String content;

    MsgSignFlagEnum(Integer type, String content){
        this.type = type;
        this.content = content;
    }

}
