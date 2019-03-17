package com.sg.yikao.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;


/**
 * @Author Ssssg
 * @Description TODO
 * @Date 2019/3/16 14:25
 **/

@Controller
@RequestMapping("/yikao")
public class CaptchaController {

    @Autowired
    private DefaultKaptcha kaptcha;

    @Bean
    public DefaultKaptcha initKaptcha(){
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.put(Constants.KAPTCHA_IMAGE_WIDTH, "140");
        properties.put(Constants.KAPTCHA_IMAGE_HEIGHT, "40");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "30");
        Config config = new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }


    @GetMapping("/captcha")
    public void getKaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        // Set to expire far in the past.
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (user addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Prama", "no-cache");

        // return a jpeg
        response.setContentType("image/png");

        // create the text for the image
        String capText = kaptcha.createText();
        // store the text in the session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        // create the image with the text
        BufferedImage bufferedImage = kaptcha.createImage(capText);
        // write the data out
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bufferedImage, "png", out);
        try {
            out.flush();
        } finally {
          out.close();
        }

    }

}