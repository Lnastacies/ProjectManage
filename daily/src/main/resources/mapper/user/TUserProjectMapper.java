package com.adtec.daily.dao;

import com.adtec.daily.bean.user.TUserProject;
import com.adtec.daily.bean.user.TUserProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserProjectMapper {
    int countByExample(TUserProjectExample example);

    int deleteByExample(TUserProjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TUserProject record);

    int insertSelective(TUserProject record);

    List<TUserProject> selectByExample(TUserProjectExample example);

    TUserProject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TUserProject record, @Param("example") TUserProjectExample example);

    int updateByExample(@Param("record") TUserProject record, @Param("example") TUserProjectExample example);

    int updateByPrimaryKeySelective(TUserProject record);

    int updateByPrimaryKey(TUserProject record);
}