package com.sg.yikao.dao;

import com.sg.yikao.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface RegisterDao {

    void RegisterUser(User user);

    int getUser(@Param("username") String username);
}
