package com.adtec.daily.service.user.impl;

import com.adtec.daily.bean.user.TRole;
import com.adtec.daily.bean.user.TRoleExample;
import com.adtec.daily.dao.user.TRoleMapper;
import com.adtec.daily.service.user.TRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version V1.0
 * @Description: 用户角色实现类
 * @author: 张琪
 * @date: 2018/4/8
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
@Service
public class TRoleServiceImpl implements TRoleService{
	@Autowired
	TRoleMapper tRoleMapper;

	/**
	 * 查询所有角色信息
	 */
	public List<TRole> getAll(TRoleExample example) {
		return tRoleMapper.selectByExample(example);
	}

	/**
	 * 检验角色名是否可用
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
	 */
	public void saveRole(TRole tRole) {
		tRoleMapper.insertSelective(tRole);
	}

	/**
	 * 按照用户id查询用户
	 */
	public TRole getRole(Integer id) {
		TRole tUser = tRoleMapper.selectByPrimaryKey(id);
		return tUser;
	}

	/**
	 * 角色更新
	 */
	public void updateRole(TRole tRole) {
		tRoleMapper.updateByPrimaryKeySelective(tRole);
	}

	/**
	 * 角色删除
	 */
	public void deleteRole(Integer id) {
        tRoleMapper.deleteByPrimaryKey(id);
	}

    /**
     * 批量删除角色
     */
	public void deleteBatch(List<Integer> ids) {
        TRoleExample example = new TRoleExample();
		TRoleExample.Criteria criteria = example.createCriteria();
		//delete from xxx where emp_id in(1,2,3)
		criteria.andRoleIdIn(ids);
        tRoleMapper.deleteByExample(example);
	}

}
