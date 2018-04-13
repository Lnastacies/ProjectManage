package com.adtec.daily.service.daily;

import com.adtec.daily.bean.daily.TOverWork;
import com.adtec.daily.bean.daily.TOverWorkExample;

import java.util.List;

/**
 * @version V1.0
 * @Description: 加班时间处理接口
 * @author: 胡浪
 * @date: 2018/4/13
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
public interface TOverWorkService {

    /**
     * 查询所有加班数据
     *
     * @param example
     * @return
     */
    public List<TOverWork> getAll(TOverWorkExample example);

    /**
     * 加班数据保存
     *
     * @param tOverWork
     */
    public void saveOverWork(TOverWork tOverWork);

    /**
     * 根据用户id及上班日期查询加班明细列表
     *
     * @param userId
     * @param startDate
     * @param endDate
     * @return
     */
    public List<TOverWork> selectOverWorkDetailListByUserIdAndWorkDate(String userId,String startDate,String endDate);

    /**
     * 根据用户id及上班日期查询加班总时长
     *
     * @param userId
     * @param startDate
     * @param endDate
     * @return
     */
    public TOverWork selectOverWorkByUserIdAndWorkDate(String userId,String startDate,String endDate);

}
