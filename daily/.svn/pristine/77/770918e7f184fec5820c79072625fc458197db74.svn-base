package com.adtec.daily.dao.user;

import com.adtec.daily.bean.user.TPrivilege;
import com.adtec.daily.bean.user.TPrivilegeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TPrivilegeMapper {
    int countByExample(TPrivilegeExample example);

    int deleteByExample(TPrivilegeExample example);

    int deleteByPrimaryKey(Integer privilegeId);

    int insert(TPrivilege record);

    int insertSelective(TPrivilege record);

    List<TPrivilege> selectByExample(TPrivilegeExample example);

    TPrivilege selectByPrimaryKey(Integer privilegeId);

    int updateByExampleSelective(@Param("record") TPrivilege record, @Param("example") TPrivilegeExample example);

    int updateByExample(@Param("record") TPrivilege record, @Param("example") TPrivilegeExample example);

    int updateByPrimaryKeySelective(TPrivilege record);

    int updateByPrimaryKey(TPrivilege record);
}