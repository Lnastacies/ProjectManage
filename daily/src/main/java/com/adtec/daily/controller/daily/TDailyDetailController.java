package com.adtec.daily.controller.daily;

import com.adtec.daily.bean.common.Msg;
import com.adtec.daily.bean.daily.TDailyDetail;
import com.adtec.daily.util.DateUtil;
import com.adtec.daily.service.daily.TDailyDetailService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 处理项目CRUD请求
 */
@Controller
public class TDailyDetailController {

    @Autowired
    TDailyDetailService tDailyDetailService;

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
        return Msg.success();
    }

    @RequestMapping(value = "/dailyDetail/deleteDailyDetail", method = RequestMethod.POST)
    @ResponseBody
    public Msg deleteDailyDetail() {

        return Msg.success();
    }
}
