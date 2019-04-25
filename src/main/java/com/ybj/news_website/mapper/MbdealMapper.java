package com.ybj.news_website.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Mapper
public interface MbdealMapper {
    @Select("select * from mb_deal")
    public List<Map<String,Object>> getUserMbDeal();

    @Select(" select  *  from mb_deal m,t_user t" +
            " where m.t_user_id=t.t_user_id")
    public List<Map<String,Object>> getMbDeal();

    @Update(" update mb_deal set mb_deal_money=mb_deal_money+#{money}," +
            " mb_role=#{mb_role} where mb_deal_id=#{mb_deal_id}")
    public void addmoney(Map map);
}
