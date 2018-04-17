package com.adtec.daily.controller.project;

import com.adtec.daily.bean.common.Msg;
import com.adtec.daily.bean.project.TProject;
import com.adtec.daily.bean.project.TProjectExample;
import com.adtec.daily.bean.user.TUser;
import com.adtec.daily.bean.user.TUserProject;
import com.adtec.daily.service.project.TProjectService;
import com.adtec.daily.service.user.TUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    TProjectService tProjectService;
    @Autowired
    TUserService tUserService;

    /**
     * 项目更新方法
     *
     * @param tProject
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/project/update/{id}", method = RequestMethod.PUT)
    public Msg updateProject(TProject tProject, HttpServletRequest request) {
        tProject.setUpdateTime(new Date());
        tProjectService.updateProject(tProject);
        return Msg.success();
    }

    /**
     * 根据id查询项目
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/project/getProjectById/{id}", method = RequestMethod.GET)
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
    @RequestMapping("/project/checkProject")
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
     *
     * @return
     */
    @RequestMapping(value = "/project/addProject", method = RequestMethod.POST)
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
     * 管理员查询所有项目信息
     *
     * @param pn
     * @return
     */
    @RequestMapping("/project/getProjectInfo")
    @ResponseBody
    public Msg getProsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 5);
        List<TProject> pros = tProjectService.getAll();
        PageInfo page = new PageInfo(pros, 5);
        return Msg.success().add("pageInfo", page);
    }

    /**
     * 员工查询所属项目信息
     *
     * @return
     */
    @RequestMapping("/project/getProjectByUser")
    @ResponseBody
    public Msg getProjectByUser(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model, @RequestParam String userId) {
        PageHelper.startPage(pn, 5);
        List<TProject> pros = tProjectService.getProjectByUser(userId);
        PageInfo page = new PageInfo(pros, 5);
        return Msg.success().add("pageInfo", page);
    }

    /**
     * 查询项目列表下拉框
     *
     * @return
     */
    @RequestMapping(value = "/project/getProjectNameList", method = RequestMethod.POST)
    @ResponseBody
    public Msg getPros(String userId) {
        List<TProject> list = tProjectService.getProjectByUser(userId);
        return Msg.success().add("pros", list);
    }

    /**
     * 根据项目id查询项目成员
     *
     * @param projectId
     * @return
     */
    @RequestMapping("/project/getProjectUserByProjectId")
    @ResponseBody
    public Msg getProjectUserByProjectId(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @RequestParam("projectId") int projectId) {
        PageHelper.startPage(pn, 5);
        int companyId =0;
        List<TUser> pros = tUserService.getAllUserByProjectIdAndCompanyId(projectId,companyId);
        PageInfo page = new PageInfo(pros, 5);
        return Msg.success().add("pageInfo", page);
    }

    /**
     *
     * 删除项目成员
     *
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "project/deleteProjectUser/{id}", method = RequestMethod.DELETE)
    public Msg updateUser(@PathVariable("id") String userId) {

            tUserService.deleteByUserId(userId);

        return Msg.success();
    }

    /**
     *添加项目成员
     *
     * @param ids
     * @param projectId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/project/projectUserSave", method = RequestMethod.GET)
    public List<TUser> projectUserSave(String ids,int projectId) {

            String[] save_ids = ids.split("-");
            List<TUser> resultList = new ArrayList<TUser>();
            //查出该项目下的成员
            int companyId = 0;
            List<TUser> list = tUserService.getAllUserByProjectIdAndCompanyId(projectId, companyId);
            //组装id的集合
            for (String userId : save_ids) {
                for (TUser user : list) {
                    //项目已经有成员，则不保存
                    if (user.getUserId().equals(userId)) {
                        resultList.add(user);
                        return resultList;
                    }
                }
            }
            //项目中没有成员，则进行保存
            for (String userId : save_ids) {
                //添加成员
                TUserProject tUserProject = new TUserProject();
                tUserProject.setUserId(userId);
                tUserProject.setProjectId(projectId);
                tUserService.projectUserSave(tUserProject);
            }
        return resultList;
    }
}
