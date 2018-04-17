package com.adtec.daily.bean.project;

import java.util.Date;

public class TProject {
    private Integer id;

    private String projectCode;

    private String projectName;

    private String projectUserId;

    private String serviceObjectId;

    private String dailyModuleId;

    private Date workStartTime;

    private Date workEndTime;

    private Date overworkStartTime;

    private String workStartTimeStr;

    private String workEndTimeStr;

    private String overworkStartTimeStr;

    private Date createTime;

    private String createUserId;

    private Date updateTime;

    private String updateUserId;

    private String remark1;

    private String remark2;

    private String remark3;

    // 项目负责人名称
    private String userName;

    // 用户编号
    private String userId;

    // 日报ID
    private String dailyId;

    // 日报日期
    private Date dailyDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectUserId() {
        return projectUserId;
    }

    public void setProjectUserId(String projectUserId) {
        this.projectUserId = projectUserId == null ? null : projectUserId.trim();
    }

    public String getServiceObjectId() {
        return serviceObjectId;
    }

    public void setServiceObjectId(String serviceObjectId) {
        this.serviceObjectId = serviceObjectId == null ? null : serviceObjectId.trim();
    }

    public String getDailyModuleId() {
        return dailyModuleId;
    }

    public void setDailyModuleId(String dailyModuleId) {
        this.dailyModuleId = dailyModuleId == null ? null : dailyModuleId.trim();
    }

    public Date getWorkStartTime() {
        return workStartTime;
    }

    public void setWorkStartTime(Date workStartTime) {
        this.workStartTime = workStartTime;
    }

    public Date getWorkEndTime() {
        return workEndTime;
    }

    public void setWorkEndTime(Date workEndTime) {
        this.workEndTime = workEndTime;
    }

    public Date getOverworkStartTime() {
        return overworkStartTime;
    }

    public void setOverworkStartTime(Date overworkStartTime) {
        this.overworkStartTime = overworkStartTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    public String getWorkStartTimeStr() {
        return workStartTimeStr;
    }

    public void setWorkStartTimeStr(String workStartTimeStr) {
        this.workStartTimeStr = workStartTimeStr;
    }

    public String getWorkEndTimeStr() {
        return workEndTimeStr;
    }

    public void setWorkEndTimeStr(String workEndTimeStr) {
        this.workEndTimeStr = workEndTimeStr;
    }

    public String getOverworkStartTimeStr() {
        return overworkStartTimeStr;
    }

    public void setOverworkStartTimeStr(String overworkStartTimeStr) {
        this.overworkStartTimeStr = overworkStartTimeStr;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDailyId() {
        return dailyId;
    }

    public void setDailyId(String dailyId) {
        this.dailyId = dailyId;
    }

    public Date getDailyDate() {
        return dailyDate;
    }

    public void setDailyDate(Date dailyDate) {
        this.dailyDate = dailyDate;
    }
}