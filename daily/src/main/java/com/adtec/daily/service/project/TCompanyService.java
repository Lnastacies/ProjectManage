package com.adtec.daily.service.project;

import com.adtec.daily.bean.project.TCompany;

import java.util.List;

/**
 * @version V1.0
 * @Description: 类名
 * @author: 胡浪
 * @date: 2018/4/13
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
public interface TCompanyService {

    /**
     * 根据部门id查询所属公司信息
     *
     * @param dept
     * @return
     */
    public TCompany selectByDeptId(int dept);

    /**
     * 查出所有的公司信息
     *
     *
     * @return
     */
    public List<TCompany> getAllCompany();
}
