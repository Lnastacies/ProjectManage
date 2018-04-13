package com.adtec.daily.service.project;

import com.adtec.daily.bean.project.TDepartment;

import java.util.List;

/**
 * @version V1.0
 * @Description: 公司部门信息接口类
 * @author: 胡浪
 * @date: 2018/4/13
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
public interface TDepartmentService {

    /**
     * 查询部门信息
     *
     * @return
     */
    public List<TDepartment> getDepts();

    /**
     * 登陆带出部门信息
     *
     * @param userId
     * @return
     */
    public TDepartment selectByUserId(String userId);
}
