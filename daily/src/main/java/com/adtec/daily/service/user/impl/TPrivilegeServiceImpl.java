package com.adtec.daily.service.user.impl;

import com.adtec.daily.bean.user.TPrivilege;
import com.adtec.daily.dao.user.TPrivilegeMapper;
import com.adtec.daily.service.project.TProjectService;
import com.adtec.daily.service.user.TPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version V1.0
 * @Description: 菜单权限实现类
 * @author: 张琪
 * @date: 2018/4/8
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
@Service
public class TPrivilegeServiceImpl implements TPrivilegeService {
	@Autowired
	TPrivilegeMapper tPrivilegeMapper;

	/**
	 * 查询所有权限信息
	 */
	public List<TPrivilege> getAll() {
		return tPrivilegeMapper.selectByExample(null);
	}

}
