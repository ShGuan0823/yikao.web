package com.sg.yikao.controller;

import com.sg.yikao.common.ServerResponse;
import com.sg.yikao.config.NeedLogin;
import com.sg.yikao.entity.User;
import com.sg.yikao.service.LoginService;
import com.sg.yikao.util.MD5;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author Ssssg
 * @Description TODO
 * @Date 2019/3/14 19:08
 **/

@Controller
@RequestMapping("/yikao")
public class LoginController {

    private LoginService loginService;


    @GetMapping("/login")
    public String login(){
        return "signin";
    }



    @PostMapping("/loginVerify")
    public String loginVerify(User user, HttpSession session) throws UnsupportedEncodingException, NoSuchAlgorithmException {

//        if (user.getUsername().equals("admin") && user.getPassword().equals("admin")){
//            session.setAttribute("user", user);
//            return "index";
//        }
        String salt = loginService.getSaltByName(user);
        String pwd = MD5.getEncryptedPwd(user.getPassword(), salt);
        if(pwd.equals(loginService.getPasswordByName(user))){
            session.setAttribute("user", user);
            return "index";
        }
        return "redirect:login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:login";
    }


}