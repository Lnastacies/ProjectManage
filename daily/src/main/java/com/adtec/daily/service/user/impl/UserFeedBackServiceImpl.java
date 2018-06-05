package com.adtec.daily.service.user.impl;

import com.adtec.daily.bean.user.UserFeedBack;
import com.adtec.daily.bean.user.UserFeedBackExample;
import com.adtec.daily.dao.user.UserFeedBackMapper;
import com.adtec.daily.service.user.UserFeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version V1.0
 * @Description: 类名
 * @author: 胡浪
 * @date: 2018/4/25
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
@Service
public class UserFeedBackServiceImpl implements UserFeedBackService{

    @Autowired
    UserFeedBackMapper userFeedBackMapper;

    /**
     * 意见反馈
     */
    @Override
    public void putFeedBack(UserFeedBack userFeedBack) {
        userFeedBackMapper.insertSelective(userFeedBack);
    }

    /**
     * 查询意见反馈信息
     */
    @Override
    public UserFeedBack queryUserFeedBack(String fbUser) {
        UserFeedBack userFeedBack = userFeedBackMapper.selectBySessionIdAndfbIdMax(fbUser);
        return userFeedBack;
    }
}
