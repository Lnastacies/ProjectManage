package com.adtec.daily.dao.daily;

import com.adtec.daily.bean.daily.DailyDetail;
import com.adtec.daily.bean.daily.DailyDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DailyDetailMapper {
    int countByExample(DailyDetailExample example);

    int deleteByExample(DailyDetailExample example);

    int deleteByPrimaryKey(String dailyDetailId);

    int insert(DailyDetail record);

    int insertSelective(DailyDetail record);

    List<DailyDetail> selectByExample(DailyDetailExample example);

    DailyDetail selectByPrimaryKey(String dailyDetailId);

    int updateByExampleSelective(@Param("record") DailyDetail record, @Param("example") DailyDetailExample example);

    int updateByExample(@Param("record") DailyDetail record, @Param("example") DailyDetailExample example);

    int updateByPrimaryKeySelective(DailyDetail record);

    int updateByPrimaryKey(DailyDetail record);

    //查询日报详细列表
    List<DailyDetail> selectDailyDetailByDailyId(DailyDetail record);
}