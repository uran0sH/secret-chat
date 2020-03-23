package com.hwy.secretchat.model.mapper;

import com.hwy.secretchat.model.entity.User;
import com.hwy.secretchat.utils.UserIdUtil;
import com.hwy.secretchat.utils.encryption.SHA1EncryptUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void findAllUser() {

    }

    @Test
    void findOneUserById() {

    }

    @Test
    @Transactional
    void insertOneUser() {
        User user = new User();
        user.setId(UserIdUtil.generateUserId());
        user.setUsername("18571549927");
        user.setPassword(SHA1EncryptUtil.encryptStr("123456"));
        boolean result = userMapper.insertOneUser(user);
        Assert.assertEquals(true, result);
    }
}