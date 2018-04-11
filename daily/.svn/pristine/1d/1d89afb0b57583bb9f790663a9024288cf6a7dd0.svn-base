package com.adtec.daily.controller.user;

import com.adtec.daily.bean.common.Msg;
import com.adtec.daily.bean.user.TRolePrivilege;
import com.adtec.daily.bean.user.TUser;
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
 * 处理用户CRUD请求
 */
@Controller
public class TUserController {

    @Autowired
    TProjectService tProjectService;
    @Autowired
    TUserService tUserService;
    @Autowired
    TRolePrivilegeService tRolePrivilegeService;

    /**
     * 检查用户名是否可用
     *
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping("/userCheck")
    public Msg userCheck(@RequestParam("userName") String userName) {
        //先判断用户名是否是合法的表达式;
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
        if (!userName.matches(regx)) {
            return Msg.fail().add("va_msg", "用户名必须是6-16位数字和字母的组合或者2-5位中文");
        }

        //数据库用户名重复校验
        boolean b = tUserService.userCheck(userName);
        if (b) {
            return Msg.success();
        } else {
            return Msg.fail().add("va_msg", "用户名不可用");
        }
    }

    /**
     * 员工保存
     * 1、支持JSR303校验
     * 2、导入Hibernate-Validator
     *
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
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
    @RequestMapping(value = "/user/{ids}", method = RequestMethod.DELETE)
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
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT)
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
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
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
    @RequestMapping("/users")
    @ResponseBody
    public Msg getUserWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 这不是一个分页查询
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
     // startPage后面紧跟的这个查询就是一个分页查询
        List<TUser> pros = tUserService.getAll();
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(pros, 5);
        return Msg.success().add("pageInfo", page);
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @RequestMapping(value ="/userLogin", method = RequestMethod.POST)
    @ResponseBody
    public Msg getLoginUser( HttpServletRequest request , TUser user , String userName,String password,@RequestParam(value = "password") String pp) {
        List<TUser> list = tUserService.getLoginUser(user);
        if (list != null && list.size() > 0) {
            TUser resultUser = list.get(0);
            Integer roleId = resultUser.getRoleId();
            List<TRolePrivilege> rolePrivileges = tRolePrivilegeService.getPrivilegesByRoleId(roleId);
            List privilegeList = new ArrayList<String>();
            for(TRolePrivilege rolePrivilege : rolePrivileges){
                privilegeList.add(rolePrivilege.getPrivilegeId());
            }
            resultUser.setPrivilegeList(privilegeList);
            request.getSession().setAttribute("user",resultUser);
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
    @RequestMapping(value ="/userExit", method = RequestMethod.POST)
    @ResponseBody
    public Msg userExit( HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null){
            return Msg.fail();
        }else{
            request.getSession().removeAttribute("user");
            return Msg.success();
        }
    }
}
