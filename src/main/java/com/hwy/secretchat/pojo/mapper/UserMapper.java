package com.hwy.secretchat.pojo.mapper;

import com.hwy.secretchat.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: secret-chat
 * @author: huangwenyu
 * @create: 2020-03-23
 */
@Repository
public interface UserMapper {

    /**
     * 查询所用用户
     * @return 用户列表，未分页
     */
    @Select("select * from user")
    List<User> findAllUsers();

    /**
     * 通过Id查询用户信息
     * @param id
     * @return 查找到的用户
     */
    @Select("select * from user where id = #{id}")
    User findOneUserById(String id);

    /**
     * 插入一个用户
     * @param user
     * @return
     */
    @Insert("insert into user(id, username, password, gender, description, face_image, face_image_big) values(#{id}, " +
            "#{username}, #{password}, #{gender}, #{description}, #{faceImage}, #{faceImageBig})")
    boolean insertOneUser(User user);

    /**
     * 通过用户名查找一个用户
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")
    User findOneUserByUsername(String username);
}
