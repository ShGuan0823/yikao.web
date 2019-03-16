package com.sg.yikao.controller;

import com.google.code.kaptcha.Constants;
import com.sg.yikao.entity.User;
import com.sg.yikao.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author Ssssg
 * @Description 注册
 * @Date 2019/3/14 22:55
 **/

@Controller
@RequestMapping("/yikao")
public class RegisterController {


    @Autowired
    private RegisterService registerService;

    @GetMapping("/register")
    public String reigister(){
        return "signup";
    }

    @PostMapping("/toregister")
    public String registerUser(User user, @RequestParam("captcha") String captcha, HttpSession session) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        if (!captcha.equals(session.getAttribute(Constants.KAPTCHA_SESSION_KEY))){
            return "redirect:register";
        }

        registerService.RegisterUser(user);
        return "redirect:login";

    }

}