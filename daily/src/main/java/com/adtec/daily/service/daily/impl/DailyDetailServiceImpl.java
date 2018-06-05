package com.adtec.daily.service.daily.impl;

import com.adtec.daily.bean.daily.DailyDetail;
import com.adtec.daily.dao.daily.DailyDetailMapper;
import com.adtec.daily.service.daily.DailyDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version V1.0
 * @Description: 日报详情内容实现类
 * @author: 胡浪
 * @date: 2018/4/13
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
@Service
public class DailyDetailServiceImpl implements DailyDetailService {

	@Autowired
    DailyDetailMapper dailyDetailMapper;

	/**
	 * 查询日报详细列表
	 */
    @Override
	public List<DailyDetail> getDailyList(DailyDetail record) {
        List<DailyDetail> dailyDetaillist = dailyDetailMapper.selectDailyDetailByDailyId(record);
		return dailyDetaillist;
	}

    /**
     * 保存日报详情
     */
    @Override
	public void saveDailyDetail(DailyDetail dailyDetail) {
	    dailyDetailMapper.insertSelective(dailyDetail);
    }

    /**
     * 删除日报详情
     */
    @Override
    public void deleteDailyDetail(String id) {
        dailyDetailMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主键查询日报内容
     */
    @Override
    public DailyDetail getDailyDetailInfo(String dailyDetailId) {
        return dailyDetailMapper.selectByPrimaryKey(dailyDetailId);
    }

    /**
     * 角色更新
     */
    @Override
    public void updateDailyDetail(DailyDetail dailyDetail) {
        dailyDetailMapper.updateByPrimaryKeySelective(dailyDetail);
    }
}
