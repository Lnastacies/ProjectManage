package com.adtec.daily.dao.demand;

import com.adtec.daily.bean.demand.DemandTrack;
import com.adtec.daily.bean.demand.DemandTrackExample;
import com.adtec.daily.bean.demand.DemandTrackKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DemandTrackMapper {
    int countByExample(DemandTrackExample example);

    int deleteByExample(DemandTrackExample example);

    int deleteByPrimaryKey(DemandTrackKey key);

    int insert(DemandTrack record);

    int insertSelective(DemandTrack record);

    List<DemandTrack> selectByExample(DemandTrackExample example);

    DemandTrack selectByPrimaryKey(DemandTrackKey key);

    int updateByExampleSelective(@Param("record") DemandTrack record, @Param("example") DemandTrackExample example);

    int updateByExample(@Param("record") DemandTrack record, @Param("example") DemandTrackExample example);

    int updateByPrimaryKeySelective(DemandTrack record);

    int updateByPrimaryKey(DemandTrack record);
}