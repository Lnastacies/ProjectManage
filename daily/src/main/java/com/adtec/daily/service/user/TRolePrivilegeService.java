package com.adtec.daily.service.user;

import com.adtec.daily.bean.user.TRolePrivilege;
import com.adtec.daily.bean.user.TRolePrivilegeExample;
import com.adtec.daily.bean.user.TUserExample;
import com.adtec.daily.dao.user.TRolePrivilegeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TRolePrivilegeService {
	@Autowired
	TRolePrivilegeMapper tRolePrivilegeMapper;


    /**
     * 保存角色权限信息
     * @return
     */
	public void savePrivilege(TRolePrivilege tRolePrivilege) {
        tRolePrivilegeMapper.insert(tRolePrivilege);
	}

	/**
	 * 删除角色所有权限信息
	 * @return
	 */
	public void deleteById(Integer roleId) {
        TRolePrivilegeExample example = new TRolePrivilegeExample();
        TRolePrivilegeExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        int t = tRolePrivilegeMapper.deleteByExample(example);
        System.out.println(t);
	}

	/**
	 * 检验用户名是否可用
	 *
	 * @param userName
	 * @return  true：代表当前姓名可用   fasle：不可用
	 */
//	public boolean userCheck(String userName) {
		TUserExample example = new TUserExample();
		TUserExample.Criteria criteria = example.createCriteria();
//		criteria.andUserNameEqualTo(userName);
//		long count = tUserMapper.countByExample(example);
//		return count == 0;
//	}

	/**
	 * 用户保存
	 * @param tUser
	 */
//	public void saveUser(TUser tUser) {
//		tUserMapper.insertSelective(tUser);
//	}

	/**
	 * 按照角色id查询权限
	 * @param roleId
	 * @return
	 */
	public List<TRolePrivilege> getPrivilegesByRoleId(Integer roleId) {
	    TRolePrivilegeExample example = new TRolePrivilegeExample();
	    TRolePrivilegeExample.Criteria criteria = example.createCriteria();
	    criteria.andRoleIdEqualTo(roleId);
        List<TRolePrivilege> list = tRolePrivilegeMapper.selectByExample(example);
		return list;
	}

	/**
	 * 用户更新
	 * @param tUser
	 */
//	public void updateUser(TUser tUser) {
//		tUserMapper.updateByPrimaryKeySelective(tUser);
//	}

	/**
	 * 项目保存
	 * @param tProject
	 */
//	public void saveProject(TProject tProject) {
//        tUserMapper.insertSelective(tProject);
//	}

	/**
	 * 检验项目编号是否可用
	 * 
	 * @param projectCode
	 * @return  true：代表当前姓名可用   fasle：不可用
	 */
//	public boolean checkProject(String projectCode) {
//		TProjectExample example = new TProjectExample();
//		Criteria criteria = example.createCriteria();
//		criteria.andProjectCodeEqualTo(projectCode);
//		long count = tProjectMapper.countByExample(example);
//		return count == 0;
//	}

	/**
	 * 按照主键id查询项目信息
	 * @param id
	 * @return
	 */
//	public TProject getProject(int id) {
//        TProject tProject = tProjectMapper.selectByPrimaryKey(id);
//		return tProject;
//	}

	/**
	 * 项目更新
	 * @param tProject
	 */
//	public void updateProject(TProject tProject) {
//        tProjectMapper.updateByPrimaryKeySelective(tProject);
//	}

	/**
	 * 用户删除
	 * @param id
	 */
//	public void deleteUser(String id) {
//        tUserMapper.deleteByPrimaryKey(id);
//	}

    /**
     * 批量删除用户
     * @param ids
     */
//	public void deleteBatch(List<String> ids) {
//        TUserExample example = new TUserExample();
//		TUserExample.Criteria criteria = example.createCriteria();
//		//delete from xxx where emp_id in(1,2,3)
//		criteria.andUserIdIn(ids);
//        tUserMapper.deleteByExample(example);
//	}

}
