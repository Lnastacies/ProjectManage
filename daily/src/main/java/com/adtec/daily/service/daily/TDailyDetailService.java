package com.adtec.daily.service.daily;

import com.adtec.daily.bean.daily.TDailyDetail;

import java.util.List;

/**
 * @version V1.0
 * @Description: 日报详情内容接口类
 * @author: 胡浪
 * @date: 2018/4/13
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
public interface TDailyDetailService {

    /**
     * 查询日报详细列表
     *
     * @param record 日报详情信息
     * @return
     */
    public List<TDailyDetail> getDailyList(TDailyDetail record);

    /**
     * 保存日报详情
     *
     * @param tDailyDetail
     */
    public void saveDailyDetail(TDailyDetail tDailyDetail);

    /**
     * 删除日报详情
     *
     * @param id 日报id
     */
    public void deleteDailyDetail(String id);

}
