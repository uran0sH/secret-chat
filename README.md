# Secret Chat后端
* 所用的框架：SpringBoot, Netty

# API
## 登录
* url: `account/login`
* 请求 json: 
```
{
    "username": "123232",
    "password": "132213",
    "publicKey": ""
}
```
* 返回json:   
成功
```
{
    "code": 0,
    "msg": "success",
    "data": {
        "id": "brsmsg_1584278438707597686",
        "username": "18571549923",
        "faceImage": null,
        "description": null
    }
}
```
失败
```
{
    "timestamp": "2020-03-15 21:25:07",
    "status": 500,
    "error": "Internal Server Error",
    "message": "password or username error",//主要解析这一行
    "path": "/account/login"
}
```
## 注册
* url: `account/register`
* 请求
```
{
    "username": "123232",
    "password": "132213"
}
```
* 返回  
成功
```
{
    "code": 0,
    "msg": "success",
    "data": "brsmsg_1584278438707597686"
}
```
失败
```
{
    "timestamp": "2020-03-15 21:25:07",
    "status": 500,
    "error": "Internal Server Error",
    "message": "password or username error",//主要解析这一行
    "path": "/account/login"
}
```
## 联系人
* url: `friend/getFriend`
* 请求：
请求方法 POST(非JSON)
```
id = 登录用户的id 
```
* 返回：
```
{
    "code": 0,
    "msg": "success",
    "data": [
        {
            “username”: //联系人用户名 ,
            “faceImage”: //头像url,			
            “description”: //个性签名,
            "publicKey":
        }
    ]
}
```
样例
```
{
    "code": 0,
    "msg": "success",
    "data": [
        {
            "username": "15172382300",
            "faceImage": null,
            "description": null
        },
        {
            "username": "18571549924",
            "faceImage": null,
            "description": null
        },
        {
            "username": "18571549920",
            "faceImage": null,
            "description": null
        },
        {
            "username": "18571549921",
            "faceImage": null,
            "description": null
        }
    ]
}
```
## 查询好友
* url: `friend/search`
* 请求
请求方法 POST(非JSON)
请求参数
```
myId = brsmsg_1584278438707597686
friendUsername = 18571549925
```
* 返回
成功
```
{
    "code": 0,
    "msg": "success",
    "data": {
        "id": "brsmsg_1584881024758574067",
        "username": "18571549925",
        "faceImage": null,
        "description": null
    }
}
```
失败
```
{
    "timestamp": "2020-03-22 20:49:33",
    "status": 500,
    "error": "Internal Server Error",
    "message": "You can't add yourself as a friend." | "User doesn't exist." | "You can't add yourself as a friend." | "This user has been your friend.",
    "path": "/contact/search"
}
```
## 查询接收到的好友请求
* url: `friend/searchFriendRequest`
* 请求
请求方法 POST(非JSON)
请求参数
```
myId = brsmsg_1584278438707597686
```
* 返回
成功
```
{
    "code": 0,
    "msg": "success",
    "data": [
        {
            "id": "1584939826406769446",
            "sendUserId": "brsmsg_1584278438707597686",
            "username":,
            "receiveUserId": "brsmsg_1584881024758574067",
            "isAccept": 0,
            "requestDateTime": "2020-03-23T13:03:46"
        }
    ]
}
```
## 发出好友请求
* url: `friend/sendFriendRequest`
* 请求
请求方法 POST(非JSON)
请求参数
```
myId = brsmsg_1584278438707597686
friendUsername = 18571549925
```
* 返回
成功
```
{
    "code": 0,
    "msg": "success",
    "data": brsmsg_1584278438707597686
}
```
## 处理好友请求
* url: `friend/operateFriendRequest`
* 请求 JSON
```
{
	"myId": "brsmsg_1584881024758574067",
	"friendId": "brsmsg_1584278438707597686",
	"operateType": 1 #1同意 2拒绝
}
```
* 返回
```
{
    "code": 0,
    "msg": "success",
    "data": null
}
```
## 聊天
* WebSocket路由 ws://localhost:8081/ws
* 请求JSON
```json
{
  "action": 1,
  "message": {
    "sendUserId": "",
    "receiveUserId": "",
    "msg": "",
    "msgId": ""
  },
  "extend": "用于存放签收的Id"
}
```
动作类型  
connect: 1
chat: 2
sign: 3
keep_alive: 4
pull_friend: 5
## 更新头像
* url: `account/update/portrait`
* 请求 非json
```
"id": "brsmsg_1584881024758574067",
"portrait": "brsmsg_1584278438707597686",
```
* 返回
```
{
    "code": 0,
    "msg": "success",
    "data": true/false
}
```
## 更新用户名
* url: `account/update/username`
* 请求 非json
```
"id": "brsmsg_1584881024758574067",
"username": "brsmsg_1584278438707597686",
```
* 返回
```
{
    "code": 0,
    "msg": "success",
    "data": true/false
}
```
## 更新密码
* url: `account/update/password`
* 请求 非json
```
"id": "brsmsg_1584881024758574067",
"oldPassword": "brsmsg_1584278438707597686",
"newPassword":
```
* 返回
```
{
    "code": 0,
    "msg": "success",
    "data": true/false
}
```
错误
```json
{
    "code": 1,
    "msg": "错误信息"
}
```
## 获取聊天记录
* url: `message/history`
* 请求 非json
```
myId
receiverId
```
* 返回
```json
{
    "code": 0,
    "msg": "success",
    "data": [
        {
            "sendUserId": "brsmsg_1586334335390388232",
            "receiveUserId": "brsmsg_1584278438707597686",
            "msg": "cPK/h1LEslS1VJXZvUeNj+XP33236akKcnWGeRr7XvdnAfy8tYXSI/NekuQF83NoAbioBAnX8xcl\njB/Tf979QIj9UBnm2tMN5vJIbuT/Xu5hL322k/yuMDftCzUHqHaPCJiT4lWBX3q0mp/bb4XbCqcA\n/TumvxjwenVMzNTRndQ=\n",
            "createTime": "2020-04-17T11:55:06"
        },
        {
            "sendUserId": "brsmsg_1586334335390388232",
            "receiveUserId": "brsmsg_1584278438707597686",
            "msg": "NfuxIkufn2PAvSE+AC0dUo7RExmgC0/AGLnTGzR2Tf57g3y2FdPU5e61paCESBNB4+Y7fJOl197s\nP5P/FOLB+ybdk0ttOkJow/FMpaQNlnjUmZCfTjlW3+zfT0peC1txEQCVrXn7L7AuI7B2mipgMZoq\nhXJ25wl67Y9laxkeqKI=\n",
            "createTime": "2020-04-20T20:11:28"
        },
        {
            "sendUserId": "brsmsg_1586334335390388232",
            "receiveUserId": "brsmsg_1584278438707597686",
            "msg": "ez+SHcsU2gFWfas5kEr45Nrtb0qCsGQr3qEi44fjNTv9tDZS5PymqNkL2ReEPeVBGG0wSZ3wlYPR\nIkyxqhmv6X7/MNIcnS8muO9jp178gd3l8BzPHlQf2Aa4hfcpyowsX25rOW0l/5u/07FK/NvAq7ke\nrd8GpvTrXCiWAU7qOHo=\n",
            "createTime": "2020-04-20T20:11:36"
        }
    ]
}
```
