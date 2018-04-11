package com.adtec.daily.controller.daily;

import com.adtec.daily.bean.common.Msg;
import com.adtec.daily.bean.daily.TDaily;
import com.adtec.daily.bean.daily.TDailyExample;
import com.adtec.daily.bean.daily.TOverWork;
import com.adtec.daily.bean.project.TProject;
import com.adtec.daily.bean.project.TProjectExample;
import com.adtec.daily.bean.user.TUser;
import com.adtec.daily.bean.user.TUserExample;
import com.adtec.daily.service.daily.TDailyService;
import com.adtec.daily.service.daily.TOverWorkService;
import com.adtec.daily.service.project.TProjectService;
import com.adtec.daily.service.user.TUserService;
import com.adtec.daily.util.DateUtil;
import com.adtec.daily.util.TemplateParseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static com.adtec.daily.util.DateUtil.StrToDate;
import static com.adtec.daily.util.DateUtil.getWeek;

/**
 * 处理项目CRUD请求
 */
@Controller
public class TDailyController {
    private static Logger logger = LoggerFactory.getLogger(TDailyController.class);

    @Autowired
    TDailyService tDailyService;

    @Autowired
    TUserService tUserService;

    @Autowired
    TProjectService tProjectService;

    @Autowired
    TOverWorkService tOverWorkService;

    /**
     * 查询日报列表信息
     *
     * @param tDaily
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDailyList", method = RequestMethod.POST)
    public Msg getDailyList(TDaily tDaily, String userId) {
        PageHelper.startPage(tDaily.getPn(), 5);
        tDaily.setUserId(userId);
        List<TDaily> tdList = tDailyService.getDailyList(tDaily);
        String week = "";
        if (tdList.size() > 0) {
            for (TDaily td : tdList) {
                week = getWeek(td.getDailyDate());
                td.setWeek(week);
            }
        }
        PageInfo page = new PageInfo(tdList, 5);
        return Msg.success().add("pageInfo", page);
    }

    /**
     * 日报保存
     * 同时判断是否超过了项目的加班开始时间，决定是否记录加班时间信息
     *
     * @param tDaily
     * @param result
     * @return
     */
    @RequestMapping(value = "/daily/saveDaily/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveDaily(@Valid TDaily tDaily, @PathVariable String userId, BindingResult result) {
        TDailyExample example = new TDailyExample();
        TDailyExample.Criteria cri = example.createCriteria();

        cri.andDailyDateEqualTo(StrToDate(tDaily.getDailyDateStr()));
        cri.andUserIdEqualTo(userId);
        cri.andProjectIdEqualTo(Integer.valueOf(tDaily.getProjectName()));
        List<TDaily> list = tDailyService.getDailyAllList(example);
        //保存之前判断是否存在该用户当日日报数据
        if (list.size() > 0) {
            return Msg.dateExist();
        } else {
            //查询获取用户名称
            TUserExample example1 = new TUserExample();
            TUserExample.Criteria cri1 = example1.createCriteria();
            cri1.andUserIdEqualTo(userId);
            List<TUser> tUserList = tUserService.getUserInfoList(example1);
            if (tUserList.size() > 0) {
                Date offDutyDate = DateUtil.getTimeShort(DateUtil.getHourTime());
                //保存该用户的日报信息
                tDaily.setDailyId("RB" + String.valueOf(System.currentTimeMillis()));
                tDaily.setUserName(tUserList.get(0).getUserName());
                tDaily.setUserId(userId);
                tDaily.setProjectId(Integer.valueOf(tDaily.getProjectName()));
                tDaily.setDailyDate(StrToDate(tDaily.getDailyDateStr()));
                tDaily.setOffDutyTime(offDutyDate);
                tDaily.setCreateTime(new Date());
                tDaily.setCreateUserId(userId);
                tDailyService.saveDaily(tDaily);

                //查询项目的加班开始时间
                TProjectExample example2 = new TProjectExample();
                TProjectExample.Criteria cri2 = example2.createCriteria();
                cri2.andIdEqualTo(Integer.valueOf(tDaily.getProjectName()));
                List<TProject> tProjectList = tProjectService.getAll(example2);
                if (tProjectList.size() > 0) {
                    //开始加班时间
                    Date overWorkStartTime = tProjectList.get(0).getOverworkStartTime();
                    //判断是否属于加班
                    if (offDutyDate.getTime() > overWorkStartTime.getTime()) {
                        //属于加班，计算获取加班时长
                        Long overWorkLong = offDutyDate.getTime() - overWorkStartTime.getTime();
                        BigDecimal overWorkDecimal = new BigDecimal(overWorkLong);
                        BigDecimal total = new BigDecimal(1000);
                        BigDecimal cycle = new BigDecimal(60);
                        BigDecimal overWorkTime = ((overWorkDecimal.divide(total,2, BigDecimal.ROUND_HALF_UP)).divide(cycle, 2, BigDecimal.ROUND_HALF_UP)).divide(cycle, 2, BigDecimal.ROUND_HALF_UP);
                        //插表t_over_work
                        TOverWork tOverWork = new TOverWork();
                        tOverWork.setUserId(userId);
                        tOverWork.setWorkDate(StrToDate(tDaily.getDailyDateStr()));
                        tOverWork.setOverWork(overWorkTime);
                        tOverWork.setCreateUserId(userId);
                        tOverWork.setCreateTime(new Date());
                        tOverWorkService.saveOverWork(tOverWork);
                    }
                }

                return Msg.success();
            } else {
                return Msg.fail();
            }
        }
    }

    /**
     * 导出个人日报
     * @param request
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/daily/dailyExport", method = RequestMethod.GET)
    public void dailyExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("导出日报开始");
        String start = request.getParameter("dailyStartDateStr");
        String end = request.getParameter("dailyEndDateStr");
        logger.info("开始日期:"+start+",结束日期:"+end);
        List<TUser> users = new ArrayList<>();
        TUser user = (TUser)request.getSession().getAttribute("user");
        users.add(user);
        List<Map<String, Object>> reportList = tDailyService.dailyExport(users,start,end);
        Map<String, Object> sheetMap = new HashMap<>();
        sheetMap.put("sheets", reportList);
        String fileName = "先进数通_工作日报_"+start+"_"+end;
        TemplateParseUtil templateParseUtil = new TemplateParseUtil();
        templateParseUtil.downloadExcel(response, sheetMap, TemplateParseUtil.Type.CaitcDaily,fileName);
        logger.info("导出日报结束");
    }

    /**
     * 导出个人周报
     * @param request
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/daily/weeklyExport", method = RequestMethod.GET)
    public void weeklyExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("导出日报开始");
        String start = request.getParameter("dailyStartDateStr");
        String end = request.getParameter("dailyEndDateStr");
        logger.info("开始日期:"+start+",结束日期:"+end);
        List<TUser> users = new ArrayList<>();
        TUser user = (TUser)request.getSession().getAttribute("user");
        users.add(user);
        List<Map<String, Object>> reportList = tDailyService.weeklyExport(users,start,end);
        Map<String, Object> sheetMap = new HashMap<>();
        sheetMap.put("sheets", reportList);
        String fileName = "先进数通_工作周报_"+start+"_"+end;
        TemplateParseUtil templateParseUtil = new TemplateParseUtil();
        templateParseUtil.downloadExcel(response, sheetMap, TemplateParseUtil.Type.CaitcWeekly,fileName);
        logger.info("导出日报结束");
    }

    /**
     * 导出项目周报
     * @param request
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/daily/projectWeeklyExport", method = RequestMethod.GET)
    public void projectWeeklyExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("导出项目周报开始");
        String startDate = request.getParameter("dailyStartDateStr");
        startDate = startDate.substring(0,4)+"年"+startDate.substring(4,6)+"月"+startDate.substring(6)+"日";
        String endDate = request.getParameter("dailyEndDateStr");
        endDate = endDate.substring(0,4)+"年"+endDate.substring(4,6)+"月"+endDate.substring(6)+"日";
        logger.info("开始日期:"+startDate+",结束日期:"+endDate);
        List<String> users = new ArrayList<>();
        users.add("王林柱");
        users.add("郭凡");
        users.add("赫鑫");
        users.add("胡浪");
        users.add("张琪");
        List<Map<String, Object>> reportList = new ArrayList<>();
        Map<String, Object> sheetMap = new HashMap<>();
        sheetMap.put("sheets", reportList);
        String fileName = "先进数通_工作周报_20180205_20180205";
        TemplateParseUtil templateParseUtil = new TemplateParseUtil();
        templateParseUtil.downloadExcel(response, sheetMap, TemplateParseUtil.Type.CaitcWeekly,fileName);
        logger.info("导出日报结束");
    }
}
