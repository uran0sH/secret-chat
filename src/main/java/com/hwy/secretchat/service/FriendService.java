package com.hwy.secretchat.service;

import com.hwy.secretchat.enums.SearchFriendStatusEnum;
import com.hwy.secretchat.pojo.Friend;
import com.hwy.secretchat.pojo.FriendRequest;

import java.util.List;

/**
 * @program secret-chat
 * @author huangwenyu
 * @create 2020-03-16
 */
public interface FriendService {

    /**
     * 查找所有好友
     * @param myId
     * @return
     */
    List<Friend> findAllFriends(String myId);


    boolean insertOneFriend(String friendId);

    /**
     * 查询好友之前的预查询
     * @param myId
     * @param friendUsername
     * @return
     */
    SearchFriendStatusEnum preconditionSearchFriend(String myId, String friendUsername);

    /**
     * 发出好友请求
     * @param myId
     * @param friendUsername
     * @return
     */
    String sendFriendRequest(String myId, String friendUsername);

    /**
     * 查询所有接收到的好友请求
     * @param myId
     * @return
     */
    List<FriendRequest> findAllFriendRequests(String myId);
}
