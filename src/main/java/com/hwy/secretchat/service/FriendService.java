package com.hwy.secretchat.service;

import com.hwy.secretchat.enums.SearchFriendStatusEnum;
import com.hwy.secretchat.pojo.Friend;

import java.util.List;

/**
 * @program: secret-chat
 * @author: huangwenyu
 * @create: 2020-03-16
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
}
