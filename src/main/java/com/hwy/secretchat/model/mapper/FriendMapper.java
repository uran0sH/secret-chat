package com.hwy.secretchat.model.mapper;

import com.hwy.secretchat.model.entity.Friend;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program secret-chat
 * @author huangwenyu
 * @create 2020-03-23
 */
@Repository
public interface FriendMapper {

    /**
     * 查询用户所有的好友
     * @param myId
     * @return
     */
    @Select("select * from friend where my_id = #{myId}")
    List<Friend> findAllFriends(String myId);

    /**
     * 插入一条好友记录
     * @param friend
     * @return
     */
    @Insert("insert into friend(id, my_id, friend_id) values(#{id}, #{myId}, #{friendId})")
    boolean insertOneFriend(Friend friend);

    /**
     * 删除好友
     * @param friend
     * @return
     */
    @Delete("delete from friend where my_id = #{myId} and friend_id = #{friendId}")
    boolean deleteOneFriend(Friend friend);

    /**
     * 查询用户的一个好友
     * @param myId
     * @param friendId
     * @return
     */
    @Select("select * from friend where my_id = #{myId} and friend_id = #{friendId}")
    Friend findOneFriend(String myId, String friendId);
}
