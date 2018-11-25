package com.interview.youknow.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/")
    public ModelAndView base () {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/index");

        return mav;
    }

    @GetMapping("/index")
    public ModelAndView index (ModelAndView mav) {
        mav.setViewName("login_page");

        return mav;
    }
}
