package com.adtec.daily.service.daily;

import com.adtec.daily.bean.daily.TDaily;
import com.adtec.daily.bean.daily.TDailyExample;
import com.adtec.daily.bean.user.TUser;

import java.util.List;
import java.util.Map;

/**
 * @version V1.0
 * @Description: 日报接口类
 * @author: 胡浪
 * @date: 2018/4/13
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
public interface TDailyService {

    /**
     * 日报列表查询
     *
     * @param record
     * @return
     * @throws Exception
     */
    List<TDaily> getDailyList(TDaily record);

    /**
     * 日报保存
     *
     * @param tDaily
     * @throws Exception
     */
    public void saveDaily(TDaily tDaily);

    /**
     * 条件查询日报信息
     *
     * @param example
     * @return
     * @throws Exception
     */
    public List<TDaily> getDailyAllList(TDailyExample example);

    /**
     * 导出个人日报
     *
     * @param users 用户信息
     * @param startDate 日报开始日期
     * @param endDate   日报结束日期
     * @return reportList
     * @throws Exception
     */
    public List<Map<String, Object>> dailyExport(List<TUser> users, String startDate, String endDate);

    /**
     * 导出长安信托个人周报
     *
     * @param users 用户信息
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return sheetMap
     * @throws Exception
     */
    public List<Map<String, Object>> caitcWeeklyExport(List<TUser> users,String startDate,String endDate);

    /**
     * 导出长安信托项目周报
     *
     * @param users 用户信息
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return thisWeekList
     * @throws Exception
     */
    public Map<String, Object> caitcProjectWeeklyExport(List<TUser> users,String startDate,String endDate);

    /**
     * 根据日报数据生成周报数据
     *
     * @param key 日报数据key
     * @param weeklyData 日报数据Map
     * @param weekly 日报数据
     * @param lineNumberArray 行数数组（日报内容，问题及计划所占行数）
     * @throws Exception
     */
    public void dailyToWeekly(String key,Map<String,TDaily> weeklyData,TDaily weekly,int[] lineNumberArray);

    /**
     * 根据日报数据生成项目周报数据
     *
     * @param weekly 日报数据
     * @param lineNumberArray 行数数组（日报内容，问题及计划所占行数）
     * @throws Exception
     */
    public void dailyToProjectWeekly(TDaily weekly,int[] lineNumberArray);
}
