package com.interview.youknow.web;

import com.interview.youknow.domain.entity.ProjectUser;
import com.interview.youknow.domain.repository.ProjectUserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private ProjectUserRepository projectUserRepository;

    @PostMapping("/login")
    public ModelAndView login (@ModelAttribute ProjectUser reqProjectUser, ModelAndView mav, HttpSession session) {

        if (reqProjectUser == null || reqProjectUser.getUserId() == null || reqProjectUser.getUserPw() == null) {
            mav.setViewName("redirect:/index");
            return mav;
        }

        ProjectUser projectUser = projectUserRepository.findByUserIdAndUserPw(reqProjectUser.getUserId(), DigestUtils.sha256Hex(reqProjectUser.getUserPw()));
        if (projectUser == null) {
            mav.setViewName("login_page");
            mav.addObject("result", "false");
            mav.addObject("resultMsg", "로그인 오류.\n아이디 또는 패스워드를 확인해주세요.");
            return mav;
        }

        session.setAttribute("projectUser", projectUser);
        mav.setViewName("redirect:/local/index");
        return mav;
    }
}
