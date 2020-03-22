package com.hwy.secretchat.service.impl;

import com.hwy.secretchat.pojo.User;
import com.hwy.secretchat.pojo.mapper.UserMapper;
import com.hwy.secretchat.service.UserService;
import com.hwy.secretchat.utils.UserIdUtil;
import com.hwy.secretchat.utils.encryption.SHA1EncryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: secret-chat
 * @author: huangwenyu
 * @create: 2020-03-13
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAllUsers() {
        return null;
    }

    @Override
    public User findOneUserById(String id) {
        return userMapper.findOneUserById(id);
    }

    @Override
    public boolean insertOneUser(User user) {
        return userMapper.insertOneUser(user);
    }

    @Override
    public User findOneUserByUsername(String username) {
        return userMapper.findOneUserByUsername(username);
    }

    @Override
    public String isLoginSuccessful(String username, String password) {
        User user = findOneUserByUsername(username);
        if (user != null) {
            String passwordHashed = SHA1EncryptUtil.encryptStr(password);
            if(StringUtils.equals(user.getPassword(), passwordHashed)) {
                return user.getId();
            } else {
                return null;
            }
        }
        return null;
    }

    //TODO 行为分析还没添加
    @Override
    public String registerUser(String username, String password) {
        String id = UserIdUtil.generateUserId();
        String passwordHashed = SHA1EncryptUtil.encryptStr(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordHashed);
        user.setId(id);
        boolean result = insertOneUser(user);
        if (result) {
            return id;
        }
        return null;
    }
}
