package com.hwy.secretchat.model.mapper;

import com.hwy.secretchat.model.entity.ChatMsg;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author huangwenyu
 * @program secret-chat
 * @create 2020-03-30
 */
@Repository
public interface ChatMsgMapper {

    /**
     * 插入一条消息到数据库
     * @param chatMsg
     * @return
     */
    @Insert("insert into chat_msg(id, send_user_id, receive_user_id, msg, sign_flag, create_time) values(#{id}, " +
            "#{sendUserId}, #{receiveUserId}, #{msg}, #{signFlag}, #{createTime})")
    boolean insertOneChatMsg(ChatMsg chatMsg);

    /**
     * 批量更新为签收
     * @param msgIdList
     * @return
     */
    @Update({"<script>" +
    "update chat_msg set sign_flag = 1 where id in" +
    "<foreach collection='msgIdList' item='item' index='index' open='(' separator=',' close=')'>" +
    "#{item}" +
    "</foreach>" +
    "</script>"})
    boolean batchUpdateMsgSigned(@Param("msgIdList") List<String> msgIdList);

    /**
     * 查询历史记录
     * @param sendUserId
     * @param receiveUserId
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("select * from chat_msg where (send_user_id = #{sendUserId} and receive_user_id = #{receiveUserId} " +
            "or send_user_id = #{receiveUserId} and receive_user_id = #{sendUserId}) and create_time between #{startTime}" +
            "and #{endTime}")
    List<ChatMsg> getHistory(String sendUserId, String receiveUserId, LocalDateTime startTime, LocalDateTime endTime);
}
