package com.hwy.secretchat.model.bo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @program secret-chat
 * @author huangwenyu
 * @create 2020-03-15
 */
@Data
public class UserBO {

    @NotEmpty(message = "username cannot be empty")
    private String username;

    @NotEmpty(message = "password cannot be empty")
    private String password;

    @NotEmpty(message = "publicKey cannot be empty")
    private String publicKey;
}
