package com.sg.yikao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Ssssg
 * @Description TODO
 * @Date 2019/3/14 20:19
 **/

@Configuration
public class LoginWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired AuthHanderInterceptor authHanderInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截配置
        registry.addInterceptor(authHanderInterceptor).addPathPatterns("/**");
    }
}