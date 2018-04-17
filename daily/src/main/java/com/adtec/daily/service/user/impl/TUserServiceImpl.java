package com.adtec.daily.service.user.impl;

import com.adtec.daily.bean.user.*;
import com.adtec.daily.dao.project.TUserDepartmentMapper;
import com.adtec.daily.dao.user.TUserMapper;
import com.adtec.daily.dao.user.TUserProjectMapper;
import com.adtec.daily.service.user.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version V1.0
 * @Description: 用户信息实现类
 * @author: 张琪
 * @date: 2018/4/8
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
@Service
public class TUserServiceImpl implements TUserService {

	@Autowired
	TUserMapper tUserMapper;
	@Autowired
    TUserProjectMapper tUserProjectMapper;
	@Autowired
    TUserDepartmentMapper tUserDepartmentMapper;

	/**
	 * 查询所有用户信息
	 */
	public List<TUser> getAll() {
		return tUserMapper.selectAllUser();
	}

	/**
	 * 检验邮箱是否可用
	 *
	 * @param email
	 * @return  true：代表当前邮箱可用   fasle：不可用
	 */
	public boolean userCheck(String email) {
		TUserExample example = new TUserExample();
		TUserExample.Criteria criteria = example.createCriteria();
		criteria.andEmailEqualTo(email);
		long count = tUserMapper.countByExample(example);
		return count == 0;
	}

	/**
	 * 用户保存
	 * @param tUser
	 */
	public void saveUser(TUser tUser) {
		tUserMapper.insertSelective(tUser);
		TUserDepartment tUserDepartment =  new TUserDepartment();
        tUserDepartment.setDeptId(tUser.getDeptId());
        tUserDepartment.setUserId(tUser.getUserId());
        tUserDepartmentMapper.insertSelective(tUserDepartment);
	}

	/**
	 * 按照用户id查询用户
	 */
	public TUser getUser(String id) {
		TUser tUser = tUserMapper.selectByUserId(id);
		return tUser;
	}

	/**
	 * 按照用户姓名查询用户
	 */
	public List<TUser> getUserInfoByUserName(String userName) {
		TUserExample example = new TUserExample();
		TUserExample.Criteria criteria = example.createCriteria();
		criteria.andUserNameLike("%"+userName+"%");
        List<TUser> list = tUserMapper.selectByExample(example);
		return list;
	}

	/**
	 * 用户登录查询
	 */
	public List<TUser> getLoginUser(TUser user) {
	    TUserExample example = new TUserExample();
	    TUserExample.Criteria criteria = example.createCriteria();
        //criteria.andUserIdEqualTo(user.getUserName());
	    criteria.andEmailEqualTo(user.getEmail());
	    criteria.andPasswordEqualTo(user.getPassword());
		List<TUser> list = tUserMapper.selectByExample(example);
		return list;
	}

	/**
	 * 用户更新
	 */
	public void updateUser(TUser tUser) {
		tUserMapper.updateByPrimaryKeySelective(tUser);
	}

	/**
	 * 用户删除
	 */
	public void deleteUser(String id) {
        tUserMapper.deleteByPrimaryKey(id);
	}

    /**
     * 批量删除用户
     */
	public void deleteBatch(List<String> ids) {
        TUserExample example = new TUserExample();
		TUserExample.Criteria criteria = example.createCriteria();
		//delete from xxx where emp_id in(1,2,3)
		criteria.andUserIdIn(ids);
        tUserMapper.deleteByExample(example);
	}

	/**
	 * 日报列表查询
	 */
	public List<TUser> getUserInfoList(TUserExample example) {
        List<TUser> tUserList = tUserMapper.selectByExample(example);
		return tUserList;
	}

	/**
	 * 根据项目id及公司id查询项目下所有用户
	 */
	public List<TUser> getAllUserByProjectIdAndCompanyId(int projectId,int companyId) {
		List<TUser> list = tUserMapper.selectAllUserByProjectIdAndCompanyId(projectId,companyId);
		return list;
	}

    /**
     * 根据用户id删除项目成员
     * @param userId
     * @return
     */
    public void deleteByUserId(String userId) {
        TUserProjectExample example = new TUserProjectExample();
        TUserProjectExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        tUserProjectMapper.deleteByExample(example);
}

    /**
     * 添加项目成员
     */
    public void projectUserSave(TUserProject tUserProject) {
        tUserProjectMapper.insertSelective(tUserProject);
    }

}
