package com.adtec.daily.service.project.impl;

import com.adtec.daily.bean.project.TDepartment;
import com.adtec.daily.dao.project.TDepartmentMapper;
import com.adtec.daily.service.project.TDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version V1.0
 * @Description: 类名
 * @author: 胡浪
 * @date: 2018/4/9
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
@Service
public class TDepartmentServiceImpl implements TDepartmentService{

    @Autowired
    private TDepartmentMapper tDepartmentMapper;

    /**
     * 查询部门信息
     */
    public List<TDepartment> getDepts() {
        List<TDepartment> list = tDepartmentMapper.selectByExample(null);
        return list;
    }

    /**
     * 登陆带出部门信息
     */
    public TDepartment selectByUserId(String userId) {
        TDepartment dept = tDepartmentMapper.selectByUserId(userId);
        return  dept;
    }

    /**
     * 查询所属公司的部门信息
     */
    public List<TDepartment> getDepartmentsByCompanyId(Integer companyId) {
        List<TDepartment> list = tDepartmentMapper.getDepartmentsByCompanyId(companyId);
        return list;
    }
}
