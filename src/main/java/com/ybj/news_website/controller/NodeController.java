package com.ybj.news_website.controller;

import com.ybj.news_website.mapper.NodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class NodeController {

    @Autowired
    NodeMapper mapper;

    @RequestMapping("/nodemanagement")
    public ModelAndView gets(){
        ModelAndView mv=new ModelAndView();
        List node=mapper.getAllNodes();
        mv.addObject("allnode",node);
        mv.setViewName("/app/nodes.html");
        return mv;
    }
    @RequestMapping("/newnode")
    public ModelAndView insertNode(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("/app/newnodes.html");
        return mv;
    }
    @RequestMapping("/deletenode")
    public ModelAndView deleteNode(String t_node_id){
        ModelAndView mv=new ModelAndView();
        if (mapper.deleteNodeById(t_node_id)){
            List node=mapper.getAllNodes();
            mv.addObject("allnode",node);
            String success="删除成功！";
            mv.addObject("success",success);
            mv.setViewName("/app/nodes.html");

        }else {
            List node=mapper.getAllNodes();
            mv.addObject("allnode",node);
            mv.setViewName("/app/nodes.html");
            String error="删除失败？？？";
            mv.addObject("error",error);
        }
        return mv;
    }
    @RequestMapping("/updatenode")
    public ModelAndView updateNode(String t_node_id){
        ModelAndView mv=new ModelAndView();
        List node=mapper.queryNodeById(t_node_id);
        mv.addObject("updatenode",node.get(0));
        mv.setViewName("/app/updatenodes.html");
        return mv;
    }
    @RequestMapping("/editnode")
    public ModelAndView updateNode(String t_node_name,
                                   String t_node_url,
                                   String t_node_level,
                                   String t_node_parent_id,
                                   String t_node_id){
        ModelAndView mv=new ModelAndView();
        mapper.editNode(t_node_name,t_node_url,t_node_level,t_node_parent_id,t_node_id);
        List node=mapper.getAllNodes();
        mv.addObject("allnode",node);
        mv.setViewName("/app/nodes.html");
        return mv;
    }
    @RequestMapping("/savenode")
    public ModelAndView saveNode(String t_node_name,
                                 String t_node_url,
                                 String t_node_level,
                                 String t_node_parent_id){
        ModelAndView mv=new ModelAndView();
        mapper.insertNode(t_node_name,t_node_url,t_node_level,t_node_parent_id);
        System.out.println(t_node_parent_id);
        List node=mapper.getAllNodes();
        mv.addObject("allnode",node);
        mv.setViewName("/app/nodes.html");
        return mv;
    }
    @RequestMapping("/selectnode")
    @ResponseBody
    public List<Map<String,Object>> getNodeSelect(){
        List<Map<String,Object>> nodes=mapper.getSelectNodes();
        return nodes;
    }

}
