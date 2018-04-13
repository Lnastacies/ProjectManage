package com.adtec.daily.service.demand;

import com.adtec.daily.bean.demand.TDemandDetail;

import java.util.List;

/**
 * @version V1.0
 * @Description: 需求详情信息接口类
 * @author: 胡浪
 * @date: 2018/4/13
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
public interface TDemandDetailService {

    /**
     * 查询所有项目信息
     */
    public List<TDemandDetail> getDemandDetail(TDemandDetail tDemandDetail);
}