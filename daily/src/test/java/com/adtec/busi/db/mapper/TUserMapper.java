package com.adtec.busi.db.mapper;

import com.adtec.busi.db.entity.TUser;
import com.adtec.busi.db.entity.TUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TUserMapper {
    long countByExample(TUserExample example);

    int deleteByExample(TUserExample example);

    @Delete({
        "delete from t_user",
        "where user_id = #{userId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String userId);

    @Insert({
        "insert into t_user (user_id, user_name, ",
        "dept_id, role_id, ",
        "project_id, password, ",
        "sex, email, identity_no, ",
        "mobile, create_time, ",
        "create_user_id, update_time, ",
        "update_user_id, remark1, ",
        "remark2, remark3)",
        "values (#{userId,jdbcType=VARCHAR}, #{userName,jdbcType=VARCHAR}, ",
        "#{deptId,jdbcType=INTEGER}, #{roleId,jdbcType=INTEGER}, ",
        "#{projectId,jdbcType=INTEGER}, #{password,jdbcType=VARCHAR}, ",
        "#{sex,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{identityNo,jdbcType=VARCHAR}, ",
        "#{mobile,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{createUserId,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{updateUserId,jdbcType=VARCHAR}, #{remark1,jdbcType=VARCHAR}, ",
        "#{remark2,jdbcType=VARCHAR}, #{remark3,jdbcType=VARCHAR})"
    })
    int insert(TUser record);

    int insertSelective(TUser record);

    List<TUser> selectByExample(TUserExample example);

    @Select({
        "select",
        "user_id, user_name, dept_id, role_id, project_id, password, sex, email, identity_no, ",
        "mobile, create_time, create_user_id, update_time, update_user_id, remark1, remark2, ",
        "remark3",
        "from t_user",
        "where user_id = #{userId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.adtec.busi.db.mapper.TUserMapper.BaseResultMap")
    TUser selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByExample(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByPrimaryKeySelective(TUser record);

    @Update({
        "update t_user",
        "set user_name = #{userName,jdbcType=VARCHAR},",
          "dept_id = #{deptId,jdbcType=INTEGER},",
          "role_id = #{roleId,jdbcType=INTEGER},",
          "project_id = #{projectId,jdbcType=INTEGER},",
          "password = #{password,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "identity_no = #{identityNo,jdbcType=VARCHAR},",
          "mobile = #{mobile,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "create_user_id = #{createUserId,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "update_user_id = #{updateUserId,jdbcType=VARCHAR},",
          "remark1 = #{remark1,jdbcType=VARCHAR},",
          "remark2 = #{remark2,jdbcType=VARCHAR},",
          "remark3 = #{remark3,jdbcType=VARCHAR}",
        "where user_id = #{userId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TUser record);
}