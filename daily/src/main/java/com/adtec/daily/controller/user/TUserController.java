package com.adtec.daily.controller.user;

import com.adtec.daily.bean.common.Msg;
import com.adtec.daily.bean.project.TDepartment;
import com.adtec.daily.bean.project.TProject;
import com.adtec.daily.bean.user.TRolePrivilege;
import com.adtec.daily.bean.user.TUser;
import com.adtec.daily.service.project.TDepartmentService;
import com.adtec.daily.service.project.TProjectService;
import com.adtec.daily.service.user.TRolePrivilegeService;
import com.adtec.daily.service.user.TUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

/**
 * @version V1.0
 * @Description: 用户管理
 * @author: 张琪
 * @date: 2018/4/8
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
@Controller
public class TUserController {

    @Autowired
    TProjectService tProjectService;
    @Autowired
    TUserService tUserService;
    @Autowired
    TRolePrivilegeService tRolePrivilegeService;
    @Autowired
    TDepartmentService tDepartmentService;


    /**
     * 检查邮箱是否可用
     *
     * @param email
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/userCheck")
    public Msg userCheck(@RequestParam("email") String email) {

        //数据库邮箱重复校验
        boolean b = tUserService.userCheck(email);
        if (b) {
            return Msg.success();
        } else {
            return Msg.fail().add("va_msg", "邮箱不可用");
        }
    }

    /**
     * 员工保存
     * 1、支持JSR303校验
     * 2、导入Hibernate-Validator
     *
     * @return
     */
    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveUser(@Valid TUser tUser, BindingResult result) {
        if (result.hasErrors()) {
            //校验失败，应该返回失败，在模态框中显示校验失败的错误信息
            Map<String, Object> map = new HashMap<String, Object>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                System.out.println("错误的字段名：" + fieldError.getField());
                System.out.println("错误信息：" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        } else {
            tUser.setUserId("USER" + String.valueOf(System.currentTimeMillis()));
            tUser.setCreateTime(new Date());
            tUserService.saveUser(tUser);
            return Msg.success();
        }

    }

    /**
     * 单个批量二合一
     * 批量删除：1-2-3
     * 单个删除：1
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/delete/{ids}", method = RequestMethod.DELETE)
    public Msg updateUser(@PathVariable("ids") String ids) {
        //批量删除
        if (ids.contains("-")) {
            List<String> del_ids = new ArrayList<String>();
            String[] str_ids = ids.split("-");
            //组装id的集合
            for (String string : str_ids) {
                del_ids.add(string);
            }
            tUserService.deleteBatch(del_ids);
        } else {
            tUserService.deleteUser(ids);
        }
        return Msg.success();
    }


    /**
     * 用户更新方法
     *
     * @param tUser
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/update/{userId}", method = RequestMethod.PUT)
    public Msg updateUser(TUser tUser, HttpServletRequest request) {
        System.out.println("请求体中的值：" + request.getParameter("userId"));
        tUser.setUpdateTime(new Date());
        tUserService.updateUser(tUser);
        return Msg.success();
    }

    /**
     * 根据id查询员工
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/selectUserById/{id}", method = RequestMethod.GET)
    public Msg getUser(@PathVariable("id") String id) {
        TUser tUser = tUserService.getUser(id);
        return Msg.success().add("user", tUser);
    }


    /**
     * 导入jackson包。
     *
     * @param pn
     * @return
     */
    @RequestMapping("/user/getUsers")
    @ResponseBody
    public Msg getUserWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {

        PageHelper.startPage(pn, 5);
        List<TUser> pros = tUserService.getAll();
        PageInfo page = new PageInfo(pros, 5);
        return Msg.success().add("pageInfo", page);
    }

    /**
     * 根据id查询用户，并分页
     *
     * @param pn
     * @param userName
     * @return
     */
    @RequestMapping("/user/getUserInfoByUserName")
    @ResponseBody
    public Msg getUserWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @RequestParam String userName) {

        PageHelper.startPage(pn, 5);
        List<TUser> pros = tUserService.getUserInfoByUserName(userName);
        PageInfo page = new PageInfo(pros, 5);
        return Msg.success().add("pageInfo", page);
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/userLogin", method = RequestMethod.POST)
    @ResponseBody
    public Msg getLoginUser(HttpServletRequest request, TUser user, @RequestParam(value = "password") String pp) {
        List<TUser> list = tUserService.getLoginUser(user);
        if (list != null && list.size() > 0) {
            TUser resultUser = list.get(0);
            Integer roleId = resultUser.getRoleId();
            List<TRolePrivilege> rolePrivileges = tRolePrivilegeService.getPrivilegesByRoleId(roleId);
            List privilegeList = new ArrayList<String>();
            for (TRolePrivilege rolePrivilege : rolePrivileges) {
                privilegeList.add(rolePrivilege.getPrivilegeId());
            }
            resultUser.setPrivilegeList(privilegeList);
            request.getSession().setAttribute("user", resultUser);
            String userId = resultUser.getUserId();
            TProject project = tProjectService.selectByUserId(userId);
            request.getSession().setAttribute("project", project);
            TDepartment department = tDepartmentService.selectByUserId(userId);
            request.getSession().setAttribute("department", department);
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/userExit", method = RequestMethod.POST)
    @ResponseBody
    public Msg userExit(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return Msg.fail();
        } else {
            request.getSession().removeAttribute("user");
            request.getSession().removeAttribute("project");
            request.getSession().removeAttribute("department");
            return Msg.success();
        }
    }

    /**
     * 用户修改密码
     *
     * @param tUser
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/updatePasswprd", method = RequestMethod.POST)
    public Msg updatePasswprd(TUser tUser, HttpServletRequest request) {
        tUserService.updateUser(tUser);
        return Msg.success();
    }
}
