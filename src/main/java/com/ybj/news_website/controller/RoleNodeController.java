package com.ybj.news_website.controller;

import com.ybj.news_website.mapper.RoleMapper;
import com.ybj.news_website.mapper.RoleNodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RoleNodeController {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RoleNodeMapper roleNodeMapper;
    @RequestMapping("/rolenode")
    public ModelAndView getUserNodePage(){
        ModelAndView mv=new ModelAndView();
        List roles=roleMapper.getAllRoles();
        List nodes=roleNodeMapper.getAllNodes();
        mv.setViewName("/app/rolenode.html");
        mv.addObject("allroles",roles);
        mv.addObject("allnodes",nodes);

        return mv;
        }
     @RequestMapping("/getnodesbyroleid")
    public List getNodeByRoleId(String t_role_id){
        List nodes=roleNodeMapper.getRoleNodeById(t_role_id);
        return nodes;
     }
     @RequestMapping("/getallnodes")
    public List getNodeByRoleId(){
        List nodes=roleNodeMapper.getAllNodes();
        return nodes;
     }
     @RequestMapping("/saverolenode")
    public Map saveRoleNode(@RequestParam Map map1){
        Map map=new HashMap();
        roleNodeMapper.saveRoleNode(map1);
        map.put("success","success");
        return map;
     }
}
