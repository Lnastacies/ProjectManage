package com.adtec.daily.controller.user;

import com.adtec.daily.bean.common.Msg;
import com.adtec.daily.bean.user.TPrivilege;
import com.adtec.daily.service.user.TPrivilegeService;
import com.adtec.daily.service.user.TRolePrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @version V1.0
 * @Description: 权限相关类
 * @author: 张琪
 * @date: 2018/4/8
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
@Controller
public class TPrivilegeController {


    @Autowired
    TPrivilegeService tPrivilegeService;
    @Autowired
    TRolePrivilegeService tRolePrivilegeService;

    /**
     * 获取权限列表。
     *
     * @return
     */
    @RequestMapping("/privilege/getPrivileges")
    @ResponseBody
    public Msg getPrivileges() {
        List<TPrivilege> pros = tPrivilegeService.getAll();
        return Msg.success().add("resultList", pros);
    }


}
