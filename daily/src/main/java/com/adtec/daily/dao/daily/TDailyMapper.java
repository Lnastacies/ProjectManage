package com.adtec.daily.dao.daily;

import com.adtec.daily.bean.daily.TDaily;
import com.adtec.daily.bean.daily.TDailyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TDailyMapper {
    int countByExample(TDailyExample example);

    int deleteByExample(TDailyExample example);

    int deleteByPrimaryKey(String dailyId);

    int insert(TDaily record);

    int insertSelective(TDaily record);

    List<TDaily> selectByExample(TDailyExample example);

    TDaily selectByPrimaryKey(String dailyId);

    int updateByExampleSelective(@Param("record") TDaily record, @Param("example") TDailyExample example);

    int updateByExample(@Param("record") TDaily record, @Param("example") TDailyExample example);

    int updateByPrimaryKeySelective(TDaily record);

    int updateByPrimaryKey(TDaily record);

    //日报列表查询
    List<TDaily> selectDailyListByUserId(TDaily record);

    //根据用户id及日报日期查询日报明细列表
    List<TDaily> selectDailyListByUserIdAndDailyDate(@Param("userId") String userId, @Param("startDate") String startDate, @Param("endDate") String endDate);
}