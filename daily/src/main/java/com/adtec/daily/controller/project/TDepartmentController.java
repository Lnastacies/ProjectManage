package com.adtec.daily.controller.project;

import com.adtec.daily.bean.common.Msg;
import com.adtec.daily.bean.project.TDepartment;
import com.adtec.daily.service.project.TDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @version V1.0
 * @Description: 部门信息
 * @author: 胡浪
 * @date: 2018/4/9
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
@Controller
public class TDepartmentController {

    @Autowired
    private TDepartmentService tDepartmentService;

    /**
     * 返回所有的部门信息
     */
    @RequestMapping("/user/getDepartment")
    @ResponseBody
    public Msg getDepts(){
        //查出的所有部门信息
        List<TDepartment> list = tDepartmentService.getDepts();
        return Msg.success().add("depts", list);
    }
}
