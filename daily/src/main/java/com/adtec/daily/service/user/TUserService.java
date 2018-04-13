package com.adtec.daily.service.user;

import com.adtec.daily.bean.common.Msg;
import com.adtec.daily.bean.user.TUser;
import com.adtec.daily.bean.user.TUserExample;
import com.adtec.daily.bean.user.TUserProject;
import com.adtec.daily.bean.user.TUserProjectExample;
import com.adtec.daily.dao.user.TUserMapper;
import com.adtec.daily.dao.user.TUserProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
public class TUserService {
	@Autowired
	TUserMapper tUserMapper;
	@Autowired
    TUserProjectMapper tUserProjectMapper;

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
	 * 按照用户姓名查询用户
	 * @param userName
	 * @return
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
	 * @param user
	 * @return
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
	 * 根据项目id及公司id查询项目下所有用户
	 * @param projectId
	 * @param companyId
	 * @return
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
     *添加项目成员
     *
     * @param tUserProject
     * @return
     */
    public void projectUserSave(TUserProject tUserProject) {
        tUserProjectMapper.insertSelective(tUserProject);
    }

}
