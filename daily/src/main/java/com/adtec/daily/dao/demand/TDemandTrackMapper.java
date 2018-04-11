package com.adtec.daily.dao.demand;

import com.adtec.daily.bean.demand.TDemandTrack;
import com.adtec.daily.bean.demand.TDemandTrackExample;
import com.adtec.daily.bean.demand.TDemandTrackKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TDemandTrackMapper {
    int countByExample(TDemandTrackExample example);

    int deleteByExample(TDemandTrackExample example);

    int deleteByPrimaryKey(TDemandTrackKey key);

    int insert(TDemandTrack record);

    int insertSelective(TDemandTrack record);

    List<TDemandTrack> selectByExample(TDemandTrackExample example);

    TDemandTrack selectByPrimaryKey(TDemandTrackKey key);

    int updateByExampleSelective(@Param("record") TDemandTrack record, @Param("example") TDemandTrackExample example);

    int updateByExample(@Param("record") TDemandTrack record, @Param("example") TDemandTrackExample example);

    int updateByPrimaryKeySelective(TDemandTrack record);

    int updateByPrimaryKey(TDemandTrack record);
}