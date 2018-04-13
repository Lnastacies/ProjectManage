package com.adtec.daily.dao.user;

import com.adtec.daily.bean.user.TUser;
import com.adtec.daily.bean.user.TUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserMapper {
    int countByExample(TUserExample example);

    int deleteByExample(TUserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(TUser record);

    int insertSelective(TUser record);

    List<TUser> selectByExample(TUserExample example);

    TUser selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByExample(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    TUser selectByUserId(String userId);

    List<TUser> selectAllUser();

    List<TUser> selectAllUserByProjectIdAndCompanyId(@Param("projectId") int projectId,@Param("companyId") int companyId);
}