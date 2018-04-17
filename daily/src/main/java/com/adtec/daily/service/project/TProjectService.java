package com.adtec.daily.service.project;

import com.adtec.daily.bean.project.TProject;

import java.util.List;

/**
 * @version V1.0
 * @Description: 类名
 * @author: 胡浪
 * @date: 2018/4/13
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
public interface TProjectService {

    /**
     * 管理员查询所有项目信息
     *
     * @return
     */
    public List<TProject> getAll();

    /**
     * 根据员工查询所属项目信息
     *
     * @param userId
     * @return
     */
    public List<TProject> getProjectByUser(String userId);

    /**
     * 项目保存
     *
     * @param tProject
     */
    public void saveProject(TProject tProject);

    /**
     * 检验项目编号是否可用
     * @param projectCode
     * @return true：代表当前姓名可用   fasle：不可用
     */
    public boolean checkProject(String projectCode);

    /**
     * 按照主键id查询项目信息
     *
     * @param id
     * @return
     */
    public TProject getProject(int id);

    /**
     * 项目更新
     *
     * @param tProject
     */
    public void updateProject(TProject tProject);

    /**
     * 项目删除
     *
     * @param id
     */
    public void deleteProject(Integer id);

    /**
     * 登陆带出项目信息
     *
     * @param userId
     * @return
     */
    public TProject selectByUserId(String userId);

    /**
     * 根据添加的日报信息获取项目下班时间
     *
     * @param dailyId
     * @return
     */
    public TProject getProjectByDailyId(String dailyId);
}
