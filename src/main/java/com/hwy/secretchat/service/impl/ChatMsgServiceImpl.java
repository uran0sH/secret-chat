package com.hwy.secretchat.service.impl;

import com.hwy.secretchat.enums.MsgSignFlagEnum;
import com.hwy.secretchat.model.entity.ChatMsg;
import com.hwy.secretchat.model.mapper.ChatMsgMapper;
import com.hwy.secretchat.netty.Message;
import com.hwy.secretchat.service.ChatMsgService;
import com.hwy.secretchat.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author huangwenyu
 * @program secret-chat
 * @create 2020-03-30
 */
@Service
public class ChatMsgServiceImpl implements ChatMsgService {

    @Autowired
    private ChatMsgMapper chatMsgMapper;


    @Override
    public String saveOneMsg(Message message) {
        ChatMsg chatMsg = new ChatMsg();
        BeanUtils.copyProperties(message, chatMsg);
        String msgId = KeyUtil.genUniqueKey();
        LocalDateTime localDateTime = LocalDateTime.now();
        Integer signFlag = MsgSignFlagEnum.UNSIGNED.getType();
        chatMsg.setId(msgId);
        chatMsg.setCreateTime(localDateTime);
        chatMsg.setSignFlag(signFlag);
        boolean result = chatMsgMapper.insertOneChatMsg(chatMsg);
        if (result) {
            return msgId;
        }
        return null;
    }

    @Override
    public boolean updateMsgSigned(List<String> msgIdList) {
        return chatMsgMapper.batchUpdateMsgSigned(msgIdList);
    }

    @Override
    public List<ChatMsg> getHistory(String myId, String friendId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = now.minusDays(4);
        List<ChatMsg> chatMsgList = chatMsgMapper.getHistory(myId, friendId, start, now);
        Collections.sort(chatMsgList);
        return chatMsgList;
    }
}
