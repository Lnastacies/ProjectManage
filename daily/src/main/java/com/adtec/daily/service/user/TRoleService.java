package com.adtec.daily.service.user;

import com.adtec.daily.bean.user.TRole;
import com.adtec.daily.bean.user.TRoleExample;

import java.util.List;

/**
 * @version V1.0
 * @Description: 用户角色接口类
 * @author: 张琪
 * @date: 2018/4/8
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
public interface TRoleService {

    /**
     * 查询所有角色信息
     *
     * @param example
     * @return
     */
    public List<TRole> getAll(TRoleExample example);

    /**
     * 检验角色名是否可用
     *
     * @param roleName
     * @return  true：代表当前姓名可用   fasle：不可用
     */
    public boolean roleCheck(String roleName);

    /**
     * 角色保存
     *
     * @param tRole 角色信息
     */
    public void saveRole(TRole tRole);

    /**
     * 按照用户id查询用户
     *
     * @param id
     * @return
     */
    public TRole getRole(Integer id);

    /**
     * 角色更新
     *
     * @param tRole
     */
    public void updateRole(TRole tRole);

    /**
     * 角色删除
     *
     * @param id
     */
    public void deleteRole(Integer id);

    /**
     * 批量删除角色
     *
     * @param ids
     */
    public void deleteBatch(List<Integer> ids);
}
