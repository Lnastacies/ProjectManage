package com.adtec.daily.controller.user;

import com.adtec.daily.bean.common.Msg;
import com.adtec.daily.bean.user.UserFeedBack;
import com.adtec.daily.bean.user.UserFeedBackExample;
import com.adtec.daily.service.user.UserFeedBackService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @version V1.0
 * @Description: 意见反馈
 * @author: 胡浪
 * @date: 2018/4/25
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
@Controller
public class UserFeedBackController {

    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    UserFeedBackService userFeedBackService;

    /**
     * 意见反馈
     * @param userFeedBack
     * @param result
     * @return
     */
    @RequestMapping(value = "/feedBack/putFeedBack", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveFeedBack(@Valid UserFeedBack userFeedBack, BindingResult result) {
        //查询意见反馈表是否存在该用户数据，如果存在，需要间隔5分钟再发，序号最大的
        UserFeedBack userFeedBack1 = userFeedBackService.queryUserFeedBack(userFeedBack.getFbUser());
        if (userFeedBack1 != null) {
            //若存在，则判断两者之间之间间隔
            double minute = ((double)(new Date().getTime()-userFeedBack1.getCreateDate().getTime()))/(60*1000);
            long m = (long) Math.ceil(minute);
            logger.info("分钟时间间隔：" + m);
            if (m < 5) {
                return Msg.intervalLsFive();
            }
        }
        userFeedBack.setFbUser(userFeedBack.getFbUser());
        userFeedBack.setFbType(userFeedBack.getFbType());
        userFeedBack.setFbInfo(userFeedBack.getFbInfo());
        userFeedBack.setFbEmail(userFeedBack.getFbEmail());
        userFeedBack.setCreateDate(new Date());
        userFeedBackService.putFeedBack(userFeedBack);
        return Msg.success();
    }

}
