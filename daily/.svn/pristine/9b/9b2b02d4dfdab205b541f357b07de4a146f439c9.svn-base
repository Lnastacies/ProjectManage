package com.adtec.daily.controller.project;

import com.adtec.daily.bean.common.Msg;
import com.adtec.daily.bean.project.TProject;
import com.adtec.daily.bean.project.TProjectExample;
import com.adtec.daily.service.project.TProjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/**
 * 处理项目CRUD请求
 */
@Controller
public class TProjectController {

    @Autowired
    TProjectService tProjectService;

    /**
     * 项目更新方法
     *
     * @param tProject
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/pro/{id}", method = RequestMethod.PUT)
    public Msg updateProject(TProject tProject, HttpServletRequest request) {
        tProject.setUpdateTime(new Date());
        tProjectService.updateProject(tProject);
        return Msg.success();
    }

    /**
     * 根据id查询员工
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/pro/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Msg getProject(@PathVariable("id") Integer id) {
        TProject tProject = tProjectService.getProject(id);
        return Msg.success().add("id", tProject);
    }

    /**
     * 检查项目编号是否可用
     *
     * @param projectCode
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkProject")
    public Msg checkProject(@RequestParam("projectCode") String projectCode) {
        //数据库项目名重复校验
        boolean b = tProjectService.checkProject(projectCode);
        if (b) {
            return Msg.success();
        } else {
            return Msg.fail().add("va_msg", "项目编号不可用");
        }
    }

    /**
     * 项目保存
     * 1、支持JSR303校验
     * 2、导入Hibernate-Validator
     *
     * @return
     */
    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveProject(@Valid TProject tProject, BindingResult result) {
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
            tProject.setCreateTime(new Date());
            tProjectService.saveProject(tProject);
            return Msg.success();
        }
    }

    /**
     * 导入jackson包。
     *
     * @param pn
     * @return
     */
    @RequestMapping("/pros")
    @ResponseBody
    public Msg getProsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 这不是一个分页查询
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        // startPage后面紧跟的这个查询就是一个分页查询
        TProjectExample example = new TProjectExample();
        example.setOrderByClause("create_time asc");
        List<TProject> pros = tProjectService.getAll(example);
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(pros, 5);
        return Msg.success().add("pageInfo", page);
    }

    /**
     * 查询项目数据（分页查询）
     *
     * @return
     */
    // @RequestMapping("/emps")
    public String getPros(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        // 这不是一个分页查询；
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        // startPage后面紧跟的这个查询就是一个分页查询
        TProjectExample example = new TProjectExample();
        example.setOrderByClause("create_time asc");
        List<TProject> pros = tProjectService.getAll(example);
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(pros, 5);
        model.addAttribute("pageInfo", page);
        return "list";
    }

    /**
     * 查询项目列表下拉框
     *
     * @return
     */
    @RequestMapping("/TProject/getProjectNameList")
    @ResponseBody
    public Msg getPros() {
        List<TProject> list = tProjectService.getAll(null);
        return Msg.success().add("pros", list);
    }
}
