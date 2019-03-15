package com.sg.yikao.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginDao {

    String getPasswordByName(@Param("username") String username);

    String getSaltByName(@Param("username") String username);
}
