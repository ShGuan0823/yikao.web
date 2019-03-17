package com.sg.yikao.controller;

import com.sg.yikao.common.ServerResponse;
import com.sg.yikao.config.NeedLogin;
import com.sg.yikao.entity.User;
import com.sg.yikao.service.LoginService;
import com.sg.yikao.util.MD5util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private LoginService loginService;


    @GetMapping("/login")
    public String login(){
        return "signin";
    }

    @GetMapping("/index")
    @NeedLogin
    public String index(@RequestParam("usename") String username, Model model){
        model.addAttribute("username", username);
        return "index";
    }

    @PostMapping("/loginVerify")
    public String loginVerify(User user, HttpSession session) throws UnsupportedEncodingException, NoSuchAlgorithmException {

//        if (user.getUsername().equals("admin") && user.getPassword().equals("admin")){
//            session.setAttribute("user", user);
//            return "index";
//        }
        if (user == null){
            return "redirect:login";
        }
        String salt = loginService.getSaltByName(user);
        String pwd;
        if (salt != null){
            pwd = MD5util.getEncryptedPwd(user.getPassword(), salt);
            if(pwd.equals(loginService.getPasswordByName(user))){
                session.setAttribute("user", user);
//                this.index(user.getUsername())
                return "index";
            }
        }
        return "redirect:login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:login";
    }


}