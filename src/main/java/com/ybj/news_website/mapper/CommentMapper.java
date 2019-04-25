package com.ybj.news_website.mapper;

import com.ybj.news_website.model.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Mapper
public interface CommentMapper {
    String TABLE_NAEM = " comment ";
    String INSERT_FIELDS = " content,t_user_id,t_article_id,pid ";
    String INSERT_FIELDS1 = " content,t_user_id,t_article_id,pid,reply_user_id ";

    @Insert({"insert into",TABLE_NAEM,"(",INSERT_FIELDS,") values (#{content}," +
            "#{t_user_id},#{t_article_id},#{pid})"})
    void insertComment(Map map);

    @Insert({"insert into",TABLE_NAEM,"(",INSERT_FIELDS1,") values (#{content}," +
            "#{t_user_id},#{t_article_id},#{pid},#{reply_user_id})"})
    void insertComment2(Map map);


    @Select(" SELECT tu.t_user_account,tu.t_user_url,co.* FROM t_user tu , COMMENT co " +
            " WHERE tu.t_user_id=co.t_user_id AND co.t_article_id=#{t_article_id}")
    List<Map<String,Object>> getAllComment2(int t_article_id);

    @Select(" SELECT tu.t_user_account,tu.t_user_url,co.* FROM t_user tu , COMMENT co " +
            " WHERE tu.t_user_id=co.t_user_id AND co.t_article_id=#{t_article_id} and co.pid=0 " +
            " order by  create_time desc   ")
    List<Map<String,Object>> getAllComment(int t_article_id);

    @Select(" SELECT * FROM t_user tu , COMMENT co " +
            " WHERE tu.t_user_id=co.t_user_id  and  co.pid!=0 ")
    List<Map<String, Object>>  countAllComment2();

    @Select(" SELECT * FROM t_user tu , COMMENT co " +
            " WHERE tu.t_user_id=co.t_user_id  and co.pid=0 ")
    List<Map<String, Object>> countAllComment();
    @Select("select count(0) from COMMENT where t_user_id=#{id} ")
    int UserComCount(@Param("id") String t_id);

    @Select("select * from t_article where t_article_id in (select  distinct  t_article_id from comment where t_user_id=#{id} )")
    List<Map<String, Object>> UserComArticleXQ(@Param("id") String t_id);

//    @Select("select * from comment where t_article_id=#{t_article_id] ")
//    List<Map<String,Object>> getReplyComment();

    @Select({"select * from",TABLE_NAEM,"where id=#{id}"})
    Comment seletById(int id);

    @Select({"select * from",TABLE_NAEM,"order by id desc limit #{offset},#{limit}"})
    List<Comment> selectLatestComments(@Param("offset") int offset, @Param("limit") int limit);

    @Select({"select * from",TABLE_NAEM,"where t_article_id = #{t_article_id} " +
            "order by create_time desc"})
    List<Comment> selectCommentsByArticleId(@Param("t_article_id") int t_article_id);

    @Select({"select count(id) from",TABLE_NAEM,"where t_article_id = #{t_article_id}"})
    int getCommentCountByArticleId(@Param("t_article_id") int t_article_id);


    @Delete({"delete from",TABLE_NAEM,"where id=#{id}"})
    void deleteById(int id);

    @Select("select c.*,t.t_user_account from comment c,t_user t where c.t_article_id=#{id} and c.t_user_id=t.t_user_id")
    public List<Map<String, Object>> getUserComment(@Param("id") String id);

}
