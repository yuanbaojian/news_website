package com.ybj.news_website.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Mapper
public interface RoleMapper {

    @Select("select * from t_role")
    public List<Map<String,Object>> getAllRoles();

    @Delete(" delete from t_role where t_role_id=#{t_role_id}")
    public boolean deleteRoleById(@Param("t_role_id") String t_role_id);

    @Update(" update t_role set t_role_name=#{t_role_name} " +
            "  where t_role_id=#{t_role_id}")
    public void editRole(@Param("t_role_name") String t_role_name,
                         @Param("t_role_id") String t_role_id);

    @Insert(" insert into t_role(t_role_name) values(#{t_role_name}) ")
    public void insertRole(@Param("t_role_name") String t_role_name);

    @Select(" select * from t_role where t_role_id=#{t_role_id} ")
    public List<Map<String,Object>> queryRoleById(@Param("t_role_id") String t_role_id);
}
