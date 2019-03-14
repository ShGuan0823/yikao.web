package com.sg.yikao.dao;

public interface LoginDao {

    String getPasswordByName(String username);

    String getSaltByName(String username);
}
