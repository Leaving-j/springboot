package com.restful.filter;

import com.google.gson.Gson;
import com.restful.vo.LoginVo;
import com.restful.vo.ResultVo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: restfulproject
 * @description: description
 * @author: lw
 * @create: 2018-11-21 10:52
 **/
public class LoginFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
      /*  LoginVo vo = (LoginVo) request.getSession().getAttribute("loginUser");
        if (vo != null) {
            return true;
        } else {
            try {
                response.getWriter().print("{\n" +
                        "    \"code\": \"N\",\n" +
                        "    \"message\": \"please login\"\n" +
                        "}");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }*/
      return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println("---- 调用postHandle----");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("---- 调用afterCompletion----");

    }
}
