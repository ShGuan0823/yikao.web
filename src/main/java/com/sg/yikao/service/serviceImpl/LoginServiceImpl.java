package com.sg.yikao.service.serviceImpl;

import com.sg.yikao.dao.LoginDao;
import com.sg.yikao.entity.User;
import com.sg.yikao.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Ssssg
 * @Description TODO
 * @Date 2019/3/14 22:07
 **/

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Override
    public String getSaltByName(User user) {
        return loginDao.getSaltByName(user.getUsername());
    }

    @Override
    public String getPasswordByName(User user) {

        return loginDao.getPasswordByName(user.getUsername());
    }
}