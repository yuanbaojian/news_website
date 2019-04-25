package com.ybj.news_website.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Mapper
public interface ArticleMapper {
    @Select("select * from t_article where t_user_account=#{t_user_account}")
    public List<Map<String, Object>> getUserAllArticles(@Param("t_user_account") String t_user_account);
    @Select("select * from t_article")
    public List<Map<String, Object>> getAllArticles();

    @Delete(" delete from t_article where t_article_id=#{t_article_id}")
    public boolean deleteById(@Param("t_article_id") String t_article_id);

    @Update(" update t_article set t_article_title_1=#{t_article_title_1}" +
            ",t_article_title_2=#{t_article_title_2}," +
            " t_article_title_3=#{t_article_title_3}," +
            " t_article_source=#{t_article_source}," +
            " t_article_author=#{t_article_author}," +
            " t_article_create_date=#{t_article_create_date}," +
            " t_article_img1=#{t_article_img1}," +
            "t_article_img2=#{t_article_img2}," +
            "t_article_img3=#{t_article_img3}," +
            "t_article_img4=#{t_article_img4}," +
            "t_article_img5=#{t_article_img5}," +
            "t_article_document=#{t_article_document}" +
            "  where t_article_id=#{t_article_id}")
    public void editArticle(Map map);

    @Insert("insert into t_article(t_article_title_1,t_article_title_2,t_article_title_3," +
            "t_article_source," +
            "t_article_author," +
            "t_article_create_date," +
            "t_article_img1," +
            "t_article_img2," +
            "t_article_img3," +
            "t_article_img4," +
            "t_article_img5,t_article_document,t_article_classification,t_user_account)values(" +
            "#{t_article_title_1},#{t_article_title_2},#{t_article_title_3}," +
            "#{t_article_source},#{t_article_author},#{t_article_create_date}," +
            "#{t_article_img1},#{t_article_img2},#{t_article_img3}," +
            "#{t_article_img4},#{t_article_img5},#{t_article_document},#{t_article_classification}," +
            "#{t_user_account})")
    public void insertArticle(@Param("t_article_title_1") String t_article_title_1,
                              @Param("t_article_title_2") String t_article_title_2,
                              @Param("t_article_title_3") String t_article_title_3,
                              @Param("t_article_source") String t_article_source,
                              @Param("t_article_author") String t_article_author,
                              @Param("t_article_create_date") String t_article_create_date,
                              @Param("t_article_img1") String t_article_img1,
                              @Param("t_article_img2") String t_article_img2,
                              @Param("t_article_img3") String t_article_img3,
                              @Param("t_article_img4") String t_article_img4,
                              @Param("t_article_img5") String t_article_img5,
                              @Param("t_article_document") String t_article_document,
                              @Param("t_article_classification") String t_article_classification,
                              @Param("t_user_account") String t_user_account);
    @Insert("insert into t_article(t_article_title_1,t_article_title_2,t_article_title_3," +
            "t_article_source," +
            "t_article_author," +
            "t_article_create_date," +
            "t_article_img1," +
            "t_article_img2," +
            "t_article_img3," +
            "t_article_img4," +
            "t_article_img5,t_article_document,t_article_classification,t_classification_code)values(" +
            "#{t_article_title_1},#{t_article_title_2},#{t_article_title_3}," +
            "#{t_article_source},#{t_article_author},#{t_article_create_date}," +
            "#{t_article_img1},#{t_article_img2},#{t_article_img3}," +
            "#{t_article_img4},#{t_article_img5},#{t_article_document},#{t_article_classification}," +
            "#{t_classification_code})")
    public void xwinsertArticle(@Param("t_article_title_1") String t_article_title_1,
                                @Param("t_article_title_2") String t_article_title_2,
                                @Param("t_article_title_3") String t_article_title_3,
                                @Param("t_article_source") String t_article_source,
                                @Param("t_article_author") String t_article_author,
                                @Param("t_article_create_date") String t_article_create_date,
                                @Param("t_article_img1") String t_article_img1,
                                @Param("t_article_img2") String t_article_img2,
                                @Param("t_article_img3") String t_article_img3,
                                @Param("t_article_img4") String t_article_img4,
                                @Param("t_article_img5") String t_article_img5,
                                @Param("t_article_document") String t_article_document,
                                @Param("t_article_classification") String t_article_classification,
                                @Param("t_classification_code") String t_classification_code);

    @Select(" select * from t_article where t_article_id=#{t_article_id} ")
    public List<Map<String, Object>> queryArticleById(@Param("t_article_id") String t_article_id);
    @Select(" select * from t_article where t_article_id=#{t_article_id} ")
    public Map queryIntArticleById(@Param("t_article_id") int t_article_id);
}
