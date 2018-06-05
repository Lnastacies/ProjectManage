package com.adtec.daily.util;

public class Constants {
    /**
     * 日报类型
     */
    public static final String WORK_TYPE_ANALYSIS = "01";//需求分析
    public static final String WORK_TYPE_DESIGN = "02";//设计
    public static final String WORK_TYPE_DEVELOPMENT = "03";//开发
    public static final String WORK_TYPE_TEST = "04";//测试
    public static final String WORK_TYPE_VERSION = "05";//版本发布
    public static final String WORK_TYPE_OPERATION = "06";//运维支持
    public static final String WORK_TYPE_MEETING = "07";//会议
    public static final String WORK_TYPE_OTHER = "08";//其他

    /**
     * 完成情况
     */
    public static final String EXECUTION_NOT_STARTED = "01";//未开始
    public static final String EXECUTION_IN_PROGRESS = "02";//正在进行
    public static final String EXECUTION_COMPLETED = "03";//已完成

    /**
     * 角色类型
     */
    public static final String ROLE_TYPE_PROJECT = "00";//项目角色
    public static final String ROLE_TYPE_DEPARTMENT = "01";//部门角色

    /**
     * 角色ID
     */
    public static final String ROLE_ID_ADMIN = "4";//系统管理员
    public static final String ROLE_ID_PROJECT_BOSS = "5";//项目经理
    public static final String ROLE_ID_PROJECT_MEMBER = "6";//项目成员
    public static final String ROLE_ID_COMPANY_LEADER = "7";//公司领导
    public static final String ROLE_ID_COMPANY_MEMBER = "8";//公司员工
    public static final String ROLE_ID_GOD_BOSS = "9";//客户项目经理
    public static final String ROLE_ID_GOD_MEMBER = "10";//客户项目成员

    /**
     * 模板类型
     */
    public static final String PERSONAL_WEEKLY = "00";//个人周报
    public static final String PROJECT_WEEKLY = "01";//项目周报

    /**
     * 模板文件名
     */
    public static final String CAITC_DAILY = "caitcDaily.ftl";//长安信托个人日报
    public static final String CAITC_WEEKLY = "caitcWeekly.ftl";//长安信托个人周报
    public static final String CAITC_PROJECT_WEEKLY = "caitcProjectWeekly.ftl";//长安信托项目周报
    public static final String ADTEC_WEEKLY = "adtecWeekly.ftl";//先进数通个人周报
    public static final String OVER_WORK = "overWork.ftl";//加班补贴表
    public static final String DEMAND = "demand.ftl";//业务需求跟踪表
}
