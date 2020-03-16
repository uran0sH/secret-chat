package com.hwy.secretchat.pojo.mapper;

import com.hwy.secretchat.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户信息Mybatis接口
 */
public interface UserMapper {

    /**
     * 查询所用用户
     * @return 用户列表，未分页
     */
    @Select("select * from user")
    List<User> findAllUser();

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
    @Insert("insert into user(id, username, password, gender, personality, face_image, face_image_big) values(#{id}, " +
            "#{username}, #{password}, #{gender}, #{personality}, #{faceImage}, #{faceImageBig})")
    boolean insertOneUser(User user);

    @Select("select * from user where username = #{username}")
    User findOneUserByUsername(String username);
}
