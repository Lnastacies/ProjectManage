package com.adtec.daily.service.user;

import com.adtec.daily.bean.user.TRole;
import com.adtec.daily.bean.user.TRoleExample;
import com.adtec.daily.dao.user.TRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TRoleService {
	@Autowired
	TRoleMapper tRoleMapper;

	/**
	 * 查询所有角色信息
	 * @return
	 */
	public List<TRole> getAll(TRoleExample example) {
		return tRoleMapper.selectByExample(example);
	}

	/**
	 * 检验角色名是否可用
	 *
	 * @param roleName
	 * @return  true：代表当前姓名可用   fasle：不可用
	 */
	public boolean roleCheck(String roleName) {
		TRoleExample example = new TRoleExample();
		TRoleExample.Criteria criteria = example.createCriteria();
		criteria.andRoleNameEqualTo(roleName);
		long count = tRoleMapper.countByExample(example);
		return count == 0;
	}

	/**
	 * 角色保存
	 * @param tRole
	 */
	public void saveRole(TRole tRole) {
		tRoleMapper.insertSelective(tRole);
	}

	/**
	 * 按照用户id查询用户
	 * @param id
	 * @return
	 */
	public TRole getRole(Integer id) {
		TRole tUser = tRoleMapper.selectByPrimaryKey(id);
		return tUser;
	}

	/**
	 * 角色更新
	 * @param tRole
	 */
	public void updateRole(TRole tRole) {
		tRoleMapper.updateByPrimaryKeySelective(tRole);
	}

	/**
	 * 角色删除
	 * @param id
	 */
	public void deleteRole(Integer id) {
        tRoleMapper.deleteByPrimaryKey(id);
	}

    /**
     * 批量删除角色
     * @param ids
     */
	public void deleteBatch(List<Integer> ids) {
        TRoleExample example = new TRoleExample();
		TRoleExample.Criteria criteria = example.createCriteria();
		//delete from xxx where emp_id in(1,2,3)
		criteria.andRoleIdIn(ids);
        tRoleMapper.deleteByExample(example);
	}

}
