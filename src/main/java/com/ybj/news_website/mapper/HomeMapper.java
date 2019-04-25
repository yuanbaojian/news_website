package com.ybj.news_website.mapper;

import com.ybj.news_website.model.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Mapper
@Service
public interface HomeMapper {
    @Select("select * from t_rece_node")
    public List<Map<String,Object>> getHomeNode();

    @Select("select count(t_article_id) from t_article ")
    public int getArticleCount();

    @Select("select * from t_article order by t_article_id desc limit #{offset},#{limit}")
    public List<Map<String,Object>> getArticle(@Param("offset") int offset, @Param("limit") int limit);

    @Select("select * from t_article order by t_article_id desc limit #{offset},#{limit}")
    public List<Article> getArticleClick(@Param("offset") int offset, @Param("limit") int limit);

    @Select("select * from t_article where t_classification_code=#{t_classification_code} ")
    public List<Map<String,Object>> getTplb(@Param("t_classification_code") String t_classification_code);

    @Select("select * from t_article where t_classification_code=#{t_classification_code} ")
    public List<Map<String,Object>> getTjgl(@Param("t_classification_code") String t_classification_code);

    @Select("select * from t_article where t_article_id=#{t_article_id} ")
    Map selectById(@Param("t_article_id") int t_article_id);

}
