package com.ybj.news_website.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Mapper
public interface NodeMapper {

    @Select("select * from t_node")
    public List<Map<String,Object>> getAllNodes();

    @Select("select * from t_node where t_node_level='1' ")
    public List<Map<String,Object>> getSelectNodes();

    @Delete(" delete from t_node where t_node_id=#{t_node_id}")
    public boolean deleteNodeById(@Param("t_node_id") String t_node_id);

    @Update(" update t_node set t_node_name=#{t_node_name}," +
            " t_node_url=#{t_node_url},t_node_level=#{t_node_level},t_node_parent_id=#{t_node_parent_id} " +
            "   where t_node_id=#{t_node_id}")
    public void editNode(@Param("t_node_name") String t_node_name,
                         @Param("t_node_url") String t_node_url,
                         @Param("t_node_level") String t_node_level,
                         @Param("t_node_parent_id") String t_node_parent_id,
                         @Param("t_node_id") String t_node_id);

    @Insert(" insert into t_node(t_node_name,t_node_url,t_node_level,t_node_parent_id)" +
            " values(#{t_node_name},#{t_node_url},#{t_node_level},#{t_node_parent_id}) ")
    public void insertNode(@Param("t_node_name") String t_node_name,
                           @Param("t_node_url") String t_node_url,
                           @Param("t_node_level") String t_node_level,
                           @Param("t_node_parent_id") String t_node_parent_id);

    @Select(" select * from t_node where t_node_id=#{t_node_id} ")
    public List<Map<String,Object>> queryNodeById(@Param("t_node_id") String t_node_id);
}
