package com.adtec.daily.service.project;

import com.adtec.daily.bean.project.TProject;
import com.adtec.daily.bean.project.TProjectExample;
import com.adtec.daily.bean.project.TProjectExample.Criteria;
import com.adtec.daily.util.DateUtil;
import com.adtec.daily.dao.project.TProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TProjectService {
    @Autowired
    TProjectMapper tProjectMapper;

    /**
     * 查询所有项目信息
     *
     * @return
     */
    public List<TProject> getAll(TProjectExample example) {
        return tProjectMapper.selectByExample(example);
    }

    /**
     * 项目保存
     *
     * @param tProject
     */
    public void saveProject(TProject tProject) {
        tProject.setWorkStartTime(DateUtil.getTimeShort(tProject.getWorkStartTimeStr()));
        tProject.setWorkEndTime(DateUtil.getTimeShort(tProject.getWorkEndTimeStr()));
        tProject.setOverworkStartTime(DateUtil.getTimeShort(tProject.getOverworkStartTimeStr()));
        tProjectMapper.insertSelective(tProject);
    }

    /**
     * 检验项目编号是否可用
     *
     * @param projectCode
     * @return true：代表当前姓名可用   fasle：不可用
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
     *
     * @param id
     * @return
     */
    public TProject getProject(int id) {
        TProject tProject = tProjectMapper.selectByPrimaryKey(id);
        return tProject;
    }

    /**
     * 项目更新
     *
     * @param tProject
     */
    public void updateProject(TProject tProject) {
        tProjectMapper.updateByPrimaryKeySelective(tProject);
    }

    /**
     * 项目删除
     *
     * @param id
     */
    public void deleteProject(Integer id) {
        tProjectMapper.deleteByPrimaryKey(id);
    }


}
