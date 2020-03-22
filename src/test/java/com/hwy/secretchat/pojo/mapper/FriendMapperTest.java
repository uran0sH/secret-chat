package com.hwy.secretchat.pojo.mapper;

import com.hwy.secretchat.pojo.Friend;
import com.hwy.secretchat.utils.KeyUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class FriendMapperTest {

    @Autowired
    FriendMapper friendMapper;

    @Test
    void findAllFriends() {
    }

    @Test
    @Transactional
    void insertOneFriend() {
        Friend friend = new Friend();
        friend.setId(KeyUtil.genUniqueKey());
        friend.setMyId("brsmsg_1584278438707597686");
        friend.setFriendId("brsmsg_1584864921773373632");
        boolean result = friendMapper.insertOneFriend(friend);
        Assert.assertEquals(true, result);
    }

    @Test
    void deleteOneFriend() {
    }
}