package com.adtec.daily.service.user;

import com.adtec.daily.bean.user.TUser;
import com.adtec.daily.bean.user.TUserExample;
import com.adtec.daily.bean.user.TUserProject;

import java.util.List;

/**
 * @version V1.0
 * @Description: 用户信息接口类
 * @author: 张琪
 * @date: 2018/4/8
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
public interface TUserService {

    /**
     * 查询所有用户信息
     *
     * @return
     */
    public List<TUser> getAll();

    /**
     * 检验邮箱是否可用
     *
     * @param email
     * @return  true：代表当前姓名可用   fasle：不可用
     */
    public boolean userCheck(String email);

    /**
     * 用户保存
     *
     * @param tUser
     */
    public void saveUser(TUser tUser);

    /**
     * 按照用户id查询用户
     *
     * @param id
     * @return
     */
    public TUser getUser(String id);

    /**
     * 按照用户姓名查询用户
     *
     * @param userName
     * @return
     */
    public List<TUser> getUserInfoByUserName(String userName);

    /**
     * 用户登录查询
     *
     * @param user
     * @return
     */
    public List<TUser> getLoginUser(TUser user);

    /**
     * 用户更新
     *
     * @param tUser
     */
    public void updateUser(TUser tUser);

    /**
     * 用户删除
     *
     * @param id
     */
    public void deleteUser(String id);

    /**
     * 批量删除用户
     *
     * @param ids
     */
    public void deleteBatch(List<String> ids);

    /**
     * 日报列表查询
     *
     * @param example
     * @return
     */
    public List<TUser> getUserInfoList(TUserExample example);

    /**
     * 根据项目id及公司id查询项目下所有用户
     *
     * @param projectId
     * @param companyId
     * @return
     */
    public List<TUser> getAllUserByProjectIdAndCompanyId(int projectId, int companyId);

    /**
     * 根据用户id删除项目成员
     *
     * @param userId
     */
    public void deleteByUserId(String userId);

    /**
     * 添加项目成员
     *
     * @param tUserProject
     */
    public void projectUserSave(TUserProject tUserProject);
}
