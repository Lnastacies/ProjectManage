package com.adtec.daily.controller.daily;

import com.adtec.daily.bean.common.Msg;
import com.adtec.daily.bean.daily.*;
import com.adtec.daily.bean.project.Project;
import com.adtec.daily.service.daily.DailyDetailService;
import com.adtec.daily.service.daily.DailyService;
import com.adtec.daily.service.daily.OverWorkService;
import com.adtec.daily.service.project.ProjectService;
import com.adtec.daily.util.DateUtil;
import com.adtec.daily.util.ToolUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * @version V1.0
 * @Description: 日报内容信息类
 * @author: 胡浪
 * @date: 2018/4/8
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
@Controller
public class DailyDetailController {

    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    DailyDetailService dailyDetailService;

    @Autowired
    DailyService dailyService;

    @Autowired
    ProjectService projectService;

    @Autowired
    OverWorkService overWorkService;

    /**
     * 查询日报列表信息
     *
     * @param dailyDetail
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/dailyDetail/getDailyDetailList", method = RequestMethod.POST)
    public Msg getDailyDetailList(DailyDetail dailyDetail, @RequestParam("dailyId") String dailyId) {
        PageHelper.startPage(dailyDetail.getPn(), 5);
        dailyDetail.setDailyId(dailyId);
        List<DailyDetail> tddList = dailyDetailService.getDailyList(dailyDetail);
        PageInfo page = new PageInfo(tddList, 5);
        return Msg.success().add("pageInfo", page);
    }

    /**
     * 保存日报详情信息
     *
     * @param dailyDetail
     * @param result
     * @return
     */
    @RequestMapping(value = "/dailyDetail/saveDailyDetail", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveDailyDateil(@Valid DailyDetail dailyDetail, BindingResult result) {
        dailyDetail.setDailyDetailId("RBD" + String.valueOf(System.currentTimeMillis()));
        dailyDetail.setDoneDate(DateUtil.StrToDate(dailyDetail.getDoneDateStr()));
        dailyDetail.setDailyId(dailyDetail.getDailyId());
        dailyDetail.setCreateTime(new Date());
        //根据传入的完成百分比0=未开始，1-99=正在进行，100=已完成（01：未开始，02：正在进行，03：已完成）
        if (dailyDetail.getDailyPercent() != null) {
            if (dailyDetail.getDailyPercent() == 0) {
                dailyDetail.setExecution("01");
            } else if (dailyDetail.getDailyPercent() == 100) {
                dailyDetail.setExecution("03");
            } else {
                dailyDetail.setExecution("02");
            }
        }
        //保存日报详细内容
        dailyDetailService.saveDailyDetail(dailyDetail);
        //查询日报信息
        Date offDutyDate = DateUtil.StrToTime(DateUtil.getHourTime());
        DailyExample example = new DailyExample();
        DailyExample.Criteria cri = example.createCriteria();
        cri.andDailyIdEqualTo(dailyDetail.getDailyId());
        List<Daily> dailyList = dailyService.getDailyAllList(example);
        if (dailyList.size() > 0) {
            overWork(dailyList.get(0).getProjectId(), offDutyDate, dailyList.get(0).getUserId(), DateUtil.StrToDate(dailyDetail.getDoneDateStr()), dailyDetail.getDailyId());
        }
        return Msg.success();
    }

    /**
     * 删除日报内容信息
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/dailyDetail/deleteDailyDetail/{ids}", method = RequestMethod.DELETE)
    public Msg deleteDailyDetail(@PathVariable("ids") String ids) {
        dailyDetailService.deleteDailyDetail(ids);
        return Msg.success();
    }

    /**
     * 根据下班时间不同场景区分计算加班时间
     * @param projectId     项目ID
     * @param offDutyDate   下班时间
     * @param userId        用户ID
     * @param dailyDate     日报日期
     * @param dailyId       日报ID
     */
    public void overWork(int projectId, Date offDutyDate, String userId, Date dailyDate, String dailyId) {
        //判断当前是否属于0-工作日，1-周末，2-节假日
        String toDayType = DateUtil.checkHoliday(new Date());
        logger.info("当前时间属性：" + toDayType);
        //查询项目的上午下班时间、下午开始上班时间、加班开始时间
        Project project = projectService.getProject(Integer.valueOf(projectId));
        if (toDayType != null && "0".equals(toDayType)) {
            if (project != null && !"".equals(project.getOverworkStartTime())) {
                //开始加班时间
                Date overWorkStartTime = project.getOverworkStartTime();
                //判断是否属于加班
                if (offDutyDate.getTime() > overWorkStartTime.getTime()) {
                    //属于加班，计算获取加班时长
                    Long overWorkLong = offDutyDate.getTime() - overWorkStartTime.getTime();
                    overWorkTime(overWorkLong, userId, dailyDate, dailyId,toDayType);
                }
            }
        } else if ("1".equals(toDayType) || "2".equals(toDayType)) {
            //计算周末或者节假日加班时间，加班时间
            if (project != null && !"".equals(project.getMorningEndTime()) && !"".equals(project.getAfterStartTime())) {
                //开始上班时间
                Date workStartTime = project.getWorkStartTime();
                //上午下班时间
                Date morningEndTime = project.getMorningEndTime();
                //下午开始上班时间
                Date afterStartTime = project.getAfterStartTime();
                //需要判断是上午下班了还是下午下班了
                //上午下班时间-开始上班时间
                Long overWorkLongMorning = morningEndTime.getTime() - workStartTime.getTime();
                //下班时间-上午下班时间
                Long overWorkLong = offDutyDate.getTime() - morningEndTime.getTime();
                //下午开始上班时间-下班时间
                Long overWorkLongNoon = afterStartTime.getTime() - offDutyDate.getTime();
                //下班时间-下午开始上班时间
                Long overWorkLongAfter = offDutyDate.getTime() - afterStartTime.getTime();
                //下午上班时间+上午上班时间
                Long overWorkLongSubtract = overWorkLongAfter + overWorkLongMorning;
                OverWork overWork = new OverWork();
                if (overWorkLong < 0) {
                    //若在上午下班之前提交了日报
                    overWorkTime(overWorkLong, userId, dailyDate, dailyId,toDayType);
                } else if (overWorkLongNoon < 0) {
                    //若在上午下班和下午上班之前，填写了日报，提前下班，加班时间=下班时间-上午下班时间
                    overWorkTime(overWorkLongMorning, userId, dailyDate, dailyId,toDayType);
                } else if (overWorkLongAfter > 0) {
                    //在下午上班之后下班
                    overWorkTime(overWorkLongSubtract, userId, dailyDate, dailyId,toDayType);
                }
            }
        }
    }

    /**
     * 计算加班时间方法
     *
     * @param overWorkLong 加班时间
     * @param userId       用户ID
     * @param dailyDate    日报日期
     * @param dailyId      日报ID
     * @param holidayFlag  节假日标识 0-工作日 1-周末 2-节假日
     */
    public void overWorkTime(Long overWorkLong, String userId, Date dailyDate, String dailyId,String holidayFlag) {
        OverWork overWork = new OverWork();
        BigDecimal overWorkDecimal = new BigDecimal(overWorkLong);
        BigDecimal total = new BigDecimal(1000);
        BigDecimal cycle = new BigDecimal(60);
        BigDecimal overWorkTime = ((overWorkDecimal.divide(total, 1, BigDecimal.ROUND_HALF_UP)).divide(cycle, 1, BigDecimal.ROUND_HALF_UP)).divide(cycle, 1, BigDecimal.ROUND_HALF_UP);
        //0.5为最小单位，不满0.5的不要；不满1的记为0.5。使用ROUND_HALF_DOWN有点问题，这里硬写
        DecimalFormat oneMit = new DecimalFormat("0.0");
        //获取整数部分
        String overWorkTimeOneMitFirst = oneMit.format(overWorkTime).split("\\.")[0];
        String overWorkTimeOneMitSecond = oneMit.format(overWorkTime).split("\\.")[1];
        BigDecimal mixOverWorkTime = new BigDecimal("0.5");
        BigDecimal overWorkTimeOneMitFirstDouble = new BigDecimal(overWorkTimeOneMitFirst);

        //查询t_over_work是否存在该数据，根据user_id，work_date，remark1查询表t_over_work是否存在
        OverWorkExample example = new OverWorkExample();
        OverWorkExample.Criteria cri = example.createCriteria();
        cri.andUserIdEqualTo(userId);
        cri.andRemark1EqualTo(dailyId);
        List<OverWork> overWorkList = overWorkService.getAll(example);
        if (overWorkList.size() > 0) {
            // 更新数据
            overWork.setId(overWorkList.get(0).getId());
            overWork.setOverWork(ToolUtil.getOverWorkTime(overWorkTime));
            overWork.setUpdateTime(new Date());
            overWork.setUserId(userId);
            overWork.setHolidayFlag(holidayFlag);
            overWorkService.updateByDailyId(overWork);
        } else {
            if (overWorkTimeOneMitFirst.compareTo("0") == 0) {
                if (overWorkTimeOneMitSecond.compareTo("5") >= 0) {
                    overWork.setOverWork(mixOverWorkTime);
                }
            } else if (overWorkTimeOneMitFirst.compareTo("0") > 0) {
                if (overWorkTimeOneMitSecond.compareTo("5") >= 0) {
                    overWork.setOverWork(overWorkTimeOneMitFirstDouble.add(mixOverWorkTime));
                } else {
                    overWork.setOverWork(overWorkTimeOneMitFirstDouble);
                }
            }
            //插表t_over_work
            overWork.setUserId(userId);
            overWork.setWorkDate(dailyDate);
            overWork.setCreateUserId(userId);
            overWork.setCreateTime(new Date());
            overWork.setRemark1(dailyId);
            overWork.setHolidayFlag(holidayFlag);
            overWorkService.saveOverWork(overWork);
        }
    }

    /**
     * 根据id查询日报内容
     *
     * @param id 日报详细ID
     * @return
     */
    @RequestMapping(value = "/dailyDetail/getDailyDetailInfo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Msg getDailyDetailInfo(@PathVariable("id") String id) {
        DailyDetail dailyDetail = dailyDetailService.getDailyDetailInfo(id);
        return Msg.success().add("tDailyDetail", dailyDetail);
    }

    /**
     * 更新日报详情信息
     *
     * @param dailyDetail
     * @param result
     * @return
     */
    @RequestMapping(value = "/dailyDetail/updateDailyDetail", method = RequestMethod.POST)
    @ResponseBody
    public Msg updateDailyDetail(@Valid DailyDetail dailyDetail, BindingResult result) {
        dailyDetail.setWorkType(dailyDetail.getWorkType());
        dailyDetail.setDailyDetailId(dailyDetail.getDailyDetailId());
        dailyDetail.setDailyContent(dailyDetail.getDailyContent());
        dailyDetail.setDailyPercent(dailyDetail.getDailyPercent());
        dailyDetail.setProblem(dailyDetail.getProblem());
        dailyDetail.setDoneDate(DateUtil.StrToDate(dailyDetail.getDoneDateStr()));
        dailyDetail.setNextWorkPlan(dailyDetail.getNextWorkPlan());
        dailyDetail.setUpdateTime(new Date());
        dailyDetail.setUpdateUserId(dailyDetail.getUserId());
        //更新日报详细内容
        dailyDetailService.updateDailyDetail(dailyDetail);
        //查询日报信息
        Date offDutyDate = DateUtil.StrToTime(DateUtil.getHourTime());
        DailyExample example = new DailyExample();
        DailyExample.Criteria cri = example.createCriteria();
        cri.andDailyIdEqualTo(dailyDetail.getDailyId());
        List<Daily> dailyList = dailyService.getDailyAllList(example);
        //同样需要处理加班时间信息
        if (dailyList.size() > 0) {
            overWork(dailyList.get(0).getProjectId(), offDutyDate, dailyList.get(0).getUserId(), DateUtil.StrToDate(dailyDetail.getDoneDateStr()), dailyDetail.getDailyId());
        }
        return Msg.success();
    }
}
