package com.adtec.daily.service.user.impl;

import com.adtec.daily.bean.user.Privilege;
import com.adtec.daily.bean.user.RolePrivilege;
import com.adtec.daily.bean.user.RolePrivilegeExample;
import com.adtec.daily.dao.user.PrivilegeMapper;
import com.adtec.daily.dao.user.RolePrivilegeMapper;
import com.adtec.daily.service.user.RolePrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version V1.0
 * @Description: 角色菜单实现类
 * @author: 张琪
 * @date: 2018/4/8
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
@Service
public class RolePrivilegeServiceImpl implements RolePrivilegeService {

	@Autowired
    RolePrivilegeMapper rolePrivilegeMapper;
	@Autowired
    PrivilegeMapper privilegeMapper;

    /**
     * 保存角色权限信息
     */
    @Override
	public void savePrivilege(RolePrivilege rolePrivilege) {
        rolePrivilegeMapper.insert(rolePrivilege);
	}

	/**
	 * 删除角色所有权限信息
	 */
    @Override
	public void deleteById(Integer roleId) {
        RolePrivilegeExample example = new RolePrivilegeExample();
        RolePrivilegeExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        int t = rolePrivilegeMapper.deleteByExample(example);
        System.out.println(t);
	}

	/**
	 * 按照角色id查询权限
	 */
    @Override
	public List<RolePrivilege> getPrivilegesByRoleId(Integer roleId) {
	    RolePrivilegeExample example = new RolePrivilegeExample();
	    RolePrivilegeExample.Criteria criteria = example.createCriteria();
	    criteria.andRoleIdEqualTo(roleId);
        List<RolePrivilege> list = rolePrivilegeMapper.selectByExample(example);
		return list;
	}

    /**
     * 按照用户id查询用户权限列表
     */
    @Override
    public List<Privilege> getPrivilegesByUserId(String userId) {
        List<Privilege> list = privilegeMapper.selectPrivilegesByUserId(userId);
        return list;
    }

}
