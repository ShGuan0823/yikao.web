package com.sg.yikao.config;

import com.sg.yikao.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Author Ssssg
 * @Description TODO
 * @Date 2019/3/14 20:13
 **/

@Component
public class AuthHanderInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            NeedLogin needLogin = handlerMethod.getMethodAnnotation(NeedLogin.class);
            if (needLogin != null){
                User user = (User) request.getSession().getAttribute("user");
                if (user == null){
                    response.sendRedirect("/signin");
                    return false;
                }
            }
        }
        return true;

    }

}