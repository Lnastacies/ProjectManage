package com.adtec.daily.service.daily.impl;

import com.adtec.daily.bean.daily.OverWork;
import com.adtec.daily.bean.daily.OverWorkExample;
import com.adtec.daily.dao.daily.OverWorkMapper;
import com.adtec.daily.service.daily.OverWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version V1.0
 * @Description: 加班时间处理实现类
 * @author: 胡浪
 * @date: 2018/4/13
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
@Service
public class OverWorkServiceImpl implements OverWorkService {
	@Autowired
    OverWorkMapper overWorkMapper;

	/**
	 * 查询所有加班数据
	 */
    @Override
	public List<OverWork> getAll(OverWorkExample example) {
		return overWorkMapper.selectByExample(example);
	}

	/**
	 * 加班数据保存
	 */
    @Override
	public void saveOverWork(OverWork overWork) {
        overWorkMapper.insertSelective(overWork);
	}

    /**
     * 更新加班数据
     */
    @Override
    public void updateByDailyId(OverWork overWork) {
        overWorkMapper.updateByPrimaryKeySelective(overWork);
    }

	/**
	 * 根据用户id及上班日期查询加班明细列表
	 */
    @Override
	public List<OverWork> selectOverWorkDetailListByUserIdAndWorkDate(String userId, String startDate, String endDate) {
		return overWorkMapper.selectOverWorkDetailListByUserIdAndWorkDate(userId,startDate,endDate);
	}

	/**
	 * 根据用户id及上班日期查询加班总时长
	 */
    @Override
	public OverWork selectOverWorkByUserIdAndWorkDate(String userId, String startDate, String endDate) {
		return overWorkMapper.selectOverWorkByUserIdAndWorkDate(userId,startDate,endDate);
	}


}
