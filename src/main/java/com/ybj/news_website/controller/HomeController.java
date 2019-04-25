package com.ybj.news_website.controller;

import com.ybj.news_website.mapper.ArticleMapper;
import com.ybj.news_website.mapper.CommentMapper;
import com.ybj.news_website.mapper.HomeMapper;
import com.ybj.news_website.redis.JedisService;
import com.ybj.news_website.util.RedisKeyUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class HomeController {
    @Autowired
    public HomeMapper mapper;
    @Autowired
    private JedisService jedisService;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @RequestMapping(path = {"/","/index"})
    public ModelAndView getindex(){
        ModelAndView mv=new ModelAndView();
        List<Map<String,Object>> articles=mapper.getArticle(0,4);
        Map page=new HashMap();
        int count = mapper.getArticleCount();
        page.put("current",1);
        page.put("nextPage",2);
        page.put("lastPage",count/4+1);
        mv.addObject("page",page);
        mv.addObject("article",articles);
        //SY_TJGL
        List list=mapper.getHomeNode();
        List ZY_TPLB=mapper.getTplb("ZY_TPLB");
        List SY_TJGL=mapper.getTjgl("SY_TJGL");
        List SY_GGGL=mapper.getTjgl("SY_GGGL");
        mv.setViewName("/index");
        mv.addObject("recenodes",list);
        mv.addObject("ZY_TPLB",ZY_TPLB);
        mv.addObject("SY_TJGL",SY_TJGL);
        mv.addObject("SY_GGGL",SY_GGGL);

        Map clickCount=new HashMap();
        String countStr1 = jedisService.get(RedisKeyUntil.getClickCountKey("/"));
        String countStr2 = jedisService.get(RedisKeyUntil.getClickCountKey("/index"));
        String countStr3 = jedisService.get(RedisKeyUntil.getClickCountKey("/page/1"));
        String currentPage = String.valueOf(Integer.parseInt(countStr1==null?"0":countStr1)
                + Integer.parseInt(countStr2==null?"0":countStr2)+ Integer.parseInt(countStr3==null?"0":countStr3));
        clickCount.put("currentPage",currentPage);
        mv.addObject("clickCount",clickCount);

        //hotArticle
        Map hotArticle = new HashMap();
        List<Map<String,Object>> hotArticles=new ArrayList<>();
        Set<String> set = jedisService.zrevrange("hotArticles",0,8);
        for (String str : set){
            int t_article_id = Integer.parseInt(str.split(":")[1]);
             hotArticle = mapper.selectById(t_article_id);
             hotArticles.add(hotArticle);
        }
        mv.addObject("hotArticles",hotArticles);
        //
        return mv;
    }
    @RequestMapping(path = "/page/{pageId}")
    public ModelAndView article(@PathVariable("pageId")int pageId) {
        ModelAndView mv=new ModelAndView();
        List<Map<String,Object>> articles = mapper.getArticle((pageId - 1) * 4, 4);
        Map page=new HashMap();
        int count = mapper.getArticleCount();
        page.put("current", pageId);
        page.put("nextPage", pageId + 1);
        page.put("prePage", pageId - 1);
        page.put("lastPage", count / 4 + 1);
        mv.setViewName("/index.html");
        mv.addObject("article",articles);
        mv.addObject("page",page);
        List list=mapper.getHomeNode();
        List ZY_TPLB=mapper.getTplb("ZY_TPLB");
        List SY_TJGL=mapper.getTjgl("SY_TJGL");
        List SY_GGGL=mapper.getTjgl("SY_GGGL");
        mv.setViewName("/index");
        mv.addObject("recenodes",list);
        mv.addObject("ZY_TPLB",ZY_TPLB);
        mv.addObject("SY_TJGL",SY_TJGL);
        mv.addObject("SY_GGGL",SY_GGGL);

        Map clickCount=new HashMap();
        String countStr1 = jedisService.get(RedisKeyUntil.getClickCountKey("/"));
        String countStr2 = jedisService.get(RedisKeyUntil.getClickCountKey("/index"));
        String countStr3 = jedisService.get(RedisKeyUntil.getClickCountKey("/page/1"));
        String currentPage = String.valueOf(Integer.parseInt(countStr1==null?"0":countStr1)
                + Integer.parseInt(countStr2==null?"0":countStr2)+ Integer.parseInt(countStr3==null?"0":countStr3));
        clickCount.put("currentPage",currentPage);
        mv.addObject("clickCount",clickCount);
        //hotArticle
        Map hotArticle = new HashMap();
        List<Map<String,Object>> hotArticles=new ArrayList<>();
        Set<String> set = jedisService.zrevrange("hotArticles",0,8);
        for (String str : set){
            int t_article_id = Integer.parseInt(str.split(":")[1]);
            hotArticle = mapper.selectById(t_article_id);
            hotArticles.add(hotArticle);
        }
        mv.addObject("hotArticles",hotArticles);
        //
        return mv;

    }

    @RequestMapping("/article/{t_article_Id}")
    public ModelAndView moban (@PathVariable("t_article_Id")int t_article_Id, HttpServletRequest request){
        //传递的参数为文章的id，用id进行条件查询
        ModelAndView mv=new ModelAndView();
        List list=mapper.getHomeNode();
        //        评论
        List comment=commentMapper.getAllComment(t_article_Id);
        List comment2=commentMapper.getAllComment2(t_article_Id);
        List SY_GGGL=mapper.getTjgl("SY_GGGL");
        int i=comment2.size();
        Map article=articleMapper.queryIntArticleById(t_article_Id);
        mv.addObject("aid",t_article_Id);
        mv.addObject("comment",comment);
        mv.addObject("comment2",comment2);
        mv.addObject("article",article);
        mv.addObject("commentsize",i);
        mv.addObject("SY_GGGL",SY_GGGL);

        //
        mv.setViewName("/app/journalism.html");
        mv.addObject("recenodes",list);
        Map clickCount=new HashMap();
        String currentPage = jedisService.get(RedisKeyUntil.getClickCountKey("/article/"+t_article_Id));
        clickCount.put("currentPage",currentPage);
        mv.addObject("clickCount",clickCount);
        //hotArticle
        Map hotArticle = new HashMap();
        List<Map<String,Object>> hotArticles=new ArrayList<>();
        Set<String> set = jedisService.zrevrange("hotArticles",0,8);
        for (String str : set){
            int t_article_id = Integer.parseInt(str.split(":")[1]);
            hotArticle = mapper.selectById(t_article_id);
            hotArticles.add(hotArticle);
        }
        mv.addObject("hotArticles",hotArticles);
        //
//        HttpSession session = request.getSession(true);
//        Object user=session.getAttribute("user");
//        if(user!=null){
//            //不做操作
//        }else {
//            session.setAttribute("t_user","no");
//        }
        return mv;
    }

}
