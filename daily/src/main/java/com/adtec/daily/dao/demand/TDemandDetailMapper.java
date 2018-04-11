package com.adtec.daily.dao.demand;

import com.adtec.daily.bean.demand.TDemandDetail;
import com.adtec.daily.bean.demand.TDemandDetailExample;
import com.adtec.daily.bean.demand.TDemandDetailKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TDemandDetailMapper {
    int countByExample(TDemandDetailExample example);

    int deleteByExample(TDemandDetailExample example);

    int deleteByPrimaryKey(TDemandDetailKey key);

    int insert(TDemandDetail record);

    int insertSelective(TDemandDetail record);

    List<TDemandDetail> selectByExample(TDemandDetailExample example);

    TDemandDetail selectByPrimaryKey(TDemandDetailKey key);

    int updateByExampleSelective(@Param("record") TDemandDetail record, @Param("example") TDemandDetailExample example);

    int updateByExample(@Param("record") TDemandDetail record, @Param("example") TDemandDetailExample example);

    int updateByPrimaryKeySelective(TDemandDetail record);

    int updateByPrimaryKey(TDemandDetail record);

    //联合表t_demand_track查询具体需求信息
    List<TDemandDetail> selectByDemandId(TDemandDetail record);
}