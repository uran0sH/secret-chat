package com.hwy.secretchat.service.impl;

import com.hwy.secretchat.model.entity.ChatMsg;
import com.hwy.secretchat.service.ChatMsgService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ChatMsgServiceImplTest {

    @Autowired
    ChatMsgService chatMsgService;

    @Test
    void getHistory() {
        List<ChatMsg> chatMsgList = chatMsgService.getHistory("brsmsg_1586334335390388232","brsmsg_1584278438707597686");
        for (ChatMsg chatMsg : chatMsgList) {
            System.out.println(chatMsg);
        }
    }
}