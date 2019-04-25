package com.ybj.news_website.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Mapper
@Service
public interface NoteMapper {
    @Select("select * from t_note")
    public List<Map<String,Object>> getNote();
    @Select("select * from t_note where t_user_id<>0 ORDER BY pId")
    public List<Map<String,Object>> getNote2();
    @Select("select * from t_note where id=#{id}")
    public Map queryByNameNote(String id);
    @Insert(" insert into t_note(name,t_user_id,pId,t_note_content)" +
            " values(#{name},#{t_user_id},#{pId},#{t_note_content})")
    public void addNote(Map map);
    @Delete("DELETE FROM t_note WHERE id=#{id} OR pId=#{id}")
    public void delNote(Map map);
    @Update("update t_note set name=#{name} where id=#{id}")
    public void editName(Map map);
    @Update("update t_note set t_note_content=#{t_note_content2} where id=#{id}")
    public void editNoteContent(Map map);
}
