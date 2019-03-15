package com.sg.yikao.service;

import com.sg.yikao.entity.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author Ssssg
 * @Description TODO
 * @Date 2019/3/14 23:12
 **/


public interface RegisterService {

    String RegisterUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    int getUser(User user);

}