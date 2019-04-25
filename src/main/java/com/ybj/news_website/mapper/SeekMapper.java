package com.ybj.news_website.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Mapper
public interface SeekMapper {
    @Select("select * from t_article where t_article_title_1 LIKE CONCAT(#{t_article_title_1},'%')")
    public List<Map<String,Object>> getIndexSeek(@Param("t_article_title_1") String t_article_title_1);
    @Select("select * from t_article where t_article_document LIKE CONCAT('%',#{keyword},'%') " +
            " or  t_article_title_1 LIKE CONCAT('%',#{keyword},'%')")
    public List<Map<String,Object>> getArticle(@Param("keyword") String keyword);
    @Select("select * from t_article where t_article_classification=#{name}")
    public List<Map<String,Object>> getReceNodeArticle(String name);
}
