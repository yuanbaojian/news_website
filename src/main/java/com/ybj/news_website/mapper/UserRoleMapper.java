package com.ybj.news_website.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Mapper
public interface UserRoleMapper {
//    @Select(" select t_user.*,t_role.* from  t_user,t_role,t_user_role" +
//            " where t_user.t_user_id=t_user_role.t_user_id " +
//            " and t_user_role.t_role_id=t_role.t_role_id")
    @Select(" SELECT tu.*,IFNULL(tr.t_role_id,'')t_role_id,IFNULL(tr.t_role_name,'')t_role_name  " +
            " FROM t_user tu LEFT JOIN t_user_role ur ON tu.t_user_id=ur.t_user_id" +
            " LEFT JOIN t_role tr ON tr.t_role_id=ur.t_role_id ")
    public List<Map<String,Object>> getAllUserRole();

//    @Update(" update t_user_role set t_role_id=#{t_role_id}" +
//            " where t_user_id=#{t_user_id}")
//    public void updateUserRole(
//            @Param("t_user_id") String t_user_id,
//            @Param("t_role_id") String t_role_id
//    );

    @Select(" call p1(#{t_user_id},#{t_role_id})")
    public void updateUserRole(@Param("t_user_id") String t_user_id,
                               @Param("t_role_id") String t_role_id);

    

}
