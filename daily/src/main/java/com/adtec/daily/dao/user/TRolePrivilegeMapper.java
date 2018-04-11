package com.adtec.daily.dao.user;

import com.adtec.daily.bean.user.TRolePrivilege;
import com.adtec.daily.bean.user.TRolePrivilegeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRolePrivilegeMapper {
    int countByExample(TRolePrivilegeExample example);

    int deleteByExample(TRolePrivilegeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRolePrivilege record);

    int insertSelective(TRolePrivilege record);

    List<TRolePrivilege> selectByExample(TRolePrivilegeExample example);

    TRolePrivilege selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRolePrivilege record, @Param("example") TRolePrivilegeExample example);

    int updateByExample(@Param("record") TRolePrivilege record, @Param("example") TRolePrivilegeExample example);

    int updateByPrimaryKeySelective(TRolePrivilege record);

    int updateByPrimaryKey(TRolePrivilege record);
}