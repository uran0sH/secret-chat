package com.hwy.secretchat.controller;

import com.hwy.secretchat.enums.ResultEnum;
import com.hwy.secretchat.exception.ReturnException;
import com.hwy.secretchat.model.entity.ChatMsg;
import com.hwy.secretchat.model.vo.ChatMsgVO;
import com.hwy.secretchat.model.vo.ResultVO;
import com.hwy.secretchat.service.ChatMsgService;
import com.hwy.secretchat.utils.ResultVOUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huangwenyu
 * @program secret-chat
 * @create 2020-04-20
 */
@RestController
@RequestMapping("message")
public class ChatMsgController {

    @Autowired
    ChatMsgService chatMsgService;

    @PostMapping("/history")
    public ResultVO getChatHistory(String myId, String receiverId) {

        if (StringUtils.isBlank(myId) || StringUtils.isBlank(receiverId)) {
            throw new ReturnException(ResultEnum.PARAM_ERROR);
        }

        List<ChatMsg> chatMsgList = chatMsgService.getHistory(myId, receiverId);
        List<ChatMsgVO> chatMsgVOList = chatMsgList.stream().map(e -> {
            ChatMsgVO chatMsgVO = new ChatMsgVO();
            BeanUtils.copyProperties(e, chatMsgVO);
            return chatMsgVO;
        }).collect(Collectors.toList());
        return ResultVOUtil.success(chatMsgVOList);
    }
}
