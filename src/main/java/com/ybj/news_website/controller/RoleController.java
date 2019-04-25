package com.ybj.news_website.controller;

import com.ybj.news_website.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class RoleController {

    @Autowired
    RoleMapper mapper;

    @RequestMapping("/rolemanagement")
    public ModelAndView getRoles(){
        ModelAndView mv=new ModelAndView();
        List role=mapper.getAllRoles();
        mv.addObject("allrole",role);
        mv.setViewName("/app/roles.html");
        return mv;
    }
    @RequestMapping("/newrole")
    public ModelAndView insertRole(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("/app/newroles.html");
        return mv;
    }
    @RequestMapping("/deleterole")
    public ModelAndView deleteRole(String t_role_id){
        ModelAndView mv=new ModelAndView();
        if (mapper.deleteRoleById(t_role_id)){
            List role=mapper.getAllRoles();
            mv.addObject("allrole",role);
            String success="删除成功！";
            mv.addObject("success",success);
            mv.setViewName("/app/roles.html");

        }else {
            List role=mapper.getAllRoles();
            mv.addObject("allrole",role);
            mv.setViewName("/app/roles.html");
            String error="删除失败？？？";
            mv.addObject("error",error);
        }
        return mv;
    }


    @RequestMapping("/updaterole")
    public ModelAndView updateRole(String t_role_id){
        ModelAndView mv=new ModelAndView();
        List<Map<String,Object>> role=mapper.queryRoleById(t_role_id);
        mv.addObject("updaterole",role.get(0));
        mv.setViewName("/app/updateroles.html");
        return mv;
    }

    @RequestMapping("/editrole")
    public ModelAndView updateRole(String t_role_name, String t_role_id){
        ModelAndView mv=new ModelAndView();
        mapper.editRole(t_role_name,t_role_id);
        List role=mapper.getAllRoles();
        mv.addObject("allrole",role);
        mv.setViewName("/app/roles.html");
        return mv;
    }

    @RequestMapping("/saverole")
    public ModelAndView saveRole(String t_role_name){
        ModelAndView mv=new ModelAndView();
        mapper.insertRole(t_role_name);
        List role=mapper.getAllRoles();
        mv.addObject("allrole",role);
        mv.setViewName("/app/roles.html");
        return mv;
    }
}
