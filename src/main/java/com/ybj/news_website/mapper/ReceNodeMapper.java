package com.ybj.news_website.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Mapper
public interface ReceNodeMapper {
    @Select("select * from t_rece_node")
    public List<Map<String,Object>> getAllReceNodes();

    @Select("select * from t_rece_node where t_rece_node_level=1")
    public List<Map<String,Object>> getFirstNode();

    @Select("select * from t_rece_node where t_rece_node_level=2")
    public List<Map<String,Object>> getFirstNode2();

    @Delete(" delete from t_rece_node where t_rece_node_id=#{t_rece_node_id}")
    public boolean deleteReceNodeById(@Param("t_rece_node_id") String t_rece_node_id);

    @Update(" update t_rece_node set t_rece_node_name=#{t_rece_node_name},t_rece_node_url=#{t_rece_node_url}," +
            " t_rece_node_level=#{t_rece_node_level}" +
            "  where t_rece_node_id=#{t_rece_node_id}")
    public void editReceNode(Map map);

    @Insert(" insert into t_rece_node(t_rece_node_name,t_rece_node_url,t_rece_node_level,t_rece_node_parent_id,data) values(" +
            "#{t_rece_node_name},#{t_rece_node_url},#{t_rece_node_level},#{t_rece_node_parent_id},#{data}) ")
    public void insertReceNode(Map map);

    @Select(" select * from t_rece_node where t_rece_node_id=#{t_rece_node_id} ")
    public List<Map<String,Object>> queryReceNodeById(@Param("t_rece_node_id") String t_t_rece_node_id);
}
