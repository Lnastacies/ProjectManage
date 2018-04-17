package com.adtec.daily.service.daily.impl;

import com.adtec.daily.bean.daily.TDailyDetail;
import com.adtec.daily.dao.daily.TDailyDetailMapper;
import com.adtec.daily.service.daily.TDailyDetailService;
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
public class TDailyDetailServiceImpl implements TDailyDetailService{

	@Autowired
    TDailyDetailMapper tDailyDetailMapper;

	/**
	 * 查询日报详细列表
	 */
	public List<TDailyDetail> getDailyList(TDailyDetail record) {
        List<TDailyDetail> tDailyDetaillist = tDailyDetailMapper.selectDailyDetailByDailyId(record);
		return tDailyDetaillist;
	}

    /**
     * 保存日报详情
     */
	public void saveDailyDetail(TDailyDetail tDailyDetail) {
	    tDailyDetailMapper.insertSelective(tDailyDetail);
    }

    /**
     * 删除日报详情
     */
    public void deleteDailyDetail(String id) {
        tDailyDetailMapper.deleteByPrimaryKey(id);
    }
}
