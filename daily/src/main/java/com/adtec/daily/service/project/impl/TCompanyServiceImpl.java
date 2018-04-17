package com.adtec.daily.service.project.impl;

import com.adtec.daily.bean.project.TCompany;
import com.adtec.daily.dao.project.TCompanyMapper;
import com.adtec.daily.service.project.TCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TCompanyServiceImpl implements TCompanyService{
    @Autowired
    TCompanyMapper tCompanyMapper;

    /**
     * 根据部门id查询所属公司信息
     */
    public TCompany selectByDeptId(int dept) {
        TCompany company = tCompanyMapper.selectByDeptId(dept);
        return  company;
    }

    /**
     * 查询所有公司信息
     */
    public List<TCompany> getAllCompany() {
        List<TCompany> list = tCompanyMapper.selectAllCompany();
        return  list;
    }
}
