package com.hwy.secretchat.service;

import com.hwy.secretchat.model.entity.ChatMsg;
import com.hwy.secretchat.netty.Message;

import java.util.List;

/**
 * @author huangwenyu
 * @program secret-chat
 * @create 2020-03-30
 */
public interface ChatMsgService {

    /**
     * 在数据库保存一条消息
     * @param message
     * @return
     */
    String saveOneMsg(Message message);

    /**
     * 批量或者当个更新消息为签收状态
     * @param msgIdList
     * @return
     */
    boolean updateMsgSigned(List<String> msgIdList);

    /**
     * 获取聊天记录
     * @param myId
     * @param friendId
     * @return
     */
    List<ChatMsg> getHistory(String myId, String friendId);

    List<ChatMsg> getUnsentMessage(String myId);


    boolean updateSendStateToSuccess(String msgId);
}
