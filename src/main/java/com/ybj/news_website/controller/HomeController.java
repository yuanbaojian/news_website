package com.ybj.news_website.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class HomeController {

    @RequestMapping("/")
    public  String index()
    {
        return "index.html";
    }
}
