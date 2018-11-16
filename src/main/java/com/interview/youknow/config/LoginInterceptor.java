package com.interview.youknow.config;

import com.interview.youknow.domain.entity.ProjectUser;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        ProjectUser user = (ProjectUser) session.getAttribute("projectUser");
        if (user == null) {
//            response.sendRedirect("/index");
//            return false;
        }
        return true;
    }
}
