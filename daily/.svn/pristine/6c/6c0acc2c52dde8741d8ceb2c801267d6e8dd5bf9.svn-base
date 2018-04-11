package com.adtec.daily.service.daily;

import com.adtec.daily.bean.daily.TDailyDetail;
import com.adtec.daily.dao.daily.TDailyDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TDailyDetailService {
	@Autowired
    TDailyDetailMapper tDailyDetailMapper;

	/**
	 * 查询日报详细列表
     *
     * @param record
	 * @return
	 */
	public List<TDailyDetail> getDailyList(TDailyDetail record) {
        List<TDailyDetail> tDailyDetaillist = tDailyDetailMapper.selectDailyDetailByDailyId(record);
		return tDailyDetaillist;
	}

    /**
     * 保存日报详情
     *
     * @param tDailyDetail
     */
	public void saveDailyDetail(TDailyDetail tDailyDetail) {
	    tDailyDetailMapper.insertSelective(tDailyDetail);
    }

}
