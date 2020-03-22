package com.hwy.secretchat.service.impl;

import com.hwy.secretchat.enums.SearchFriendStatusEnum;
import com.hwy.secretchat.pojo.Friend;
import com.hwy.secretchat.pojo.User;
import com.hwy.secretchat.pojo.mapper.FriendMapper;
import com.hwy.secretchat.service.FriendService;
import com.hwy.secretchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: secret-chat
 * @author: huangwenyu
 * @create: 2020-03-17
 */
@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<Friend> findAllFriends(String myId) {
        return friendMapper.findAllFriends(myId);
    }

    @Override
    public boolean insertOneFriend(String friendId) {
        return false;
    }

    @Override
    public SearchFriendStatusEnum preconditionSearchFriend(String myId, String friendUsername) {
        User user = userService.findOneUserByUsername(friendUsername);

        //用户不存在
        if (user == null) {
            return SearchFriendStatusEnum.USER_NOT_EXIST;
        }

        //查询到的用户是自己
        if (myId.equals(user.getId())) {
            return SearchFriendStatusEnum.NOT_YOURSELF;
        }

        //查询到的用户已经是自己的好友
        if (friendMapper.findOneFriend(myId, user.getId()) != null) {
            return SearchFriendStatusEnum.ALREADY_FRIEND;
        }

        return SearchFriendStatusEnum.SUCCESS;
    }


}
