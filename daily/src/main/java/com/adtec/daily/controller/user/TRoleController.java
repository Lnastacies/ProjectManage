package com.adtec.daily.controller.user;

import com.adtec.daily.bean.common.Msg;
import com.adtec.daily.bean.user.TRole;
import com.adtec.daily.bean.user.TRoleExample;
import com.adtec.daily.service.user.TRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/**
 * 处理角色CRUD请求
 */
@Controller
public class TRoleController {

    @Autowired
    TRoleService tRoleService;


    /**
     * 检查角色名是否可用
     *
     * @param roleName
     * @return
     */
    @ResponseBody
    @RequestMapping("/roleCheck")
    public Msg userCheck(@RequestParam("roleName") String roleName) {
        //先判断角色名是否是合法的表达式;
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,10})";
        if (!roleName.matches(regx)) {
            return Msg.fail().add("va_msg", "角色名必须是6-16位数字和字母的组合或者2-10位中文");
        }

        //数据库角色名重复校验
        boolean b = tRoleService.roleCheck(roleName);
        if (b) {
            return Msg.success();
        } else {
            return Msg.fail().add("va_msg", "角色名不可用");
        }
    }

    /**
     * 角色保存
     * 1、支持JSR303校验
     * 2、导入Hibernate-Validator
     *
     * @return
     */
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveRole(@Valid TRole tRole, BindingResult result) {
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
            tRole.setCreateTime(new Date());
            tRoleService.saveRole(tRole);
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
    @RequestMapping(value = "/role/{ids}", method = RequestMethod.DELETE)
    public Msg updateRole(@PathVariable("ids") String ids) {
        //批量删除
        if (ids.contains("-")) {
            List<Integer> del_ids = new ArrayList<Integer>();
            String[] str_ids = ids.split("-");
            //组装id的集合
            for (String string : str_ids) {
                del_ids.add(Integer.parseInt(string));
            }
            tRoleService.deleteBatch(del_ids);
        } else {
            tRoleService.deleteRole(Integer.parseInt(ids));
        }
        return Msg.success();
    }


    /**
     * 角色更新方法
     *
     * @param tRole
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/role/{roleId}", method = RequestMethod.PUT)
    public Msg updateUser(TRole tRole, HttpServletRequest request) {
        System.out.println("请求体中的值：" + request.getParameter("roleId"));
        tRole.setUpdateTime(new Date());
        tRoleService.updateRole(tRole);
        return Msg.success();
    }

    /**
     * 根据id查询角色
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Msg getUser(@PathVariable("id") Integer id) {
        TRole tRole = tRoleService.getRole(id);
        return Msg.success().add("role",tRole );
    }


    /**
     * 导入jackson包。
     *
     * @param pn
     * @return
     */
    @RequestMapping("/roles")
    @ResponseBody
    public Msg getRoleWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 这不是一个分页查询
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
     // startPage后面紧跟的这个查询就是一个分页查询
        TRoleExample example = new TRoleExample();
        example.setOrderByClause("create_time asc");
        List<TRole> pros = tRoleService.getAll(example);
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(pros, 5);
        return Msg.success().add("pageInfo", page);
    }


}