package com.sg.yikao.service.serviceImpl;

import com.sg.yikao.dao.LoginDao;
import com.sg.yikao.dao.RegisterDao;
import com.sg.yikao.entity.User;
import com.sg.yikao.service.RegisterService;
import com.sg.yikao.util.MD5util;
import com.sg.yikao.util.UUIDutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @Author Ssssg
 * @Description TODO
 * @Date 2019/3/14 23:13
 **/

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterDao registerDao;



    @Override
    public String RegisterUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//        int isExist = getUser(user);
//        System.out.println("isExit" + isExist);
        String salt = UUIDutil.getUUID();
        user.setSalt(salt);
        String pwd = MD5util.getEncryptedPwd(user.getPassword(),salt);
        user.setPassword(pwd);
        registerDao.RegisterUser(user);
        return "success";
    }

    @Override
    public int getUser(User user) {
        int result = registerDao.getUser(user.getUsername());
        return result;
    }
}