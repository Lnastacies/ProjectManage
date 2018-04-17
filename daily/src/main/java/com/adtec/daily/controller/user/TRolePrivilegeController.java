package com.adtec.daily.controller.user;

import com.adtec.daily.bean.common.Msg;
import com.adtec.daily.bean.user.TRolePrivilege;
import com.adtec.daily.service.user.TRolePrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @version V1.0
 * @Description: 角色权限管理
 * @author: 张琪
 * @date: 2018/4/8
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
@Controller
public class TRolePrivilegeController {

      @Autowired
      TRolePrivilegeService tRolePrivilegeService;

    /**
     * 角色权限更新
     * 1、支持JSR303校验
     * 2、导入Hibernate-Validator
     *
     * @return
     */
    @RequestMapping(value = "rolePrivilege/savePrivilege", method = RequestMethod.POST)
    @ResponseBody
    public Msg savePrivileges(Integer roleId, String[] privilegeList){
        //先删除角色的权限
        tRolePrivilegeService.deleteById(roleId);
        //保存角色的权限
        for(String id : privilegeList){
            Integer privilegeId = Integer.parseInt(id);
            TRolePrivilege rolePrivilege = new TRolePrivilege();
            rolePrivilege.setRoleId(roleId);
            rolePrivilege.setPrivilegeId(privilegeId);
            rolePrivilege.setCreateTime(new Date());
            tRolePrivilegeService.savePrivilege(rolePrivilege);
        }
            return Msg.success();

    }



     /**
      *  根据id查询权限
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/rolePrivilege/getPrivileges", method = RequestMethod.GET)
    @ResponseBody
    public Msg getPrivilegesByRoleId(Integer roleId) {
        List<TRolePrivilege> list = tRolePrivilegeService.getPrivilegesByRoleId(roleId);
        return Msg.success().add("resultList",list);
    }
    
}
