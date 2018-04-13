package com.adtec.daily.service.demand.impl;

import com.adtec.daily.bean.demand.TDemandDetail;
import com.adtec.daily.dao.demand.TDemandDetailMapper;
import com.adtec.daily.service.demand.TDemandDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TDemandDetailServiceImpl implements TDemandDetailService{

	@Autowired
    TDemandDetailMapper tDemandDetailMapper;

	/**
	 * 查询所有项目信息
	 */
	public List<TDemandDetail> getDemandDetail(TDemandDetail tDemandDetail) {
		return tDemandDetailMapper.selectByDemandId(tDemandDetail);
	}

}
