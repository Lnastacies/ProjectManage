package com.adtec.daily.service.user;

import com.adtec.daily.bean.user.TPrivilege;

import java.util.List;

/**
 * @version V1.0
 * @Description: 菜单权限接口类
 * @author: 张琪
 * @date: 2018/4/8
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
public interface TPrivilegeService {

    /**
     * 查询所有权限信息
     *
     * @return
     */
    public List<TPrivilege> getAll();


}
