package com.ybj.news_website.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Mapper
public interface ClassificationMapper {

 

  

    @Select(" select * from t_classification ")
    public List<Map<String,Object>> getAllClassifications();

    @Delete(" delete from t_classification where t_classification_id=#{t_classification_id} ")
    public void deleteClassificationById(@Param("t_classification_id") String t_classification_id);

    @Insert(" insert into t_classification(t_classification_name,t_classification_code)" +
            " values(#{t_classification_name},#{t_classification_code}) ")
    public void insertClassification(
            @Param("t_classification_name") String t_classification_name,
            @Param("t_classification_code") String t_classification_code
    );

    @Select(" select * from t_classification where t_classification_id=#{t_classification_id} ")
    public List<Map<String,Object>> queryClassificationById(@Param("t_classification_id") String t_classification_id);

    @Update(" update t_classification set t_classification_name=#{t_classification_name}" +
            " ,t_classification_code=#{t_classification_code}" +
            "  where t_classification_id=#{t_classification_id}")
    public  void editClassification(
            @Param("t_classification_name") String t_classification_name,
            @Param("t_classification_code") String t_classification_code,
            @Param("t_classification_id") String t_classification_id
    );

}
