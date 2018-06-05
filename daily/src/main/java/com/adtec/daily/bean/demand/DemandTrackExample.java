package com.adtec.daily.bean.demand;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DemandTrackExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DemandTrackExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDemandIdIsNull() {
            addCriterion("demand_id is null");
            return (Criteria) this;
        }

        public Criteria andDemandIdIsNotNull() {
            addCriterion("demand_id is not null");
            return (Criteria) this;
        }

        public Criteria andDemandIdEqualTo(String value) {
            addCriterion("demand_id =", value, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdNotEqualTo(String value) {
            addCriterion("demand_id <>", value, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdGreaterThan(String value) {
            addCriterion("demand_id >", value, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdGreaterThanOrEqualTo(String value) {
            addCriterion("demand_id >=", value, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdLessThan(String value) {
            addCriterion("demand_id <", value, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdLessThanOrEqualTo(String value) {
            addCriterion("demand_id <=", value, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdLike(String value) {
            addCriterion("demand_id like", value, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdNotLike(String value) {
            addCriterion("demand_id not like", value, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdIn(List<String> values) {
            addCriterion("demand_id in", values, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdNotIn(List<String> values) {
            addCriterion("demand_id not in", values, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdBetween(String value1, String value2) {
            addCriterion("demand_id between", value1, value2, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdNotBetween(String value1, String value2) {
            addCriterion("demand_id not between", value1, value2, "demandId");
            return (Criteria) this;
        }

        public Criteria andProjectCodeIsNull() {
            addCriterion("project_code is null");
            return (Criteria) this;
        }

        public Criteria andProjectCodeIsNotNull() {
            addCriterion("project_code is not null");
            return (Criteria) this;
        }

        public Criteria andProjectCodeEqualTo(String value) {
            addCriterion("project_code =", value, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectCodeNotEqualTo(String value) {
            addCriterion("project_code <>", value, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectCodeGreaterThan(String value) {
            addCriterion("project_code >", value, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectCodeGreaterThanOrEqualTo(String value) {
            addCriterion("project_code >=", value, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectCodeLessThan(String value) {
            addCriterion("project_code <", value, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectCodeLessThanOrEqualTo(String value) {
            addCriterion("project_code <=", value, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectCodeLike(String value) {
            addCriterion("project_code like", value, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectCodeNotLike(String value) {
            addCriterion("project_code not like", value, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectCodeIn(List<String> values) {
            addCriterion("project_code in", values, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectCodeNotIn(List<String> values) {
            addCriterion("project_code not in", values, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectCodeBetween(String value1, String value2) {
            addCriterion("project_code between", value1, value2, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectCodeNotBetween(String value1, String value2) {
            addCriterion("project_code not between", value1, value2, "projectCode");
            return (Criteria) this;
        }

        public Criteria andDemandDateIsNull() {
            addCriterion("demand_date is null");
            return (Criteria) this;
        }

        public Criteria andDemandDateIsNotNull() {
            addCriterion("demand_date is not null");
            return (Criteria) this;
        }

        public Criteria andDemandDateEqualTo(Date value) {
            addCriterionForJDBCDate("demand_date =", value, "demandDate");
            return (Criteria) this;
        }

        public Criteria andDemandDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("demand_date <>", value, "demandDate");
            return (Criteria) this;
        }

        public Criteria andDemandDateGreaterThan(Date value) {
            addCriterionForJDBCDate("demand_date >", value, "demandDate");
            return (Criteria) this;
        }

        public Criteria andDemandDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("demand_date >=", value, "demandDate");
            return (Criteria) this;
        }

        public Criteria andDemandDateLessThan(Date value) {
            addCriterionForJDBCDate("demand_date <", value, "demandDate");
            return (Criteria) this;
        }

        public Criteria andDemandDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("demand_date <=", value, "demandDate");
            return (Criteria) this;
        }

        public Criteria andDemandDateIn(List<Date> values) {
            addCriterionForJDBCDate("demand_date in", values, "demandDate");
            return (Criteria) this;
        }

        public Criteria andDemandDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("demand_date not in", values, "demandDate");
            return (Criteria) this;
        }

        public Criteria andDemandDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("demand_date between", value1, value2, "demandDate");
            return (Criteria) this;
        }

        public Criteria andDemandDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("demand_date not between", value1, value2, "demandDate");
            return (Criteria) this;
        }

        public Criteria andDemandNameIsNull() {
            addCriterion("demand_name is null");
            return (Criteria) this;
        }

        public Criteria andDemandNameIsNotNull() {
            addCriterion("demand_name is not null");
            return (Criteria) this;
        }

        public Criteria andDemandNameEqualTo(String value) {
            addCriterion("demand_name =", value, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandNameNotEqualTo(String value) {
            addCriterion("demand_name <>", value, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandNameGreaterThan(String value) {
            addCriterion("demand_name >", value, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandNameGreaterThanOrEqualTo(String value) {
            addCriterion("demand_name >=", value, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandNameLessThan(String value) {
            addCriterion("demand_name <", value, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandNameLessThanOrEqualTo(String value) {
            addCriterion("demand_name <=", value, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandNameLike(String value) {
            addCriterion("demand_name like", value, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandNameNotLike(String value) {
            addCriterion("demand_name not like", value, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandNameIn(List<String> values) {
            addCriterion("demand_name in", values, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandNameNotIn(List<String> values) {
            addCriterion("demand_name not in", values, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandNameBetween(String value1, String value2) {
            addCriterion("demand_name between", value1, value2, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandNameNotBetween(String value1, String value2) {
            addCriterion("demand_name not between", value1, value2, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandDesIsNull() {
            addCriterion("demand_des is null");
            return (Criteria) this;
        }

        public Criteria andDemandDesIsNotNull() {
            addCriterion("demand_des is not null");
            return (Criteria) this;
        }

        public Criteria andDemandDesEqualTo(String value) {
            addCriterion("demand_des =", value, "demandDes");
            return (Criteria) this;
        }

        public Criteria andDemandDesNotEqualTo(String value) {
            addCriterion("demand_des <>", value, "demandDes");
            return (Criteria) this;
        }

        public Criteria andDemandDesGreaterThan(String value) {
            addCriterion("demand_des >", value, "demandDes");
            return (Criteria) this;
        }

        public Criteria andDemandDesGreaterThanOrEqualTo(String value) {
            addCriterion("demand_des >=", value, "demandDes");
            return (Criteria) this;
        }

        public Criteria andDemandDesLessThan(String value) {
            addCriterion("demand_des <", value, "demandDes");
            return (Criteria) this;
        }

        public Criteria andDemandDesLessThanOrEqualTo(String value) {
            addCriterion("demand_des <=", value, "demandDes");
            return (Criteria) this;
        }

        public Criteria andDemandDesLike(String value) {
            addCriterion("demand_des like", value, "demandDes");
            return (Criteria) this;
        }

        public Criteria andDemandDesNotLike(String value) {
            addCriterion("demand_des not like", value, "demandDes");
            return (Criteria) this;
        }

        public Criteria andDemandDesIn(List<String> values) {
            addCriterion("demand_des in", values, "demandDes");
            return (Criteria) this;
        }

        public Criteria andDemandDesNotIn(List<String> values) {
            addCriterion("demand_des not in", values, "demandDes");
            return (Criteria) this;
        }

        public Criteria andDemandDesBetween(String value1, String value2) {
            addCriterion("demand_des between", value1, value2, "demandDes");
            return (Criteria) this;
        }

        public Criteria andDemandDesNotBetween(String value1, String value2) {
            addCriterion("demand_des not between", value1, value2, "demandDes");
            return (Criteria) this;
        }

        public Criteria andDemandMakerIsNull() {
            addCriterion("demand_maker is null");
            return (Criteria) this;
        }

        public Criteria andDemandMakerIsNotNull() {
            addCriterion("demand_maker is not null");
            return (Criteria) this;
        }

        public Criteria andDemandMakerEqualTo(String value) {
            addCriterion("demand_maker =", value, "demandMaker");
            return (Criteria) this;
        }

        public Criteria andDemandMakerNotEqualTo(String value) {
            addCriterion("demand_maker <>", value, "demandMaker");
            return (Criteria) this;
        }

        public Criteria andDemandMakerGreaterThan(String value) {
            addCriterion("demand_maker >", value, "demandMaker");
            return (Criteria) this;
        }

        public Criteria andDemandMakerGreaterThanOrEqualTo(String value) {
            addCriterion("demand_maker >=", value, "demandMaker");
            return (Criteria) this;
        }

        public Criteria andDemandMakerLessThan(String value) {
            addCriterion("demand_maker <", value, "demandMaker");
            return (Criteria) this;
        }

        public Criteria andDemandMakerLessThanOrEqualTo(String value) {
            addCriterion("demand_maker <=", value, "demandMaker");
            return (Criteria) this;
        }

        public Criteria andDemandMakerLike(String value) {
            addCriterion("demand_maker like", value, "demandMaker");
            return (Criteria) this;
        }

        public Criteria andDemandMakerNotLike(String value) {
            addCriterion("demand_maker not like", value, "demandMaker");
            return (Criteria) this;
        }

        public Criteria andDemandMakerIn(List<String> values) {
            addCriterion("demand_maker in", values, "demandMaker");
            return (Criteria) this;
        }

        public Criteria andDemandMakerNotIn(List<String> values) {
            addCriterion("demand_maker not in", values, "demandMaker");
            return (Criteria) this;
        }

        public Criteria andDemandMakerBetween(String value1, String value2) {
            addCriterion("demand_maker between", value1, value2, "demandMaker");
            return (Criteria) this;
        }

        public Criteria andDemandMakerNotBetween(String value1, String value2) {
            addCriterion("demand_maker not between", value1, value2, "demandMaker");
            return (Criteria) this;
        }

        public Criteria andDemandAuditorIsNull() {
            addCriterion("demand_auditor is null");
            return (Criteria) this;
        }

        public Criteria andDemandAuditorIsNotNull() {
            addCriterion("demand_auditor is not null");
            return (Criteria) this;
        }

        public Criteria andDemandAuditorEqualTo(String value) {
            addCriterion("demand_auditor =", value, "demandAuditor");
            return (Criteria) this;
        }

        public Criteria andDemandAuditorNotEqualTo(String value) {
            addCriterion("demand_auditor <>", value, "demandAuditor");
            return (Criteria) this;
        }

        public Criteria andDemandAuditorGreaterThan(String value) {
            addCriterion("demand_auditor >", value, "demandAuditor");
            return (Criteria) this;
        }

        public Criteria andDemandAuditorGreaterThanOrEqualTo(String value) {
            addCriterion("demand_auditor >=", value, "demandAuditor");
            return (Criteria) this;
        }

        public Criteria andDemandAuditorLessThan(String value) {
            addCriterion("demand_auditor <", value, "demandAuditor");
            return (Criteria) this;
        }

        public Criteria andDemandAuditorLessThanOrEqualTo(String value) {
            addCriterion("demand_auditor <=", value, "demandAuditor");
            return (Criteria) this;
        }

        public Criteria andDemandAuditorLike(String value) {
            addCriterion("demand_auditor like", value, "demandAuditor");
            return (Criteria) this;
        }

        public Criteria andDemandAuditorNotLike(String value) {
            addCriterion("demand_auditor not like", value, "demandAuditor");
            return (Criteria) this;
        }

        public Criteria andDemandAuditorIn(List<String> values) {
            addCriterion("demand_auditor in", values, "demandAuditor");
            return (Criteria) this;
        }

        public Criteria andDemandAuditorNotIn(List<String> values) {
            addCriterion("demand_auditor not in", values, "demandAuditor");
            return (Criteria) this;
        }

        public Criteria andDemandAuditorBetween(String value1, String value2) {
            addCriterion("demand_auditor between", value1, value2, "demandAuditor");
            return (Criteria) this;
        }

        public Criteria andDemandAuditorNotBetween(String value1, String value2) {
            addCriterion("demand_auditor not between", value1, value2, "demandAuditor");
            return (Criteria) this;
        }

        public Criteria andDemandStatIsNull() {
            addCriterion("demand_stat is null");
            return (Criteria) this;
        }

        public Criteria andDemandStatIsNotNull() {
            addCriterion("demand_stat is not null");
            return (Criteria) this;
        }

        public Criteria andDemandStatEqualTo(String value) {
            addCriterion("demand_stat =", value, "demandStat");
            return (Criteria) this;
        }

        public Criteria andDemandStatNotEqualTo(String value) {
            addCriterion("demand_stat <>", value, "demandStat");
            return (Criteria) this;
        }

        public Criteria andDemandStatGreaterThan(String value) {
            addCriterion("demand_stat >", value, "demandStat");
            return (Criteria) this;
        }

        public Criteria andDemandStatGreaterThanOrEqualTo(String value) {
            addCriterion("demand_stat >=", value, "demandStat");
            return (Criteria) this;
        }

        public Criteria andDemandStatLessThan(String value) {
            addCriterion("demand_stat <", value, "demandStat");
            return (Criteria) this;
        }

        public Criteria andDemandStatLessThanOrEqualTo(String value) {
            addCriterion("demand_stat <=", value, "demandStat");
            return (Criteria) this;
        }

        public Criteria andDemandStatLike(String value) {
            addCriterion("demand_stat like", value, "demandStat");
            return (Criteria) this;
        }

        public Criteria andDemandStatNotLike(String value) {
            addCriterion("demand_stat not like", value, "demandStat");
            return (Criteria) this;
        }

        public Criteria andDemandStatIn(List<String> values) {
            addCriterion("demand_stat in", values, "demandStat");
            return (Criteria) this;
        }

        public Criteria andDemandStatNotIn(List<String> values) {
            addCriterion("demand_stat not in", values, "demandStat");
            return (Criteria) this;
        }

        public Criteria andDemandStatBetween(String value1, String value2) {
            addCriterion("demand_stat between", value1, value2, "demandStat");
            return (Criteria) this;
        }

        public Criteria andDemandStatNotBetween(String value1, String value2) {
            addCriterion("demand_stat not between", value1, value2, "demandStat");
            return (Criteria) this;
        }

        public Criteria andTestLoadIsNull() {
            addCriterion("test_load is null");
            return (Criteria) this;
        }

        public Criteria andTestLoadIsNotNull() {
            addCriterion("test_load is not null");
            return (Criteria) this;
        }

        public Criteria andTestLoadEqualTo(BigDecimal value) {
            addCriterion("test_load =", value, "testLoad");
            return (Criteria) this;
        }

        public Criteria andTestLoadNotEqualTo(BigDecimal value) {
            addCriterion("test_load <>", value, "testLoad");
            return (Criteria) this;
        }

        public Criteria andTestLoadGreaterThan(BigDecimal value) {
            addCriterion("test_load >", value, "testLoad");
            return (Criteria) this;
        }

        public Criteria andTestLoadGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("test_load >=", value, "testLoad");
            return (Criteria) this;
        }

        public Criteria andTestLoadLessThan(BigDecimal value) {
            addCriterion("test_load <", value, "testLoad");
            return (Criteria) this;
        }

        public Criteria andTestLoadLessThanOrEqualTo(BigDecimal value) {
            addCriterion("test_load <=", value, "testLoad");
            return (Criteria) this;
        }

        public Criteria andTestLoadIn(List<BigDecimal> values) {
            addCriterion("test_load in", values, "testLoad");
            return (Criteria) this;
        }

        public Criteria andTestLoadNotIn(List<BigDecimal> values) {
            addCriterion("test_load not in", values, "testLoad");
            return (Criteria) this;
        }

        public Criteria andTestLoadBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("test_load between", value1, value2, "testLoad");
            return (Criteria) this;
        }

        public Criteria andTestLoadNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("test_load not between", value1, value2, "testLoad");
            return (Criteria) this;
        }

        public Criteria andOnlindLoadIsNull() {
            addCriterion("onlind_load is null");
            return (Criteria) this;
        }

        public Criteria andOnlindLoadIsNotNull() {
            addCriterion("onlind_load is not null");
            return (Criteria) this;
        }

        public Criteria andOnlindLoadEqualTo(BigDecimal value) {
            addCriterion("onlind_load =", value, "onlindLoad");
            return (Criteria) this;
        }

        public Criteria andOnlindLoadNotEqualTo(BigDecimal value) {
            addCriterion("onlind_load <>", value, "onlindLoad");
            return (Criteria) this;
        }

        public Criteria andOnlindLoadGreaterThan(BigDecimal value) {
            addCriterion("onlind_load >", value, "onlindLoad");
            return (Criteria) this;
        }

        public Criteria andOnlindLoadGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("onlind_load >=", value, "onlindLoad");
            return (Criteria) this;
        }

        public Criteria andOnlindLoadLessThan(BigDecimal value) {
            addCriterion("onlind_load <", value, "onlindLoad");
            return (Criteria) this;
        }

        public Criteria andOnlindLoadLessThanOrEqualTo(BigDecimal value) {
            addCriterion("onlind_load <=", value, "onlindLoad");
            return (Criteria) this;
        }

        public Criteria andOnlindLoadIn(List<BigDecimal> values) {
            addCriterion("onlind_load in", values, "onlindLoad");
            return (Criteria) this;
        }

        public Criteria andOnlindLoadNotIn(List<BigDecimal> values) {
            addCriterion("onlind_load not in", values, "onlindLoad");
            return (Criteria) this;
        }

        public Criteria andOnlindLoadBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("onlind_load between", value1, value2, "onlindLoad");
            return (Criteria) this;
        }

        public Criteria andOnlindLoadNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("onlind_load not between", value1, value2, "onlindLoad");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(String value) {
            addCriterion("create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(String value) {
            addCriterion("create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(String value) {
            addCriterion("create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(String value) {
            addCriterion("create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(String value) {
            addCriterion("create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLike(String value) {
            addCriterion("create_user_id like", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotLike(String value) {
            addCriterion("create_user_id not like", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<String> values) {
            addCriterion("create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<String> values) {
            addCriterion("create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(String value1, String value2) {
            addCriterion("create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(String value1, String value2) {
            addCriterion("create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNull() {
            addCriterion("update_user_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNotNull() {
            addCriterion("update_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdEqualTo(String value) {
            addCriterion("update_user_id =", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotEqualTo(String value) {
            addCriterion("update_user_id <>", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThan(String value) {
            addCriterion("update_user_id >", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("update_user_id >=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThan(String value) {
            addCriterion("update_user_id <", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThanOrEqualTo(String value) {
            addCriterion("update_user_id <=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLike(String value) {
            addCriterion("update_user_id like", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotLike(String value) {
            addCriterion("update_user_id not like", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIn(List<String> values) {
            addCriterion("update_user_id in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotIn(List<String> values) {
            addCriterion("update_user_id not in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdBetween(String value1, String value2) {
            addCriterion("update_user_id between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotBetween(String value1, String value2) {
            addCriterion("update_user_id not between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andRemark1IsNull() {
            addCriterion("remark1 is null");
            return (Criteria) this;
        }

        public Criteria andRemark1IsNotNull() {
            addCriterion("remark1 is not null");
            return (Criteria) this;
        }

        public Criteria andRemark1EqualTo(String value) {
            addCriterion("remark1 =", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1NotEqualTo(String value) {
            addCriterion("remark1 <>", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1GreaterThan(String value) {
            addCriterion("remark1 >", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1GreaterThanOrEqualTo(String value) {
            addCriterion("remark1 >=", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1LessThan(String value) {
            addCriterion("remark1 <", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1LessThanOrEqualTo(String value) {
            addCriterion("remark1 <=", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1Like(String value) {
            addCriterion("remark1 like", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1NotLike(String value) {
            addCriterion("remark1 not like", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1In(List<String> values) {
            addCriterion("remark1 in", values, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1NotIn(List<String> values) {
            addCriterion("remark1 not in", values, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1Between(String value1, String value2) {
            addCriterion("remark1 between", value1, value2, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1NotBetween(String value1, String value2) {
            addCriterion("remark1 not between", value1, value2, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark2IsNull() {
            addCriterion("remark2 is null");
            return (Criteria) this;
        }

        public Criteria andRemark2IsNotNull() {
            addCriterion("remark2 is not null");
            return (Criteria) this;
        }

        public Criteria andRemark2EqualTo(String value) {
            addCriterion("remark2 =", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2NotEqualTo(String value) {
            addCriterion("remark2 <>", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2GreaterThan(String value) {
            addCriterion("remark2 >", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2GreaterThanOrEqualTo(String value) {
            addCriterion("remark2 >=", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2LessThan(String value) {
            addCriterion("remark2 <", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2LessThanOrEqualTo(String value) {
            addCriterion("remark2 <=", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2Like(String value) {
            addCriterion("remark2 like", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2NotLike(String value) {
            addCriterion("remark2 not like", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2In(List<String> values) {
            addCriterion("remark2 in", values, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2NotIn(List<String> values) {
            addCriterion("remark2 not in", values, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2Between(String value1, String value2) {
            addCriterion("remark2 between", value1, value2, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2NotBetween(String value1, String value2) {
            addCriterion("remark2 not between", value1, value2, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark3IsNull() {
            addCriterion("remark3 is null");
            return (Criteria) this;
        }

        public Criteria andRemark3IsNotNull() {
            addCriterion("remark3 is not null");
            return (Criteria) this;
        }

        public Criteria andRemark3EqualTo(String value) {
            addCriterion("remark3 =", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3NotEqualTo(String value) {
            addCriterion("remark3 <>", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3GreaterThan(String value) {
            addCriterion("remark3 >", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3GreaterThanOrEqualTo(String value) {
            addCriterion("remark3 >=", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3LessThan(String value) {
            addCriterion("remark3 <", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3LessThanOrEqualTo(String value) {
            addCriterion("remark3 <=", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3Like(String value) {
            addCriterion("remark3 like", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3NotLike(String value) {
            addCriterion("remark3 not like", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3In(List<String> values) {
            addCriterion("remark3 in", values, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3NotIn(List<String> values) {
            addCriterion("remark3 not in", values, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3Between(String value1, String value2) {
            addCriterion("remark3 between", value1, value2, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3NotBetween(String value1, String value2) {
            addCriterion("remark3 not between", value1, value2, "remark3");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}