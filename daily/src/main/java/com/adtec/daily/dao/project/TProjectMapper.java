package com.adtec.daily.dao.project;

import com.adtec.daily.bean.project.TProject;
import com.adtec.daily.bean.project.TProjectExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TProjectMapper {
    int countByExample(TProjectExample example);

    int deleteByExample(TProjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TProject record);

    int insertSelective(TProject record);

    List<TProject> selectByExample(TProjectExample example);

    TProject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TProject record, @Param("example") TProjectExample example);

    int updateByExample(@Param("record") TProject record, @Param("example") TProjectExample example);

    int updateByPrimaryKeySelective(TProject record);

    int updateByPrimaryKey(TProject record);

    List<TProject> selectAllProject();

    TProject selectInfoByPrimaryKey(Integer id);

    TProject selectByUserId(String userId);

    // 根据员工查看项目信息
    List<TProject> selectProjectByUser(String userId);

}