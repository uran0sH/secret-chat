package com.hwy.secretchat.pojo.vo;

import lombok.Data;

/**
 * @program secret-chat
 * @author huangwenyu
 * @create 2020-03-15
 */
@Data
public class ResultVO<T> {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回的具体数据，可以自定义
     */
    private T data;


}
