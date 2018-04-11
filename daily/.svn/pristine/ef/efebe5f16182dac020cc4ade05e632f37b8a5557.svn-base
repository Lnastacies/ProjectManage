package com.adtec.daily.dao.project;

import com.adtec.daily.bean.user.TUserDepartment;
import com.adtec.daily.bean.user.TUserDepartmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserDepartmentMapper {
    int countByExample(TUserDepartmentExample example);

    int deleteByExample(TUserDepartmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TUserDepartment record);

    int insertSelective(TUserDepartment record);

    List<TUserDepartment> selectByExample(TUserDepartmentExample example);

    TUserDepartment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TUserDepartment record, @Param("example") TUserDepartmentExample example);

    int updateByExample(@Param("record") TUserDepartment record, @Param("example") TUserDepartmentExample example);

    int updateByPrimaryKeySelective(TUserDepartment record);

    int updateByPrimaryKey(TUserDepartment record);
}