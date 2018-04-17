package com.adtec.daily.service.user;

import com.adtec.daily.bean.user.TRolePrivilege;

import java.util.List;

/**
 * @version V1.0
 * @Description: 角色菜单接口类
 * @author: 张琪
 * @date: 2018/4/8
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
public interface TRolePrivilegeService {

    /**
     * 保存角色权限信息
     *
     * @param tRolePrivilege
     */
    public void savePrivilege(TRolePrivilege tRolePrivilege);

    /**
     * 删除角色所有权限信息
     *
     * @param roleId
     */
    public void deleteById(Integer roleId);

    /**
     * 按照角色id查询权限
     *
     * @param roleId
     * @return
     */
    public List<TRolePrivilege> getPrivilegesByRoleId(Integer roleId);
}
