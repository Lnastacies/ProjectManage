package com.adtec.daily.controller.daily;

import com.adtec.daily.bean.daily.TOverWork;
import com.adtec.daily.bean.project.TCompany;
import com.adtec.daily.bean.project.TDepartment;
import com.adtec.daily.bean.project.TProject;
import com.adtec.daily.bean.user.TUser;
import com.adtec.daily.service.daily.TOverWorkService;
import com.adtec.daily.service.project.TCompanyService;
import com.adtec.daily.service.user.TUserService;
import com.adtec.daily.util.DateUtil;
import com.adtec.daily.util.TemplateParseUtil;
import com.adtec.daily.util.ToolUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 处理加班补贴
 */
@Controller
public class TOverWorkController {

    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    TUserService tUserService;

    @Autowired
    TOverWorkService tOverWorkService;

    @Autowired
    TCompanyService tCompanyService;

    /**
     * 导出加班补贴表
     * @param request
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/overWork/overWorkExport", method = RequestMethod.GET)
    public void dailyExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("导出加班补贴表开始");
        String monthStr = request.getParameter("monthStr");
        //1.算出所选月份所有日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date month = null;
        try {
            month = sdf.parse(monthStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<String> dateList =  DateUtil.getDayListOfMonth(month);
        String startDate = dateList.get(0);//月初日期
        String endDate = dateList.get(dateList.size()-1);//月末日期

        //2.查询当前用户所属项目下的所有用户
        TProject project = (TProject)request.getSession().getAttribute("project");
        TDepartment department = (TDepartment)request.getSession().getAttribute("department");
        TCompany company =  tCompanyService.selectByDeptId(department.getDeptId());
        //TUser user = (TUser)request.getSession().getAttribute("user");
        List<TUser> users = tUserService.getAllUserByProjectIdAndCompanyId(project.getId(),company.getCompanyId());

        //3.查询每个用户的加班情况
        BigDecimal sumOverWork = BigDecimal.ZERO;
        List<TOverWork> overWorkList = new ArrayList<>();
        List<TOverWork> overWorkDetailList = new ArrayList<>();
        for(int i=0;i<users.size();i++){
            //根据用户id及上班日期查询加班总时长
            TOverWork overWork = tOverWorkService.selectOverWorkByUserIdAndWorkDate(users.get(i).getUserId(),startDate,endDate);
            sumOverWork = sumOverWork.add(overWork.getSumOverWork());
            overWorkList.add(overWork);

            //初始化加班明细数据
            Map<String,TOverWork> overWorkMap = new HashMap<>();
            for(int x=0;x<dateList.size();x++){
                overWorkMap.put(dateList.get(x),new TOverWork());
            }

            //根据用户id及上班日期查询加班明细列表

            List<TOverWork> overWorks = tOverWorkService.selectOverWorkDetailListByUserIdAndWorkDate(users.get(i).getUserId(),startDate,endDate);
            for(int j=0;j<overWorks.size();j++){
                TOverWork ow = overWorks.get(j);
                overWorkMap.put(ToolUtil.doubRecFormateDateString(ow.getWorkDate()),ow);
            }

            //重组加班补贴数据
            List<TOverWork> owList = new ArrayList<>();
            TreeMap<String, TOverWork> treeMap = new TreeMap<>(overWorkMap);
            for(String key:treeMap.keySet()){
                owList.add(treeMap.get(key));
            }
            overWork.setOverWorkList(owList);
            overWorkDetailList.add(overWork);
        }

        Map<String, Object> sheetMap = new HashMap<>();
        sheetMap.put("companyName", company.getCompanyName());
        sheetMap.put("sumOverWork", sumOverWork);
        sheetMap.put("dateList", dateList);
        sheetMap.put("overWorkList", overWorkList);
        sheetMap.put("overWorkDetailList", overWorkDetailList);
        String fileName = department.getDeptName()+ "_项目补贴表（"+monthStr.substring(0,4)+"年"+monthStr.substring(5,7)+"月_new）_"+project.getProjectCode()+"_" + users.get(0).getUserName();
        TemplateParseUtil templateParseUtil = new TemplateParseUtil();
        templateParseUtil.downloadExcel(response, sheetMap, TemplateParseUtil.Type.OverWork,fileName);
        logger.info("导出加班补贴表结束");
    }
}
