package com.adtec.daily.dao.daily;

import com.adtec.daily.bean.daily.TDailyDetail;
import com.adtec.daily.bean.daily.TDailyDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TDailyDetailMapper {
    int countByExample(TDailyDetailExample example);

    int deleteByExample(TDailyDetailExample example);

    int deleteByPrimaryKey(String dailyDetailId);

    int insert(TDailyDetail record);

    int insertSelective(TDailyDetail record);

    List<TDailyDetail> selectByExample(TDailyDetailExample example);

    TDailyDetail selectByPrimaryKey(String dailyDetailId);

    int updateByExampleSelective(@Param("record") TDailyDetail record, @Param("example") TDailyDetailExample example);

    int updateByExample(@Param("record") TDailyDetail record, @Param("example") TDailyDetailExample example);

    int updateByPrimaryKeySelective(TDailyDetail record);

    int updateByPrimaryKey(TDailyDetail record);

    //查询日报详细列表
    List<TDailyDetail> selectDailyDetailByDailyId(TDailyDetail record);
}