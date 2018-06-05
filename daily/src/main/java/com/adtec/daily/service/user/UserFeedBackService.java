package com.adtec.daily.service.user;

import com.adtec.daily.bean.user.UserFeedBack;
import com.adtec.daily.bean.user.UserFeedBackExample;

import java.util.List;

/**
 * @version V1.0
 * @Description: 意见反馈
 * @author: 胡浪
 * @date: 2018/4/25
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
public interface UserFeedBackService {

    /**
     * 意见反馈
     *
     * @param userFeedBack
     */
    void putFeedBack(UserFeedBack userFeedBack);

    /**
     * 查询意见反馈信息
     */
    UserFeedBack queryUserFeedBack(String fbUser);
}
