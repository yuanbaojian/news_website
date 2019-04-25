package com.ybj.news_website.controller;

import com.ybj.news_website.mapper.MbdealMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
public class MbdealController {
    @Autowired
    MbdealMapper mapper;
    @RequestMapping("/mbdeal")
    public ModelAndView getmbdeal() {
        ModelAndView mv = new ModelAndView();
        List list=mapper.getUserMbDeal();
        mv.addObject("alldeal",list);
        mv.setViewName("/app/mbdeal.html");
        return mv;
    }
    @RequestMapping("/mbhome")
    public ModelAndView getmbdealhome() {
        ModelAndView mv = new ModelAndView();
        List list=mapper.getMbDeal();
        mv.addObject("alldeal",list);
        mv.setViewName("/app/mbdealhome.html");
        return mv;
    }
    @RequestMapping("/addmoney")
    public ModelAndView addmoeny(@RequestParam Map map){
        ModelAndView mv = new ModelAndView();
        mapper.addmoney(map);
        mv.setViewName("/app/mbdealhome.html");
        return mv;
    }
}
