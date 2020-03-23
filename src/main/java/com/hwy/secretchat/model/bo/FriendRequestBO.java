package com.hwy.secretchat.model.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @author huangwenyu
 * @program secret-chat
 * @create 2020-03-23
 */
@Data
public class FriendRequestBO {

    @JsonProperty(value = "myId")
    @NotEmpty(message = "parameter error")
    private String receiveUserId;

    @JsonProperty(value = "friendId")
    @NotEmpty(message = "parameter error")
    private String sendUserId;

    /**
     * 1 通过 2 拒绝
     */
    @Min(1)
    @Max(2)
    private Integer operateType;
}
