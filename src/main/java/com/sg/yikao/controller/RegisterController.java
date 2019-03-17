package com.sg.yikao.controller;

import com.google.code.kaptcha.Constants;
import com.sg.yikao.entity.User;
import com.sg.yikao.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.apache.commons.lang3.StringUtils;


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
    public ModelAndView reigister(@ModelAttribute("userForm")User user){
        ModelAndView view = new ModelAndView();
        view.setViewName("signup");
        return view;
    }

    @PostMapping("/toregister")
    public RedirectView registerUser(User user, @RequestParam("captcha") String captcha, HttpSession session, RedirectAttributes attributes)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        RedirectView view = new RedirectView();
        // 判空
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword()) || StringUtils.isBlank(user.getMail())){
            attributes.addFlashAttribute("userForm", user);
            view.setUrl("/yikao/register");
        }else {
            registerService.RegisterUser(user);
            view.setUrl("/yikao/login");
        }
//        if (!captcha.equals(session.getAttribute(Constants.KAPTCHA_SESSION_KEY))){
//            view.setUrl("/register");
//        }

        return view;

    }

}