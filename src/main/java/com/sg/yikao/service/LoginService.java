package com.sg.yikao.service;

import com.sg.yikao.entity.User;

/**
 * @Author Ssssg
 * @Description TODO
 * @Date 2019/3/14 22:07
 **/


public interface LoginService {

    String getSaltByName(User user);

    String getPasswordByName(User user);

}