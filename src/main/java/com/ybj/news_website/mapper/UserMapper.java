package com.ybj.news_website.mapper;

import com.ybj.news_website.excel.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM t_user tu," +
            "     t_role tr," +
            "     t_node tn," +
            "     t_user_role tur," +
            "     t_role_node trn" +
            " where tu.t_user_id=tur.t_user_id " +
            " and tur.t_role_id=tr.t_role_id " +
            " and tr.t_role_id=trn.t_role_id " +
            " and trn.t_node_id=tn.t_node_id " +
            " and tu.t_user_id=#{t_user_id}")
    public List<Map<String,Object>> getUserNode(String t_user_id);

    @Select("select*from t_user where t_user_account=#{t_user_account} " +
            "                   and t_user_password=#{t_user_password}")
    public List<Map<String,Object>> userLogin(@Param("t_user_account") String t_user_account,
                                              @Param("t_user_password") String t_user_password);
    @Select("select*from t_user where t_user_account=#{t_user_account} " +
            "                   and t_user_password=#{t_user_password}")
    public Map<String,String> userLogin2(@Param("t_user_account") String t_user_account,
                                         @Param("t_user_password") String t_user_password);

    @Select("select * from t_user")
    public List<Map<String,Object>> getAllUsers();

    @Delete(" delete from t_user where t_user_id=#{t_user_id}")
    public boolean deleteById(@Param("t_user_id") String t_user_id);

    @Update(" update t_user set t_user_account=#{t_user_account},t_user_password=#{t_user_password}" +
            "  where t_user_id=#{t_user_id}")
    public void editUser(@Param("t_user_account") String t_user_account,
                         @Param("t_user_password") String t_user_password,
                         @Param("t_user_id") String t_user_id);

    @Insert(" insert into t_user(t_user_account,t_user_password,t_user_email,activate_code) values(#{t_user_account},#{t_user_password},#{t_user_email},#{activate_code}) ")
    public void insertUser(@Param("t_user_account") String t_user_account,
                           @Param("t_user_password") String t_user_password,
                           @Param("t_user_email") String t_user_email,
                           @Param("activate_code") String activate_code);
    @Insert(" insert into t_user(t_user_account,t_user_password) values(#{t_user_account},#{t_user_password}) ")
    public void insertUser2(@Param("t_user_account") String t_user_account,
                            @Param("t_user_password") String t_user_password);
    @Select("select count(*) from t_user where t_user_email=#{t_user_email}")
    public int selectEmailCount(@Param("t_user_email") String t_user_email);

    @Select(" select * from t_user where t_user_id=#{t_user_id} ")
    public List<Map<String,Object>> queryUserById(@Param("t_user_id") String t_user_id);

    //excel
    @Select("select * from t_user")
    public List<User> teacherinfor();

    @Update("update t_user set  t_user_url=#{t_user_url} " +
            " where t_user_account=#{t_user_account}")
    public void userhomeimg(@Param("t_user_url") String t_user_url,
                            @Param("t_user_account") String t_user_account);

    @Update("update t_user set t_user_password=#{t_user_password} where" +
            " t_user_account=#{t_user_account}")
    public void NewPassWord(Map map);

    @Select("select * from t_user where t_user_account=#{t_user_account}")
    public List<Map<String,Object>>  getUserHome(@Param("t_user_account") String t_user_account);

    @Update("update t_user set t_user_state=1 where activate_code=#{activate_code}")
    public void updateUserState(@Param("activate_code") String activate_code);
}
