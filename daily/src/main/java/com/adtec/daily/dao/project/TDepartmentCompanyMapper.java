package com.adtec.daily.dao.project;

import com.adtec.daily.bean.project.TDepartmentCompany;
import com.adtec.daily.bean.project.TDepartmentCompanyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TDepartmentCompanyMapper {
    int countByExample(TDepartmentCompanyExample example);

    int deleteByExample(TDepartmentCompanyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TDepartmentCompany record);

    int insertSelective(TDepartmentCompany record);

    List<TDepartmentCompany> selectByExample(TDepartmentCompanyExample example);

    TDepartmentCompany selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TDepartmentCompany record, @Param("example") TDepartmentCompanyExample example);

    int updateByExample(@Param("record") TDepartmentCompany record, @Param("example") TDepartmentCompanyExample example);

    int updateByPrimaryKeySelective(TDepartmentCompany record);

    int updateByPrimaryKey(TDepartmentCompany record);
}