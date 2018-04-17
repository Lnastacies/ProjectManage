package com.adtec.daily.dao.project;

import com.adtec.daily.bean.project.TCompany;
import com.adtec.daily.bean.project.TCompanyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TCompanyMapper {
    int countByExample(TCompanyExample example);

    int deleteByExample(TCompanyExample example);

    int deleteByPrimaryKey(Integer companyId);

    int insert(TCompany record);

    int insertSelective(TCompany record);

    List<TCompany> selectByExample(TCompanyExample example);

    TCompany selectByPrimaryKey(Integer companyId);

    int updateByExampleSelective(@Param("record") TCompany record, @Param("example") TCompanyExample example);

    int updateByExample(@Param("record") TCompany record, @Param("example") TCompanyExample example);

    int updateByPrimaryKeySelective(TCompany record);

    int updateByPrimaryKey(TCompany record);

    TCompany selectByDeptId(int deptId);

    List<TCompany> selectAllCompany();
}