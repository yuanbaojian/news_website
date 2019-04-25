package com.ybj.news_website.controller;

import com.ybj.news_website.mapper.RoleMapper;
import com.ybj.news_website.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Controller
public class UserRoleController {
    @Autowired
    public  UserRoleMapper mapper;
    @Autowired
    public RoleMapper rolemapper;
    @RequestMapping("/userrole")
    public ModelAndView getAllUserRole(){
        ModelAndView mv=new ModelAndView();
        List userrole=mapper.getAllUserRole();
        List role=rolemapper.getAllRoles();
        mv.addObject("allrole",role);
        mv.addObject("alluserrole",userrole);
        mv.setViewName("/app/userrole.html");
        return mv;
    }
    @RequestMapping("/updateuserrole")
    @ResponseBody
    public Map updateUserRole(String t_user_id, String t_role_id){
        mapper.updateUserRole(t_user_id,t_role_id);
        Map map=new HashMap();
        map.put("flag","true");
        return map;
    }
}
