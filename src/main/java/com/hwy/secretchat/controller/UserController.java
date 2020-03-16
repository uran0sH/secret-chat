package com.hwy.secretchat.controller;

import com.hwy.secretchat.enums.ResultEnum;
import com.hwy.secretchat.exception.UserException;
import com.hwy.secretchat.pojo.bo.UserBO;
import com.hwy.secretchat.pojo.vo.ResultVO;
import com.hwy.secretchat.service.UserService;
import com.hwy.secretchat.utils.ResultVOUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: secret-chat
 * @author: huangwenyu
 * @create: 2020-03-13
 */
@RestController
@RequestMapping("account")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResultVO<String> login(@RequestBody UserBO userBO) {

        if (StringUtils.isBlank(userBO.getUsername()) || StringUtils.isBlank(userBO.getPassword())) {
            throw new UserException(ResultEnum.PARAM_ERROR);
        }

        String userId = userService.isLoginSuccessful(userBO.getUsername(), userBO.getPassword());
        if (userId != null) {
            return ResultVOUtil.success(userId);
        } else {
            throw new UserException(ResultEnum.LOGIN_FAILED);
        }

    }

    @PostMapping("/register")
    public ResultVO<String> register(@RequestBody UserBO userBO) {

        if (StringUtils.isBlank(userBO.getUsername()) || StringUtils.isBlank(userBO.getPassword())) {
            throw new UserException(ResultEnum.PARAM_ERROR);
        }

        if (userService.findOneUserByUsername(userBO.getUsername()) != null) {
            throw new UserException(ResultEnum.USERNAME_DUPLICATE);
        }

        String resultId = userService.registerUser(userBO.getUsername(), userBO.getPassword());

        if (resultId != null) {
            return ResultVOUtil.success(resultId);
        } else {
            throw new UserException(ResultEnum.REGISTER_FAILED);
        }
    }
}
