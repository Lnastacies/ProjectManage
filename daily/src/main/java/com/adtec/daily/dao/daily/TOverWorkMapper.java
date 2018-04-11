package com.adtec.daily.dao.daily;

import com.adtec.daily.bean.daily.TOverWork;
import com.adtec.daily.bean.daily.TOverWorkExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TOverWorkMapper {
    int countByExample(TOverWorkExample example);

    int deleteByExample(TOverWorkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TOverWork record);

    int insertSelective(TOverWork record);

    List<TOverWork> selectByExample(TOverWorkExample example);

    TOverWork selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TOverWork record, @Param("example") TOverWorkExample example);

    int updateByExample(@Param("record") TOverWork record, @Param("example") TOverWorkExample example);

    int updateByPrimaryKeySelective(TOverWork record);

    int updateByPrimaryKey(TOverWork record);

    //根据用户id及上班日期查询加班明细列表
    List<TOverWork> selectOverWorkDetailListByUserIdAndWorkDate(@Param("userId") String userId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    //根据用户id及上班日期查询加班总时长
    TOverWork selectOverWorkByUserIdAndWorkDate(@Param("userId") String userId, @Param("startDate") String startDate, @Param("endDate") String endDate);
}