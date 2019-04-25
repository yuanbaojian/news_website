package com.ybj.news_website.controller;

import com.ybj.news_website.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ArticleController {

    @Autowired
    ArticleMapper mapper;

    @RequestMapping("/tuwenshuju")
    public ModelAndView getArticles(HttpSession session){
        ModelAndView mv=new ModelAndView();
        String t_user_account=session.getAttribute("t_user").toString();
        List article=mapper.getUserAllArticles(t_user_account);
        mv.addObject("allarticle",article);
        mv.setViewName("/app2/article.html");
        return mv;
    }
    @RequestMapping("/wendangguanli")
    public ModelAndView xw_getArticles(){
        ModelAndView mv=new ModelAndView();
        List article=mapper.getAllArticles();
        mv.addObject("allarticle",article);
        mv.setViewName("/app2/xw_article.html");
        return mv;
    }
    @RequestMapping("/newxwarticle")
    public ModelAndView insertArticle(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("/edit/xwedit.html");
        return mv;
    }

    @RequestMapping("/deletearticle")
    public ModelAndView deleteArticle(String t_article_id){
        ModelAndView mv=new ModelAndView();
        if (mapper.deleteById(t_article_id)){
            List article=mapper.getAllArticles();
            mv.addObject("allarticle",article);
            String success="删除成功！";
            mv.addObject("success",success);
            mv.setViewName("/app2/article.html");

        }else {
            List article=mapper.getAllArticles();
            mv.addObject("allarticle",article);
            mv.setViewName("/app/article.html");
            String error="删除失败？？？";
            mv.addObject("error",error);
        }
        return mv;
    }


    @RequestMapping("/updatearticle")
    public ModelAndView updateArticle(String t_article_id){
        ModelAndView mv=new ModelAndView();
        List<Map<String,Object>> article=mapper.queryArticleById(t_article_id);
        mv.addObject("updatearticle",article.get(0));
        mv.setViewName("/app/updatearticle.html");
        return mv;
    }

    @RequestMapping("/editarticle")
    public ModelAndView updateArticle(@RequestParam Map map){
        ModelAndView mv=new ModelAndView();
        mapper.editArticle(map);
        List article=mapper.getAllArticles();
        mv.addObject("allarticle",article);
        mv.setViewName("/app/article.html");
        return mv;
    }

    @RequestMapping("/savearticle")
    public ModelAndView saveArticle(String t_article_title_1,
                                    String t_article_title_2,
                                    String t_article_title_3,
                                    String t_article_source,
                                    String t_article_author,
                                    String t_article_create_date,
                                    String t_article_img1,
                                    String t_article_img2,
                                    String t_article_img3,
                                    String t_article_img4,
                                    String t_article_img5,
                                    String t_article_document,
                                    String t_article_classification,
                                    HttpSession session){
        String t_user_account=session.getAttribute("t_user").toString();
        ModelAndView mv=new ModelAndView();
        mapper.insertArticle(t_article_title_1,t_article_title_2,t_article_title_3,t_article_source,t_article_author,t_article_create_date,t_article_img1,t_article_img2,t_article_img3,t_article_img4,t_article_img5,t_article_document,t_article_classification,t_user_account);
        List article=mapper.getAllArticles();
        mv.addObject("allarticle",article);
        mv.setViewName("/edit/edit.html");
        return mv;
    }
    @RequestMapping("/xwsavearticle")
    public ModelAndView xwsaveArticle(String t_article_title_1,
                                      String t_article_title_2,
                                      String t_article_title_3,
                                      String t_article_source,
                                      String t_article_author,
                                      String t_article_create_date,
                                      String t_article_img1,
                                      String t_article_img2,
                                      String t_article_img3,
                                      String t_article_img4,
                                      String t_article_img5,
                                      String t_article_document,
                                      String t_article_classification,
                                      String t_classification_code){
        ModelAndView mv=new ModelAndView();
        mapper.xwinsertArticle(t_article_title_1,t_article_title_2,t_article_title_3,t_article_source,t_article_author,t_article_create_date,t_article_img1,t_article_img2,t_article_img3,t_article_img4,t_article_img5,t_article_document,t_article_classification,t_classification_code);
        List article=mapper.getAllArticles();
        mv.addObject("allarticle",article);
        mv.setViewName("/app2/xw_article.html");
        return mv;
    }
}
