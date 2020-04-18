package com.hwy.secretchat.controller;

import com.hwy.secretchat.enums.ResultEnum;
import com.hwy.secretchat.enums.SearchFriendStatusEnum;
import com.hwy.secretchat.exception.ReturnException;
import com.hwy.secretchat.model.entity.Friend;
import com.hwy.secretchat.model.entity.User;
import com.hwy.secretchat.model.bo.FriendRequestBO;
import com.hwy.secretchat.model.vo.FriendVO;
import com.hwy.secretchat.model.vo.ResultVO;
import com.hwy.secretchat.model.vo.UserVO;
import com.hwy.secretchat.service.FriendService;
import com.hwy.secretchat.service.UserService;
import com.hwy.secretchat.utils.ResultVOUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @program secret-chat
 * @author huangwenyu
 * @create 2020-03-16
 */
@RestController
@RequestMapping("friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

    /**
     * 查询好友列表REST接口
     * @param myId
     * @return
     * TODO 用WebSocket完成这个功能，实现好友列表自动刷新
     */
    @PostMapping("/getFriend")
    public ResultVO<List<FriendVO>> findAllFriends(@RequestParam("id") String myId) {

        if (StringUtils.isBlank(myId)) {
            throw new ReturnException(ResultEnum.PARAM_ERROR);
        }

        List<Friend> friendList = friendService.findAllFriends(myId);

        List<FriendVO> friendVOList;
        friendVOList = null;
        if (friendList != null && friendList.size() > 0) {
            friendVOList = friendList.stream().map(e -> {
                String friendId = e.getFriendId();
                User user = userService.findOneUserById(friendId);
                FriendVO friendVO = new FriendVO();
                if (user != null) {
                    BeanUtils.copyProperties(user, friendVO);
                } else {
                    return null;
                }
                return friendVO;
            }).collect(Collectors.toList());
            friendVOList = friendVOList.stream().filter(Objects::nonNull).collect((Collectors.toList()));
        }

        return ResultVOUtil.success(friendVOList);

    }

    /**
     * 查询好友
     * @param myId 本人用户id
     * @param friendUsername 好友用户名
     * @return
     */
    @PostMapping("/search")
    public ResultVO<UserVO> searchFriend(String myId, String friendUsername) {

        if (StringUtils.isBlank(friendUsername) || StringUtils.isBlank(myId)) {
            throw new ReturnException(ResultEnum.PARAM_ERROR);
        }

        // 前置条件 - 1. 搜索的用户如果不存在，返回[无此用户]
        // 前置条件 - 2. 搜索账号是你自己，返回[不能添加自己]
        // 前置条件 - 3. 搜索的朋友已经是你的好友，返回[该用户已经是你的好友]
        SearchFriendStatusEnum searchFriendStatusEnum = friendService.preconditionSearchFriend(myId, friendUsername);
        if (searchFriendStatusEnum != SearchFriendStatusEnum.SUCCESS) {
            throw new ReturnException(searchFriendStatusEnum);
        }
        User user = userService.findOneUserByUsername(friendUsername);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return ResultVOUtil.success(userVO);
    }

    /**
     * 发出好友请求
     * @param myId
     * @param friendUsername
     * @return
     */
    @PostMapping("/sendFriendRequest")
    public ResultVO<String> sendFriendRequest(String myId, String friendUsername) {

        if (StringUtils.isBlank(friendUsername) || StringUtils.isBlank(myId)) {
            throw new ReturnException(ResultEnum.PARAM_ERROR);
        }

        // 前置条件 - 1. 搜索的用户如果不存在，返回[无此用户]
        // 前置条件 - 2. 搜索账号是你自己，返回[不能添加自己]
        // 前置条件 - 3. 搜索的朋友已经是你的好友，返回[该用户已经是你的好友]
        SearchFriendStatusEnum searchFriendStatusEnum = friendService.preconditionSearchFriend(myId, friendUsername);
        if (searchFriendStatusEnum != SearchFriendStatusEnum.SUCCESS) {
            throw new ReturnException(searchFriendStatusEnum);
        }

        String result = friendService.sendFriendRequest(myId, friendUsername);
        return ResultVOUtil.success(result);
    }

    /**
     * 查询接收到的好友请求
     * @param myId
     * @return
     */
    @PostMapping("/searchFriendRequest")
    public ResultVO searchFriendRequest(String myId) {

        if (StringUtils.isBlank(myId)) {
            throw new ReturnException(ResultEnum.PARAM_ERROR);
        }

        return ResultVOUtil.success(friendService.findAllFriendRequests(myId));

    }

    /**
     * 处理好友请求
     * @param friendRequestBO
     * @return
     */
    @PostMapping("/operateFriendRequest")
    public ResultVO operateFriendRequest(@RequestBody FriendRequestBO friendRequestBO) {
        boolean result = friendService.operateFriendRequest(friendRequestBO.getSendUserId(), friendRequestBO.getReceiveUserId()
                , friendRequestBO.getOperateType());
        if (result) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("friend request error");
    }
}
