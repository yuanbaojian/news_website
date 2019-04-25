package com.ybj.news_website.controller;

import com.ybj.news_website.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CommentController {
    @Autowired
    CommentMapper mapper;
    @RequestMapping("/insertcomment")
    public void addComment(@RequestParam Map map){
        mapper.insertComment(map);
//        Map map1=new HashMap();
//        map1.put("error","success");
//        return map1;
    }
    @RequestMapping("/insertcomment2")
    public void addComment2(@RequestParam Map map){
        mapper.insertComment2(map);
//        Map map1=new HashMap();
//        map1.put("error","success");
//        return map1;
    }
    @RequestMapping("/comcount")
    public List countComment(){
        List list = mapper.countAllComment();
        return list;
    }
    @RequestMapping("/comcount2")
    public List countComment2(){
        List list=mapper.countAllComment2();
        return list;
    }
    @RequestMapping("/usercomcount")
    public Map UserCountComment(String account){
        Map map=new HashMap();
        int  count= mapper.UserComCount(account);
        map.put("comcount",count);
        return map;
    }
    @RequestMapping(path = "/deletecomment/{id}")
    public Map deleteComment(@PathVariable("id")int id){
        mapper.deleteById(id);
        Map map1=new HashMap();
        map1.put("error","success");
        return map1;
    }
    @RequestMapping("/commentpage")
    public ModelAndView getCommentHtml(HttpSession session){
        ModelAndView mv=new ModelAndView();
        String t_id=session.getAttribute("t_id").toString();
        List list=mapper.UserComArticleXQ(t_id);
        //相关文章
        mv.addObject("article",list);
        //评论内容
        mv.setViewName("/app/comment.html");

        return mv;
    }
    @RequestMapping(path = "/comment/{id}")
    public ModelAndView getUserComment(@PathVariable String id, HttpSession session){
        ModelAndView mv=new ModelAndView();
        String t_id=session.getAttribute("t_id").toString();
        List list=mapper.UserComArticleXQ(t_id);
        //相关文章
        List list2=mapper.getUserComment(id);
        mv.addObject("comment",list2);
        mv.addObject("article",list);
        mv.setViewName("/app/comment.html");
        return mv;
    }

}
