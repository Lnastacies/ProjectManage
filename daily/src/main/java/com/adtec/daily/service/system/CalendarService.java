package com.adtec.daily.service.system;

import com.adtec.daily.bean.system.TCalendar;

/**
 * @version V1.0
 * @Description: 万年历
 * @author: 胡浪
 * @date: 2018/9/28
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
public interface CalendarService {

    /**
     * 新增
     */
    void saveCalendar(TCalendar calendar) throws Exception;
}
