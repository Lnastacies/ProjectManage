package com.adtec.daily.service.daily.impl;

import com.adtec.daily.bean.daily.TOverWork;
import com.adtec.daily.bean.daily.TOverWorkExample;
import com.adtec.daily.dao.daily.TOverWorkMapper;
import com.adtec.daily.service.daily.TOverWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TOverWorkServiceImpl implements TOverWorkService{
	@Autowired
    TOverWorkMapper tOverWorkMapper;

	/**
	 * 查询所有加班数据
	 */
	public List<TOverWork> getAll(TOverWorkExample example) {
		return tOverWorkMapper.selectByExample(example);
	}

	/**
	 * 加班数据保存
	 */
	public void saveOverWork(TOverWork tOverWork) {
        tOverWorkMapper.insertSelective(tOverWork);
	}

	/**
	 * 根据用户id及上班日期查询加班明细列表
	 */
	public List<TOverWork> selectOverWorkDetailListByUserIdAndWorkDate(String userId,String startDate,String endDate) {
		return tOverWorkMapper.selectOverWorkDetailListByUserIdAndWorkDate(userId,startDate,endDate);
	}

	/**
	 * 根据用户id及上班日期查询加班总时长
	 */
	public TOverWork selectOverWorkByUserIdAndWorkDate(String userId,String startDate,String endDate) {
		return tOverWorkMapper.selectOverWorkByUserIdAndWorkDate(userId,startDate,endDate);
	}


}
