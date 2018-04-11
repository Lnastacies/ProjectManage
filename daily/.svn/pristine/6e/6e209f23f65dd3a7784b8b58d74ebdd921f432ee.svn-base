package com.adtec.daily.service.user;

import com.adtec.daily.bean.user.TUser;
import com.adtec.daily.bean.user.TUserExample;
import com.adtec.daily.dao.user.TUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TUserService {
	@Autowired
	TUserMapper tUserMapper;

	/**
	 * 查询所有用户信息
	 * @return
	 */
	public List<TUser> getAll() {
		return tUserMapper.selectAllUser();
	}

	/**
	 * 检验用户名是否可用
	 *
	 * @param userName
	 * @return  true：代表当前姓名可用   fasle：不可用
	 */
	public boolean userCheck(String userName) {
		TUserExample example = new TUserExample();
		TUserExample.Criteria criteria = example.createCriteria();
		criteria.andUserNameEqualTo(userName);
		long count = tUserMapper.countByExample(example);
		return count == 0;
	}

	/**
	 * 用户保存
	 * @param tUser
	 */
	public void saveUser(TUser tUser) {
		tUserMapper.insertSelective(tUser);
	}

	/**
	 * 按照用户id查询用户
	 * @param id
	 * @return
	 */
	public TUser getUser(String id) {
		TUser tUser = tUserMapper.selectByUserId(id);
		return tUser;
	}

	/**
	 * 用户登录查询
	 * @param user
	 * @return
	 */
	public List<TUser> getLoginUser(TUser user) {
	    TUserExample example = new TUserExample();
	    TUserExample.Criteria criteria = example.createCriteria();
        //criteria.andUserIdEqualTo(user.getUserName());
	    criteria.andUserNameEqualTo(user.getUserName());
	    criteria.andPasswordEqualTo(user.getPassword());
		List<TUser> list = tUserMapper.selectByExample(example);
		return list;
	}

	/**
	 * 用户更新
	 * @param tUser
	 */
	public void updateUser(TUser tUser) {
		tUserMapper.updateByPrimaryKeySelective(tUser);
	}



	/**
	 * 用户删除
	 * @param id
	 */
	public void deleteUser(String id) {
        tUserMapper.deleteByPrimaryKey(id);
	}

    /**
     * 批量删除用户
     * @param ids
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
     *
     * @param example
	 * @return
	 */
	public List<TUser> getUserInfoList(TUserExample example) {
        List<TUser> tUserList = tUserMapper.selectByExample(example);
		return tUserList;
	}

	/**
	 * 查询项目下所有用户
	 * @param user
	 * @return
	 */
	public List<TUser> getAllUserByProjectId(TUser user) {
		TUserExample example = new TUserExample();
		TUserExample.Criteria criteria = example.createCriteria();
		//criteria.andProjectIdEqualTo(user.getProjectId());
		example.setOrderByClause("user_id asc");
		List<TUser> list = tUserMapper.selectByExample(example);
		return list;
	}
}
