# Secret Chat后端
* 所用的框架：SpringBoot, Netty

# API
## 登录
* url: `account/login`
* 请求 json: 
```
{
    "username": "123232",
    "password": "132213"
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
            “username” //联系人用户名 ,
            “faceImage” //头像url,			
            “description” //个性签名
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
            "receiveUserId": "brsmsg_1584881024758574067",
            "isAccept": 0,
            "requestDateTime": "2020-03-23T13:03:46"
        }
    ]
}
```
## 发出好友请求
* url: `friend/searchFriendRequest`
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
