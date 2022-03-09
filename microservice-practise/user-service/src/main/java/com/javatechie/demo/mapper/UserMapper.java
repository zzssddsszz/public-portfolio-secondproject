package com.javatechie.demo.mapper;

import com.javatechie.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user_table")
    List<User> getUserInfo();
}