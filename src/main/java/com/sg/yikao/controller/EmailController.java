package com.sg.yikao.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.sg.yikao.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

/**
 * @Author Ssssg
 * @Description 发送邮箱验证码
 * @Date 2019/3/17 16:08
 **/

@Controller
@RequestMapping("/yikao")
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private DefaultKaptcha kaptcha;

    @GetMapping("/emailcaptcha")
    @ResponseBody
    public ServerResponse sendEmail(HttpSession session, String To, String[] Cc, String[] Bcc){

        if (session == null){
            return ServerResponse.createByErrorMessage("请求失败");
        }

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            // 设置发送方
            helper.setFrom("shguan.qj@qq.com");
            // 接受方不能为空
            if (To == null){
                return ServerResponse.createByErrorMessage("接受地址不能为空");
            }else {
                // 设置接收方
                helper.setTo(To);
            }
            if (Cc != null){
                // 设置抄送
                helper.setCc(Cc);
            }
            if (Bcc != null){
                // 设置密抄
                helper.setBcc(Bcc);
            }
            // 设置主题
            helper.setSubject("主题：验证码测试");
            // 生成验证码
            String eamilcode = kaptcha.createText();
            // 将验证码存进session
            session.setAttribute("emailcaptcha", eamilcode);
            // 设置内容
            message.setText("<h1>" + eamilcode + "<h1>", "utf-8", "html");
            // 发送
            mailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }


        return ServerResponse.createByErrorMessage("验证码已发送");
    }


    @PostMapping("/checkemailcaptcha")
    @ResponseBody
    public ServerResponse checkEmailCaptcha(@RequestParam("usecode") String userCode, @RequestParam("emailcaptcha") String emailCaptcha , HttpSession session){


        if (userCode.equals(emailCaptcha)){
            session.removeAttribute("emailcaptcha");
            return ServerResponse.createBySuccessMessage("验证成功");
        }

        return ServerResponse.createByErrorMessage("验证码输入错误");

    }

}