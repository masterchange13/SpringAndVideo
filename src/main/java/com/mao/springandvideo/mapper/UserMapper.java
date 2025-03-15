package com.mao.springandvideo.mapper;

import com.mao.springandvideo.dao.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from users where username= #{username} and password = #{password}")
    public User selectUserByUser(User user);

    @Select("select * from users")
    List<User> getUsers();
}
