package com.ybj.news_website.controller;

import com.ybj.news_website.mapper.ReceNodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class ReceNodeController {

    @Autowired
    ReceNodeMapper mapper;

    @RequestMapping("/recenodemanagement")
    public ModelAndView getReceNode(){
        ModelAndView mv=new ModelAndView();
        List recenode=mapper.getAllReceNodes();
        mv.addObject("allrecenode",recenode);
        mv.setViewName("/app/recenode.html");
        return mv;
    }
    @RequestMapping("/newrecenode")
    public ModelAndView insertReceNode(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("/app/newrecenode.html");
        return mv;
    }
    @RequestMapping("/newrecenode2")
    public ModelAndView insertReceNode2(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("/app/newrecenode2.html");
        return mv;
    }
    @RequestMapping("/queryallrecenodes")
    @ResponseBody
    public List<Map<String,Object>> getAllReceNode(){
        ModelAndView mv=new ModelAndView();
        List recenode=mapper.getFirstNode2();
        return  recenode;
    }
    @RequestMapping("/queryfirstnode")
    @ResponseBody
    public List<Map<String,Object>> getAllFirstNode(){
        ModelAndView mv=new ModelAndView();
        List recenode=mapper.getFirstNode();
        return  recenode;
    }
    @RequestMapping("/deleterecenode")
    public ModelAndView deleteReceNode(String t_rece_node_id){
        ModelAndView mv=new ModelAndView();
        if (mapper.deleteReceNodeById(t_rece_node_id)){
            List recenode=mapper.getAllReceNodes();
            mv.addObject("allrecenode",recenode);
            String success="删除成功！";
            mv.addObject("success",success);
            mv.setViewName("/app/recenode.html");

        }else {
            List recenode=mapper.getAllReceNodes();
            mv.addObject("allrecenode",recenode);
            mv.setViewName("/app/recenode.html");
            String error="删除失败？？？";
            mv.addObject("error",error);
        }
        return mv;
    }


    @RequestMapping("/updaterecenode")
    public ModelAndView updateReceNode(String t_rece_node_id){
        ModelAndView mv=new ModelAndView();
        List<Map<String,Object>> recenode=mapper.queryReceNodeById(t_rece_node_id);
        mv.addObject("updaterecenode",recenode.get(0));
        mv.setViewName("/app/updaterecenode.html");
        return mv;
    }

    @RequestMapping("/editrecenode")
    public ModelAndView updateReceNode(@RequestParam Map map){
        ModelAndView mv=new ModelAndView();
        mapper.editReceNode(map);
        List recenode=mapper.getAllReceNodes();
        mv.addObject("allrecenode",recenode);
        mv.setViewName("/app/recenode.html");
        return mv;
    }

    @RequestMapping("/saverecenode")
    public ModelAndView saveReceNode(@RequestParam Map map){
        ModelAndView mv=new ModelAndView();
        mapper.insertReceNode(map);
        List recenode=mapper.getAllReceNodes();
        mv.addObject("allrecenode",recenode);
        mv.setViewName("/app/recenode.html");
        return mv;
    }
}
