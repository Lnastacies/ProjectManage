package com.adtec.daily.service.user.impl;

import com.adtec.daily.bean.user.TRolePrivilege;
import com.adtec.daily.bean.user.TRolePrivilegeExample;
import com.adtec.daily.bean.user.TUserExample;
import com.adtec.daily.dao.user.TRolePrivilegeMapper;
import com.adtec.daily.service.user.TRolePrivilegeService;
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
public class TRolePrivilegeServiceImpl implements TRolePrivilegeService {

	@Autowired
	TRolePrivilegeMapper tRolePrivilegeMapper;

    /**
     * 保存角色权限信息
     */
	public void savePrivilege(TRolePrivilege tRolePrivilege) {
        tRolePrivilegeMapper.insert(tRolePrivilege);
	}

	/**
	 * 删除角色所有权限信息
	 */
	public void deleteById(Integer roleId) {
        TRolePrivilegeExample example = new TRolePrivilegeExample();
        TRolePrivilegeExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        int t = tRolePrivilegeMapper.deleteByExample(example);
        System.out.println(t);
	}

	/**
	 * 按照角色id查询权限
	 */
	public List<TRolePrivilege> getPrivilegesByRoleId(Integer roleId) {
	    TRolePrivilegeExample example = new TRolePrivilegeExample();
	    TRolePrivilegeExample.Criteria criteria = example.createCriteria();
	    criteria.andRoleIdEqualTo(roleId);
        List<TRolePrivilege> list = tRolePrivilegeMapper.selectByExample(example);
		return list;
	}

}
