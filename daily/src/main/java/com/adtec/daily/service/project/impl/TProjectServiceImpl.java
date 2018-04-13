package com.adtec.daily.service.project.impl;

import com.adtec.daily.bean.project.TProject;
import com.adtec.daily.bean.project.TProjectExample;
import com.adtec.daily.bean.project.TProjectExample.Criteria;
import com.adtec.daily.dao.project.TProjectMapper;
import com.adtec.daily.service.project.TProjectService;
import com.adtec.daily.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TProjectServiceImpl implements TProjectService{

    @Autowired
    TProjectMapper tProjectMapper;

    /**
     * 管理员查询所有项目信息
     *
     * @return
     */
    public List<TProject> getAll() {
        return tProjectMapper.selectAllProject();
    }

    /**
     * 根据员工查询所属项目信息
     */
    public List<TProject> getProjectByUser(String userId) {
        return tProjectMapper.selectProjectByUser(userId);
    }

    /**
     * 项目保存
     */
    public void saveProject(TProject tProject) {
        tProject.setWorkStartTime(DateUtil.getTimeShort(tProject.getWorkStartTimeStr()));
        tProject.setWorkEndTime(DateUtil.getTimeShort(tProject.getWorkEndTimeStr()));
        tProject.setOverworkStartTime(DateUtil.getTimeShort(tProject.getOverworkStartTimeStr()));
        tProjectMapper.insertSelective(tProject);
    }

    /**
     * 检验项目编号是否可用
     */
    public boolean checkProject(String projectCode) {
        TProjectExample example = new TProjectExample();
        Criteria criteria = example.createCriteria();
        criteria.andProjectCodeEqualTo(projectCode);
        long count = tProjectMapper.countByExample(example);
        return count == 0;
    }

    /**
     * 按照主键id查询项目信息
     */
    public TProject getProject(int id) {
        TProject tProject = tProjectMapper.selectInfoByPrimaryKey(id);
        return tProject;
    }

    /**
     * 项目更新
     */
    public void updateProject(TProject tProject) {
        tProjectMapper.updateByPrimaryKeySelective(tProject);
    }

    /**
     * 项目删除
     */
    public void deleteProject(Integer id) {
        tProjectMapper.deleteByPrimaryKey(id);
    }

    /**
     * 登陆带出项目信息
     */
    public TProject selectByUserId(String userId) {
        TProject project = tProjectMapper.selectByUserId(userId);
        return  project;
    }
}
