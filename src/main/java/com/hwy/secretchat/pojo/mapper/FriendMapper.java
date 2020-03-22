package com.hwy.secretchat.pojo.mapper;

import com.hwy.secretchat.pojo.Friend;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 好友Mapper接口
 */
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
