package com.adtec.daily.service.daily;

import com.adtec.daily.bean.daily.DailyDetail;

import java.util.List;

/**
 * @version V1.0
 * @Description: 日报详情内容接口类
 * @author: 胡浪
 * @date: 2018/4/13
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
public interface DailyDetailService {

    /**
     * 查询日报详细列表
     *
     * @param record 日报详情信息
     * @return
     */
    List<DailyDetail> getDailyList(DailyDetail record);

    /**
     * 保存日报详情
     *
     * @param dailyDetail
     */
    void saveDailyDetail(DailyDetail dailyDetail);

    /**
     * 删除日报详情
     *
     * @param id 日报id
     */
    void deleteDailyDetail(String id);

    /**
     * 根据主键查询日报内容
     *
     * @param dailyDetailId
     */
    DailyDetail getDailyDetailInfo(String dailyDetailId);

    /**
     * 根据主键修改日报内容信息
     * @param dailyDetail
     */
    void updateDailyDetail(DailyDetail dailyDetail);
}
