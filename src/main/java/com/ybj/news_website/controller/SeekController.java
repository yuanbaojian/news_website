package com.ybj.news_website.controller;

import com.ybj.news_website.mapper.HomeMapper;
import com.ybj.news_website.mapper.SeekMapper;
import com.ybj.news_website.redis.JedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
public class SeekController {
    @Autowired
    SeekMapper seekMapper;
    @Autowired
    HomeMapper homeMapper;
    @Autowired
    JedisService jedisService;
    @RequestMapping("/getindexseek")
    public List<Map<String,Object>> getIndexSeek(String t_article_title_1){
        List seek=seekMapper.getIndexSeek(t_article_title_1);
        return seek;
    }
    @RequestMapping("/clickseek")
    public ModelAndView clickSeek(String keyword){
        ModelAndView mv=new ModelAndView();
        List<Map<String,Object>> articles=seekMapper.getArticle(keyword);
        mv.addObject("article",articles);
        //SY_TJGL
        List list=homeMapper.getHomeNode();
        List SY_GGGL=homeMapper.getTjgl("SY_GGGL");
        mv.setViewName("/app/seek");
        mv.addObject("recenodes",list);
        mv.addObject("SY_GGGL",SY_GGGL);
        //hotArticle
        Map hotArticle = new HashMap();
        List<Map<String,Object>> hotArticles=new ArrayList<>();
        Set<String> set = jedisService.zrevrange("hotArticles",0,8);
        for (String str : set){
            int t_article_id = Integer.parseInt(str.split(":")[1]);
            hotArticle = homeMapper.selectById(t_article_id);
            hotArticles.add(hotArticle);
        }
        mv.addObject("hotArticles",hotArticles);
        //
        return mv;
    }

    @RequestMapping(path = "/seekrecenode/{name}")
    public ModelAndView getReceNodeArticle(@PathVariable String name){
        ModelAndView mv=new ModelAndView();
        List<Map<String,Object>> articles=seekMapper.getReceNodeArticle(name);
        mv.addObject("article",articles);
        //SY_TJGL
        List list=homeMapper.getHomeNode();
        List SY_GGGL=homeMapper.getTjgl("SY_GGGL");
        mv.setViewName("/app/seek");
        mv.addObject("recenodes",list);
        mv.addObject("SY_GGGL",SY_GGGL);
        //hotArticle
        Map hotArticle = new HashMap();
        List<Map<String,Object>> hotArticles=new ArrayList<>();
        Set<String> set = jedisService.zrevrange("hotArticles",0,8);
        for (String str : set){
            int t_article_id = Integer.parseInt(str.split(":")[1]);
            hotArticle = homeMapper.selectById(t_article_id);
            hotArticles.add(hotArticle);
        }
        mv.addObject("hotArticles",hotArticles);
        //
        return mv;
    }



}


