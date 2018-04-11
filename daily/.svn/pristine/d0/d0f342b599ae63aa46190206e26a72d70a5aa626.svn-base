package com.adtec.daily.service.user;

import com.adtec.daily.bean.user.TPrivilege;
import com.adtec.daily.dao.user.TPrivilegeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TPrivilegeService {
	@Autowired
	TPrivilegeMapper tPrivilegeMapper;

	/**
	 * 查询所有权限信息
	 * @return
	 */
	public List<TPrivilege> getAll() {
		return tPrivilegeMapper.selectByExample(null);
	}

	/**
	 * 检验用户名是否可用
	 *
	 * @param userName
	 * @return  true：代表当前姓名可用   fasle：不可用
	 */
//	public boolean userCheck(String userName) {
//		TUserExample example = new TUserExample();
//		TUserExample.Criteria criteria = example.createCriteria();
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
	 * 按照用户id查询用户
	 * @param id
	 * @return
	 */
//	public TUser getUser(String id) {
//		TUser tUser = tUserMapper.selectByUserId(id);
//		return tUser;
//	}

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
