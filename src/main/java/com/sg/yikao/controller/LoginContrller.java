package com.sg.yikao.controller;

import com.sg.yikao.common.ServerResponse;
import com.sg.yikao.config.NeedLogin;
import com.sg.yikao.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Author Ssssg
 * @Description TODO
 * @Date 2019/3/14 19:08
 **/

@Controller
@RequestMapping("/yikao")
public class LoginContrller {


    @GetMapping("/login")
    public String login(){
        return "signin";
    }



    @PostMapping("/loginVerify")
//    @NeedLogin
    public String loginVerify(User user, HttpSession session){

        if (user.getUsername().equals("admin") && user.getPassword().equals("admin")){
            session.setAttribute("user", user);
            return "index";
        }
        return "redirect:login";
    }
}