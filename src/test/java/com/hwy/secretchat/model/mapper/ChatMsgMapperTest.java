package com.hwy.secretchat.model.mapper;

import com.hwy.secretchat.model.entity.ChatMsg;
import com.hwy.secretchat.utils.KeyUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ChatMsgMapperTest {

    @Autowired
    ChatMsgMapper chatMsgMapper;

    @Test
    @Transactional
    void insertOneChatMsg() {
        ChatMsg chatMsg = new ChatMsg();
        chatMsg.setId(KeyUtil.genUniqueKey());
        boolean result = chatMsgMapper.insertOneChatMsg(chatMsg);
        Assert.assertEquals(true, result);
    }
}