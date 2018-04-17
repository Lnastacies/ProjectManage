package com.adtec.daily.dao.project;

import com.adtec.daily.bean.user.TUserDepartment;
import com.adtec.daily.bean.user.TUserDepartmentExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TUserDepartmentMapper {
    long countByExample(TUserDepartmentExample example);

    int deleteByExample(TUserDepartmentExample example);

    @Delete({
            "delete from t_user_department",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into t_user_department (id, user_id, ",
            "dept_id, user_level, ",
            "create_time, create_user_id, ",
            "update_time, update_user_id, ",
            "remark1, remark2, ",
            "remark3)",
            "values (#{id,jdbcType=INTEGER}, #{userId,jdbcType=VARCHAR}, ",
            "#{deptId,jdbcType=INTEGER}, #{userLevel,jdbcType=VARCHAR}, ",
            "#{createTime,jdbcType=TIMESTAMP}, #{createUserId,jdbcType=VARCHAR}, ",
            "#{updateTime,jdbcType=TIMESTAMP}, #{updateUserId,jdbcType=VARCHAR}, ",
            "#{remark1,jdbcType=VARCHAR}, #{remark2,jdbcType=VARCHAR}, ",
            "#{remark3,jdbcType=VARCHAR})"
    })
    int insert(TUserDepartment record);

    int insertSelective(TUserDepartment record);

    List<TUserDepartment> selectByExample(TUserDepartmentExample example);

    @Select({
            "select",
            "id, user_id, dept_id, user_level, create_time, create_user_id, update_time, ",
            "update_user_id, remark1, remark2, remark3",
            "from t_user_department",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.adtec.daily.dao.project.TUserDepartmentMapper.BaseResultMap")
    TUserDepartment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TUserDepartment record, @Param("example") TUserDepartmentExample example);

    int updateByExample(@Param("record") TUserDepartment record, @Param("example") TUserDepartmentExample example);

    int updateByPrimaryKeySelective(TUserDepartment record);

    @Update({
            "update t_user_department",
            "set user_id = #{userId,jdbcType=VARCHAR},",
            "dept_id = #{deptId,jdbcType=INTEGER},",
            "user_level = #{userLevel,jdbcType=VARCHAR},",
            "create_time = #{createTime,jdbcType=TIMESTAMP},",
            "create_user_id = #{createUserId,jdbcType=VARCHAR},",
            "update_time = #{updateTime,jdbcType=TIMESTAMP},",
            "update_user_id = #{updateUserId,jdbcType=VARCHAR},",
            "remark1 = #{remark1,jdbcType=VARCHAR},",
            "remark2 = #{remark2,jdbcType=VARCHAR},",
            "remark3 = #{remark3,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TUserDepartment record);
}