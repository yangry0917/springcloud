package com.cloud.oauth2authserver.controller;


import com.alibaba.fastjson.JSONObject;
import com.cloud.oauth2authserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MainController {

    @Autowired
    UserDetailsService userDetailsServiceImpl;

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public /*String*/ModelAndView login() {
        //UserDetails user  = myUserDetailsServiceImpl.loadUserByUsername("admin");
        return /*"login"*/new ModelAndView("login");
    }

    @RequestMapping("/login-error")
    public /*String*/ModelAndView  loginError(Model model) {
        model.addAttribute( "loginError"  , true);
        model.addAttribute("errorMsg", "登陆失败，账号或者密码错误！");
        return /*"login-error"*/new ModelAndView("login", "userModel", model);
    }

    @GetMapping("/401")
    public String accessDenied() {
        return "401";
    }

    @GetMapping("/user/common")
    public String common() {
        return "user/common";
    }

    @GetMapping("/user/admin")
    public String admin() {
        return "user/admin";
    }


}
