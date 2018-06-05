package com.adtec.daily.service.user;

import com.adtec.daily.bean.user.UserProjectExample;

/**
 * @version V1.0
 * @Description: 类名
 * @author: 胡浪
 * @date: 2018/4/28
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
public interface UserProjectService {

    /**
     * 根据条件删除用户项目关联表
     *
     * @param example
     */
    void deleteUserProjectByExample(UserProjectExample example);
}
