package com.ybj.news_website.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Mapper
public interface RoleNodeMapper {

    @Select(" SELECT n.* FROM t_role r,t_role_node rn,t_node n" +
            " WHERE r.t_role_id=rn.t_role_id AND rn.t_node_id=n.t_node_id and r.t_role_id=#{t_role_id}")
    public List<Map<String,Object>> getRoleNodeById(@Param("t_role_id") String t_role_id);

 @Select("select * from t_node ")
    public List<Map<String,Object>> getAllNodes();

 @Select("call saverolenode(#{t_role_id},#{node_id})")
    public String saveRoleNode(Map map);
}
