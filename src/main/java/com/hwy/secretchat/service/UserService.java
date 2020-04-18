package com.hwy.secretchat.service;

import com.hwy.secretchat.model.entity.User;

import java.util.List;

/**
 * @program secret-chat
 * @author huangwenyu
 * @create 2020-03-23
 */
public interface UserService {

    List<User> findAllUsers();

    User findOneUserById(String id);

    boolean insertOneUser(User user);

    /**
     * 通过用户名查找用户
     * @param username 用户名
     * @return 找到返回一个用户类，找不到返回null
     */
    User findOneUserByUsername(String username);

    /**
     * 判断是否登录成功
     * @param username 登录的用户名
     * @param password 登录的密码
     * @return 登录成功返回用户Id，失败返回null
     */
    String isLoginSuccessful(String username, String password);

    /**
     * 注册用户
     * @param username 用户名
     * @param password 密码
     * @return 注册成功返回true，失败返回false
     */
    String registerUser(String username, String password);

    /**
     * 更新公钥
     * @param username
     * @param publicKey
     * @return
     */
    boolean updatePublicKey(String username, String publicKey);

    /**
     * 更新头像
     * @param userId
     * @param portrait
     * @return
     */
    boolean updatePortrait(String userId, String portrait);

    /**
     * 更新用户名
     * @param id
     * @param username
     * @return
     */
    boolean updateUsername(String id, String username);

    /**
     * 更新密码
     * @param id
     * @param password
     * @return
     */
    boolean updatePassword(String id, String password);

    /**
     * 更新个性签名
     * @param id
     * @param description
     * @return
     */
    boolean updateDescription(String id, String description);
}
