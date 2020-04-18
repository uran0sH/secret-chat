package com.hwy.secretchat.controller;

import com.hwy.secretchat.enums.ResultEnum;
import com.hwy.secretchat.exception.ReturnException;
import com.hwy.secretchat.model.bo.UserBO;
import com.hwy.secretchat.model.entity.User;
import com.hwy.secretchat.model.vo.ResultVO;
import com.hwy.secretchat.model.vo.UserVO;
import com.hwy.secretchat.service.UserService;
import com.hwy.secretchat.utils.ResultVOUtil;
import com.hwy.secretchat.utils.encryption.SHA1EncryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program secret-chat
 * @author huangwenyu
 * @create 2020-03-13
 */
@RestController
@RequestMapping("account")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param userBO
     * @return
     */
    @PostMapping("/login")
    public ResultVO<UserVO> login(@RequestBody UserBO userBO) {

        if (StringUtils.isBlank(userBO.getUsername()) || StringUtils.isBlank(userBO.getPassword())) {
            throw new ReturnException(ResultEnum.PARAM_ERROR);
        }

        String userId = userService.isLoginSuccessful(userBO.getUsername(), userBO.getPassword());
        if (userId != null) {
            User user = userService.findOneUserById(userId);
            userService.updatePublicKey(userBO.getUsername(), userBO.getPublicKey());
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return ResultVOUtil.success(userVO);
        } else {
            throw new ReturnException(ResultEnum.LOGIN_FAILED);
        }

    }

    /**
     * 用户注册
     * @param userBO
     * @return
     */
    @PostMapping("/register")
    public ResultVO<String> register(@RequestBody UserBO userBO) {

        if (StringUtils.isBlank(userBO.getUsername()) || StringUtils.isBlank(userBO.getPassword())) {
            throw new ReturnException(ResultEnum.PARAM_ERROR);
        }

        if (userService.findOneUserByUsername(userBO.getUsername()) != null) {
            throw new ReturnException(ResultEnum.USERNAME_DUPLICATE);
        }

        String resultId = userService.registerUser(userBO.getUsername(), userBO.getPassword());

        if (resultId != null) {
            return ResultVOUtil.success(resultId);
        } else {
            throw new ReturnException(ResultEnum.REGISTER_FAILED);
        }
    }

    @PostMapping("/update/portrait")
    public ResultVO updatePortrait(@RequestParam("id") String userId, @RequestParam("portrait") String faceImage) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(faceImage)) {
            throw new ReturnException(ResultEnum.PARAM_ERROR);
        }
        boolean result = userService.updatePortrait(userId, faceImage);
        return ResultVOUtil.success(result);
    }

    @PostMapping("/update/username")
    public ResultVO updateUsername(@RequestParam("id") String userId, String username) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(username)) {
            throw new ReturnException(ResultEnum.PARAM_ERROR);
        }
        boolean result = userService.updateUsername(userId, username);
        return ResultVOUtil.success(result);
    }

    @PostMapping("/update/password")
    public ResultVO updatePassword(@RequestParam("id") String userId, String oldPassword, String newPassword) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)) {
            throw new ReturnException(ResultEnum.PARAM_ERROR);
        }

        User user = userService.findOneUserById(userId);
        String oldPasswordHashed = SHA1EncryptUtil.encryptStr(oldPassword);
        if (!StringUtils.equals(oldPasswordHashed, user.getPassword())) {
            return ResultVOUtil.error("password error");
        }
        boolean result = userService.updatePassword(userId, newPassword);
        return ResultVOUtil.success(result);
    }

    @PostMapping("/update/description")
    public ResultVO updateDescription(@RequestParam("id") String userId, String description) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(description)) {
            throw new ReturnException(ResultEnum.PARAM_ERROR);
        }
        boolean result = userService.updateDescription(userId, description);
        return ResultVOUtil.success(result);
    }
}
