package com.ybj.news_website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UserController {


    //去往登陆页面
    @RequestMapping("/tologin")
    public String toLogin(){
        return "app/login.html";
    }




}
