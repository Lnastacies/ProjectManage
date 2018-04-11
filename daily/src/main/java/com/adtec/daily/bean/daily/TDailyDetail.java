package com.adtec.daily.bean.daily;

import java.util.Date;

public class TDailyDetail {
    private String dailyDetailId;

    private String dailyId;

    private Integer dailyNo;

    private String dailyContent;

    private Integer dailyPercent;

    private Date doneDate;

    private String problem;

    private String nextWorkPlan;

    private String workType;

    private String execution;

    private Date createTime;

    private String createUserId;

    private Date updateTime;

    private String updateUserId;

    private String remark1;

    private String remark2;

    private String remark3;

    private String dailyIds;

    private String userId;

    private String userName;

    private int pn;

    private String doneDateStr;

    public String getDailyDetailId() {
        return dailyDetailId;
    }

    public void setDailyDetailId(String dailyDetailId) {
        this.dailyDetailId = dailyDetailId == null ? null : dailyDetailId.trim();
    }

    public String getDailyId() {
        return dailyId;
    }

    public void setDailyId(String dailyId) {
        this.dailyId = dailyId == null ? null : dailyId.trim();
    }

    public Integer getDailyNo() {
        return dailyNo;
    }

    public void setDailyNo(Integer dailyNo) {
        this.dailyNo = dailyNo;
    }

    public String getDailyContent() {
        return dailyContent;
    }

    public void setDailyContent(String dailyContent) {
        this.dailyContent = dailyContent == null ? null : dailyContent.trim();
    }

    public Integer getDailyPercent() {
        return dailyPercent;
    }

    public void setDailyPercent(Integer dailyPercent) {
        this.dailyPercent = dailyPercent;
    }

    public Date getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(Date doneDate) {
        this.doneDate = doneDate;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem == null ? null : problem.trim();
    }

    public String getNextWorkPlan() {
        return nextWorkPlan;
    }

    public void setNextWorkPlan(String nextWorkPlan) {
        this.nextWorkPlan = nextWorkPlan == null ? null : nextWorkPlan.trim();
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

    public String getDailyIds() {
        return dailyIds;
    }

    public void setDailyIds(String dailyIds) {
        this.dailyIds = dailyIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPn() {
        return pn;
    }

    public void setPn(int pn) {
        this.pn = pn;
    }

    public String getDoneDateStr() {
        return doneDateStr;
    }

    public void setDoneDateStr(String doneDateStr) {
        this.doneDateStr = doneDateStr;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getExecution() {
        return execution;
    }

    public void setExecution(String execution) {
        this.execution = execution;
    }
}