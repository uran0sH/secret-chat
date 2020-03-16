package com.hwy.secretchat.pojo.mapper;

import com.hwy.secretchat.pojo.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        User resultUser = userMapper.findOneUserById("11111111");
        Assert.assertNotNull(resultUser);
    }

    @Test
    void insertOneUser() {
        User user = new User();
        user.setId("11111111");
        user.setUsername("brsmsg");
        user.setPassword("brsmsg");
        user.setGender(1);
        boolean result = userMapper.insertOneUser(user);
        System.out.println(result);
    }
}