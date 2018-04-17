package com.adtec.daily.controller.daily;

import com.adtec.daily.bean.common.Msg;
import com.adtec.daily.bean.daily.*;
import com.adtec.daily.bean.project.TProject;
import com.adtec.daily.service.daily.TDailyDetailService;
import com.adtec.daily.service.daily.TDailyService;
import com.adtec.daily.service.daily.TOverWorkService;
import com.adtec.daily.service.daily.impl.TDailyServiceImpl;
import com.adtec.daily.service.project.TProjectService;
import com.adtec.daily.util.DateUtil;
import com.adtec.daily.service.daily.impl.TDailyDetailServiceImpl;
import com.adtec.daily.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.adtec.daily.util.DateUtil.StrToDate;

/**
 * @version V1.0
 * @Description: 日报内容信息类
 * @author: 胡浪
 * @date: 2018/4/8
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
@Controller
public class TDailyDetailController {

    @Autowired
    TDailyDetailService tDailyDetailService;

    @Autowired
    TDailyService tDailyService;

    @Autowired
    TProjectService tProjectService;

    @Autowired
    TOverWorkService tOverWorkService;

    /**
     * 查询日报列表信息
     *
     * @param tDailyDetail
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/dailyDetail/getDailyDetailList", method = RequestMethod.POST)
    public Msg getDailyDetailList(TDailyDetail tDailyDetail, @RequestParam("dailyId") String dailyId) {
        PageHelper.startPage(tDailyDetail.getPn(), 5);
        tDailyDetail.setDailyId(dailyId);
        List<TDailyDetail> tddList = tDailyDetailService.getDailyList(tDailyDetail);
        PageInfo page = new PageInfo(tddList, 5);
        return Msg.success().add("pageInfo", page);
    }

    /**
     * 保存日志详情
     *
     * @param tDailyDetail
     * @param result
     * @return
     */
    @RequestMapping(value = "/dailyDetail/saveDailyDateil", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveDailyDateil(@Valid TDailyDetail tDailyDetail, BindingResult result) {
        tDailyDetail.setDailyDetailId("RBD" + String.valueOf(System.currentTimeMillis()));
        tDailyDetail.setDoneDate(DateUtil.StrToDate(tDailyDetail.getDoneDateStr()));
        tDailyDetail.setDailyId(tDailyDetail.getDailyId());
        tDailyDetail.setCreateTime(new Date());
        //根据传入的完成百分比0=未开始，1-99=正在进行，100=已完成（01：未开始，02：正在进行，03：已完成）
        if (tDailyDetail.getDailyPercent() != null) {
            if (tDailyDetail.getDailyPercent() == 0) {
                tDailyDetail.setExecution("01");
            } else if (tDailyDetail.getDailyPercent() == 100) {
                tDailyDetail.setExecution("03");
            } else {
                tDailyDetail.setExecution("02");
            }
        }
        tDailyDetailService.saveDailyDetail(tDailyDetail);
        //添加日报内容信息时进行更新下班时间t_daily.off_duty_time
        //获取项目开始加班时间
        TProject tProject = tProjectService.getProjectByDailyId(tDailyDetail.getDailyId());
        if (tProject != null && !"".equals(tProject.getOverworkStartTime())) {
            Date offDutyDate = DateUtil.getTimeShort(DateUtil.getHourTime());
            //开始加班时间
            Date overWorkStartTime = tProject.getOverworkStartTime();
            //判断是否属于加班
            if (offDutyDate.getTime() > overWorkStartTime.getTime()) {
                //属于加班，计算获取加班时长
                Long overWorkLong = offDutyDate.getTime() - overWorkStartTime.getTime();
                BigDecimal overWorkDecimal = new BigDecimal(overWorkLong);
                BigDecimal total = new BigDecimal(1000);
                BigDecimal cycle = new BigDecimal(60);
                BigDecimal overWorkTime = ((overWorkDecimal.divide(total,2, BigDecimal.ROUND_HALF_UP)).divide(cycle, 2, BigDecimal.ROUND_HALF_UP)).divide(cycle, 2, BigDecimal.ROUND_HALF_UP);

                //查询t_over_work是否存在该数据，根据user_id，work_date，remark1查询表t_over_work是否存在
                TOverWorkExample example = new TOverWorkExample();
                TOverWorkExample.Criteria cri = example.createCriteria();
                cri.andUserIdEqualTo(tProject.getUserId());
                cri.andRemark1EqualTo(tDailyDetail.getDailyId());
                List<TOverWork> tOverWorkList = tOverWorkService.getAll(example);

                //若存在，则更新数据，否则新增数据
                TOverWork tOverWork = new TOverWork();
                if (tOverWorkList.size() > 0) {
                    // 更新数据
                    tOverWork.setId(tOverWorkList.get(0).getId());
                    tOverWork.setOverWork(overWorkTime);
                    tOverWork.setUpdateTime(new Date());
                    tOverWork.setUserId(tProject.getUserId());
                    tOverWorkService.updateByDailyId(tOverWork);
                } else {
                    // 新增数据
                    tOverWork.setUserId(tProject.getUserId());
                    tOverWork.setWorkDate(tProject.getDailyDate());
                    tOverWork.setOverWork(overWorkTime);
                    tOverWork.setCreateUserId(tProject.getUserId());
                    tOverWork.setCreateTime(new Date());
                    tOverWorkService.saveOverWork(tOverWork);
                }
            }
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
        tDailyDetailService.deleteDailyDetail(ids);
        return Msg.success();
    }
}
