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
 * 处理用户CRUD请求
 */
@Controller
public class TRolePrivilegeController {

      @Autowired
      TRolePrivilegeService tRolePrivilegeService;


    /**
     * 检查用户名是否可用
     *
     * @param userName
     * @return
     */
//    @ResponseBody
//    @RequestMapping("/userCheck")
//    public Msg userCheck(@RequestParam("userName") String userName) {
//        //先判断用户名是否是合法的表达式;
//        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
//        if (!userName.matches(regx)) {
//            return Msg.fail().add("va_msg", "用户名必须是6-16位数字和字母的组合或者2-5位中文");
//        }
//
//        //数据库用户名重复校验
//        boolean b = tUserService.userCheck(userName);
//        if (b) {
//            return Msg.success();
//        } else {
//            return Msg.fail().add("va_msg", "用户名不可用");
//        }
//    }

    /**
     * 角色权限更新
     * 1、支持JSR303校验
     * 2、导入Hibernate-Validator
     *
     * @return
     */
    @RequestMapping(value = "/savePrivilege", method = RequestMethod.POST)
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
     * 单个批量二合一
     * 批量删除：1-2-3
     * 单个删除：1
     *
     * @param ids
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value = "/user/{ids}", method = RequestMethod.DELETE)
//    public Msg updateUser(@PathVariable("ids") String ids) {
//        //批量删除
//        if (ids.contains("-")) {
//            List<String> del_ids = new ArrayList<String>();
//            String[] str_ids = ids.split("-");
//            //组装id的集合
//            for (String string : str_ids) {
//                del_ids.add(string);
//            }
//            tUserService.deleteBatch(del_ids);
//        } else {
//            tUserService.deleteUser(ids);
//        }
//        return Msg.success();
//    }


    /**
     * 权限更新方法
     *
     * @param tUser
     * @return
     */


//    @ResponseBody
//        @RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT)
//        public Msg updateUser(TUser tUser, HttpServletRequest request) {
//            System.out.println("请求体中的值：" + request.getParameter("userId"));
//            tUser.setUpdateTime(new Date());
//            tUserService.updateUser(tUser);
//            return Msg.success();
//        }
     /* 根据id查询员工
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getPrivileges", method = RequestMethod.GET)
    @ResponseBody
    public Msg getPrivilegesByRoleId(Integer roleId) {
        List<TRolePrivilege> list = tRolePrivilegeService.getPrivilegesByRoleId(roleId);
        return Msg.success().add("resultList",list);
    }


    /**
     * 获取权限列表。
     *
     *
     * @return
     */
//    @RequestMapping("/privileges")
//    @ResponseBody
//    public Msg getPrivileges() {
//        List<TPrivilege> pros = tPrivilegeService.getAll();
//        return Msg.success().add("resultList", pros);
//    }


}
