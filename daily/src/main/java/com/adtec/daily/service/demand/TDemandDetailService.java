package com.adtec.daily.service.demand;

import com.adtec.daily.bean.demand.TDemandDetail;
import com.adtec.daily.dao.demand.TDemandDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TDemandDetailService {
	@Autowired
    TDemandDetailMapper tDemandDetailMapper;

	/**
	 * 查询所有项目信息
	 * @return
	 */
	public List<TDemandDetail> getDemandDetail(TDemandDetail tDemandDetail) {
		return tDemandDetailMapper.selectByDemandId(tDemandDetail);
	}

}
