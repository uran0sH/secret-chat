package com.hwy.secretchat.model.mapper;

import com.hwy.secretchat.model.entity.FriendRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program secret-chat
 * @author huangwenyu
 * @create 2020-03-23
 */
@Repository
public interface FriendRequestMapper {

    /**
     * 查询发出的好友请求
     * @param sendUserId
     * @return
     */
    @Select("select * from friend_request where send_user_id = #{sendUserId}")
    List<FriendRequest> findSendFriendRequest(String sendUserId);

    /**
     * 查询接收的好友请求
     * @param receiveUserId
     * @return
     */
    @Select("select * from friend_request where receive_user_id = #{receiveUserId}")
    List<FriendRequest> findReceiveFriendRequest(String receiveUserId);

    /**
     * 查询一条好友请求
     * @param sendUserId
     * @param receiveUserId
     * @return
     */
    @Select("select * from friend_request where send_user_id = #{sendUserId} and receive_user_id = #{receiveUserId}")
    FriendRequest findFriendRequest(String sendUserId, String receiveUserId);

    /**
     * 插入一条好友请求
     * @param friendRequest
     * @return
     */
    @Insert("insert into friend_request(id, send_user_id, receive_user_id, is_accept, request_date_time) values(#{id}, " +
            "#{sendUserId}, #{receiveUserId}, #{isAccept}, #{requestDateTime})")
    boolean insertFriendRequest(FriendRequest friendRequest);

    /**
     * 更新请求状态
     * @param sendUserId
     * @param receiveUserId
     * @param operateType
     * @return
     */
    @Update("update friend_request set is_accept = #{operateType} where send_user_id = #{sendUserId} " +
            "and receive_user_id = #{receiveUserId}")
    boolean updateFriendRequestStatus(String sendUserId, String receiveUserId, Integer operateType);
}
