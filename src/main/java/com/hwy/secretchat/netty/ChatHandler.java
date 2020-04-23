package com.hwy.secretchat.netty;

import com.google.gson.Gson;
import com.hwy.secretchat.SpringUtil;
import com.hwy.secretchat.enums.MsgActionEnum;
import com.hwy.secretchat.model.entity.ChatMsg;
import com.hwy.secretchat.service.ChatMsgService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huangwenyu
 * @program secret-chat
 * @create 2020-03-29
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    public static ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        //发送过来的消息
        String content = textWebSocketFrame.text();
        //对应的channel
        Channel currentChannel = channelHandlerContext.channel();

        Gson gson = new Gson();
        DataContent dataContent = gson.fromJson(content, DataContent.class);
        Integer action = dataContent.getAction();

        //TODO if结构优化
        //根据动作类型处理相应的事
        if (action.equals(MsgActionEnum.CONNECT.getType())) {
            //第一次连接将用户id和channel关联
            String senderId = dataContent.getMessage().getSendUserId();
            //判断是否有相同的用户名在两个地方登陆
            //如果有强制前一个用户下线
            Channel preChannel = UserChannelRel.get(senderId);
            if (preChannel != null) {
                Channel channel = users.find(preChannel.id());
                if (channel != null) {
                    //发送强制下线消息
                    DataContent tempDataContent = new DataContent();
                    tempDataContent.setAction(MsgActionEnum.FORCE_OFFLINE.getType());
                    channel.writeAndFlush(new TextWebSocketFrame(gson.toJson(tempDataContent)));
                    //关闭channel
                    channel.close();
                }
            }
            //绑定新的用户channel
            UserChannelRel.put(senderId, currentChannel);
            UserChannelRel.output();
            //将还未发送的消息发送
            ChatMsgService chatMsgService = (ChatMsgService)SpringUtil.getBean("chatMsgServiceImpl");
            List<ChatMsg> resultList = chatMsgService.getUnsentMessage(senderId);
            for (ChatMsg chatMsg : resultList) {
                Message message = new Message();
                message.setMsgId(chatMsg.getId());
                message.setSendUserId(chatMsg.getSendUserId());
                message.setReceiveUserId(chatMsg.getReceiveUserId());
                message.setMsg(chatMsg.getMsg());
                boolean result = sendMessage(message, MsgActionEnum.CHAT_UNENCRYPTED.getType());
                if (result) {
                    chatMsgService.updateSendStateToSuccess(chatMsg.getId());
                }
            }
        } else if (action.equals(MsgActionEnum.CHAT_UNENCRYPTED.getType())) {
            Message message = dataContent.getMessage();
            //将这条消息保存到数据库
            ChatMsgService chatMsgService = (ChatMsgService)SpringUtil.getBean("chatMsgServiceImpl");
            String msgId = chatMsgService.saveOneMsg(message);
            message.setMsgId(msgId);
            //发送消息
            boolean result = sendMessage(message, MsgActionEnum.CHAT_UNENCRYPTED.getType());
            if (result) {
                chatMsgService.updateSendStateToSuccess(msgId);
            }
        } else if (action.equals(MsgActionEnum.SIGN.getType())) {
            ChatMsgService chatMsgService = (ChatMsgService)SpringUtil.getBean("chatMsgServiceImpl");
            //签收的消息Id存放在扩展字段，用,分割
            String msgId = dataContent.getExtend();
            String[] msgIds = msgId.split(",");
            //去除null
            List<String> msgList = Arrays.stream(msgIds).filter(StringUtils::isNotBlank).collect(Collectors.toList());
            if(!msgList.isEmpty()) {
                chatMsgService.updateMsgSigned(msgList);
            }
        } else if (action.equals(MsgActionEnum.KEEP_ALIVE.getType())) {
            //调试
            System.out.println(currentChannel + "心跳包");
        } else if (action.equals(MsgActionEnum.CHAT_ENCRYPTED.getType())) {
            //发送消息
            Message message = dataContent.getMessage();
            sendMessage(message, MsgActionEnum.CHAT_ENCRYPTED.getType());
        }

    }

    private boolean sendMessage(Message message, Integer type) {
        //生成消息
        DataContent dataContent = new DataContent();
        dataContent.setMessage(message);
        dataContent.setAction(type);
        //发送消息
        String receiverId = message.getReceiveUserId();
        Channel receiveChannel = UserChannelRel.get(receiverId);
        if (receiveChannel == null) {
            //TODO 用户不在线进行推送
            return false;
        } else {
            Channel channel = users.find(receiveChannel.id());
            if (channel == null) {
                //TODO 用户掉线进行推送
                return false;
            } else {
                Gson gson = new Gson();
                channel.writeAndFlush(new TextWebSocketFrame(gson.toJson(dataContent)));
                return true;
            }
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        users.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        users.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //发生异常之后关闭连接（关闭channel）并从ChannelGroup中移除
        ctx.channel().close();
        users.remove(ctx.channel());
    }
}
