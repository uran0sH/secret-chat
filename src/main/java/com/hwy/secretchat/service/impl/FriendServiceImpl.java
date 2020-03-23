package com.hwy.secretchat.service.impl;

import com.hwy.secretchat.enums.FriendRequestStatusEnum;
import com.hwy.secretchat.enums.SearchFriendStatusEnum;
import com.hwy.secretchat.model.entity.Friend;
import com.hwy.secretchat.model.entity.FriendRequest;
import com.hwy.secretchat.model.entity.User;
import com.hwy.secretchat.model.mapper.FriendMapper;
import com.hwy.secretchat.model.mapper.FriendRequestMapper;
import com.hwy.secretchat.service.FriendService;
import com.hwy.secretchat.service.UserService;
import com.hwy.secretchat.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @program secret-chat
 * @author huangwenyu
 * @create 2020-03-17
 */
@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private FriendRequestMapper friendRequestMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<Friend> findAllFriends(String myId) {
        return friendMapper.findAllFriends(myId);
    }

    @Override
    public boolean insertOneFriend(String myId, String friendId) {
        Friend friend = new Friend();
        friend.setId(KeyUtil.genUniqueKey());
        friend.setMyId(myId);
        friend.setFriendId(friendId);
        return friendMapper.insertOneFriend(friend);
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

    @Override
    public String sendFriendRequest(String myId, String friendUsername) {
        User friend = userService.findOneUserByUsername(friendUsername);
        FriendRequest friendRequest = friendRequestMapper.findFriendRequest(myId, friend.getId());
        if (friendRequest == null) {
            FriendRequest request = new FriendRequest();
            String id = KeyUtil.genUniqueKey();
            request.setId(id);
            request.setSendUserId(myId);
            request.setReceiveUserId(friend.getId());
            request.setRequestDateTime(LocalDateTime.now());
            request.setIsAccept(FriendRequestStatusEnum.UNPROCESSED.getCode());
            if(friendRequestMapper.insertFriendRequest(request)) {
                return id;
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public List<FriendRequest> findAllFriendRequests(String myId) {
        return friendRequestMapper.findReceiveFriendRequest(myId);
    }

    @Override
    public boolean operateFriendRequest(String sendUserId, String receiveUserId, Integer operateType) {
        boolean result = friendRequestMapper.updateFriendRequestStatus(sendUserId, receiveUserId, operateType);
        if (operateType.equals(FriendRequestStatusEnum.ACCEPTED.getCode()) && result) {
                return insertOneFriend(sendUserId, receiveUserId) & insertOneFriend(receiveUserId, sendUserId);
        }
        return false;
    }

}
