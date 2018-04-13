package com.adtec.daily.service.daily.impl;

import com.adtec.daily.bean.daily.TDaily;
import com.adtec.daily.bean.daily.TDailyExample;
import com.adtec.daily.bean.user.TUser;
import com.adtec.daily.dao.daily.TDailyMapper;
import com.adtec.daily.service.daily.TDailyService;
import com.adtec.daily.util.Constants;
import com.adtec.daily.util.StringUtil;
import com.adtec.daily.util.ToolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TDailyServiceImpl implements TDailyService {

	@Autowired
    TDailyMapper tDailyMapper;

	/**
	 * 日报列表查询
	 */
	public List<TDaily> getDailyList(TDaily record) {
        List<TDaily> tDailyList = tDailyMapper.selectDailyListByUserId(record);
		return tDailyList;
	}

    /**
     * 日报保存
     */
	public void saveDaily(TDaily tDaily) {
	    tDailyMapper.insertSelective(tDaily);
    }

    /**
     * 条件查询日报信息
     */
    public List<TDaily> getDailyAllList(TDailyExample example){
        List<TDaily> tDailyList = tDailyMapper.selectByExample(example);
        return tDailyList;
    }

	/**
	 * 导出个人日报
	 */
	public List<Map<String, Object>> dailyExport(List<TUser> users, String startDate, String endDate){
		List<Map<String, Object>> reportList = new ArrayList<>();
		for(int i=0;i<users.size();i++){
			//1.获取日报数据
			List<TDaily> dailyList = tDailyMapper.selectDailyListByUserIdAndDailyDate(users.get(i).getUserId(),startDate,endDate);

			//2.组装日报数据
			List<TDaily> nextWeekList = new ArrayList<>();
			Map<String,TDaily> dailyMap = new HashMap<>();
			for(int j=0;j<dailyList.size();j++){
				TDaily daily = dailyList.get(j);

				//组装下周计划数据
				if(StringUtils.isNotEmpty(daily.getNextWorkPlan())){
					TDaily nextDaily = new TDaily();
					nextDaily.setNextWorkPlan(daily.getNextWorkPlan());
					nextDaily.setLineNumber(StringUtil.stringRowNum(daily.getNextWorkPlan(),45));
					nextWeekList.add(nextDaily);
				}

				String dailyDate = ToolUtil.dateToString(daily.getDailyDate(),"yyyy-MM-dd");
				if(dailyMap.get(dailyDate)!=null){
					dailyMap.put(dailyDate,dailyMap.get(dailyDate));
				}else{
					dailyMap.put(dailyDate,new TDaily());
				}
				//遍历map判断日期是否存在map中，若存在则取出list里面的数据放到map中
				Iterator keys = dailyMap.keySet().iterator();
				while(keys.hasNext()){
					String key = (String)keys.next();
					if(dailyDate.equals(key)){
						int[] lineNumberArray = {24,12,45};
						dailyToWeekly(key,dailyMap,daily,lineNumberArray);
					}
				}

			}

			List<TDaily> thisWeekList = new ArrayList<>();
			TreeMap<String, TDaily> treeMap = new TreeMap<>(dailyMap);
			for(String key:treeMap.keySet()){
				thisWeekList.add(treeMap.get(key));
			}

			Map<String, Object> data = new HashMap<>();
			data.put("thisWeeks", thisWeekList);
			data.put("nextWeeks", nextWeekList);
			data.put("name", users.get(i).getUserName());
			data.put("startDate",startDate);
			data.put("endDate", endDate);
			data.put("sheetTitle",users.get(i).getUserName());
			reportList.add(data);
		}
		return reportList;
	}

	/**
	 * 导出长安信托个人周报
	 */
	public List<Map<String, Object>> caitcWeeklyExport(List<TUser> users,String startDate,String endDate){
		List<Map<String, Object>> reportList = new ArrayList<>();
		for(int i=0;i<users.size();i++){
			//1.获取周报数据
			List<TDaily> dailyList = tDailyMapper.selectDailyListByUserIdAndDailyDate(users.get(i).getUserId(),startDate,endDate);

			//2.组装周报数据
			Map<String, TDaily> weeklyData = new HashMap<>();
			//初始化需求分析数据
			TDaily analysis = new TDaily();
			analysis.setWorkType(Constants.WORK_TYPE_ANALYSIS);
			analysis.setLineNumber(1);
			analysis.setPlanLineNumber(1);
			weeklyData.put(Constants.WORK_TYPE_ANALYSIS,analysis);
			//初始化设计数据
			TDaily design = new TDaily();
			design.setWorkType(Constants.WORK_TYPE_DESIGN);
			design.setLineNumber(1);
			design.setPlanLineNumber(1);
			weeklyData.put(Constants.WORK_TYPE_DESIGN,design);
			//初始化开发数据
			TDaily development = new TDaily();
			development.setWorkType(Constants.WORK_TYPE_DEVELOPMENT);
			development.setLineNumber(1);
			development.setPlanLineNumber(1);
			weeklyData.put(Constants.WORK_TYPE_DEVELOPMENT,development);
			//初始化测试数据
			TDaily test = new TDaily();
			test.setWorkType(Constants.WORK_TYPE_TEST);
			test.setLineNumber(1);
			test.setPlanLineNumber(1);
			weeklyData.put(Constants.WORK_TYPE_TEST,test);
			//初始化版本发布数据
			TDaily version = new TDaily();
			version.setWorkType(Constants.WORK_TYPE_VERSION);
			version.setLineNumber(1);
			version.setPlanLineNumber(1);
			weeklyData.put(Constants.WORK_TYPE_VERSION,version);
			//初始化运维支持数据
			TDaily operation = new TDaily();
			operation.setWorkType(Constants.WORK_TYPE_OPERATION);
			operation.setLineNumber(1);
			operation.setPlanLineNumber(1);
			weeklyData.put(Constants.WORK_TYPE_OPERATION,operation);
			//初始化会议数据
			TDaily meeting = new TDaily();
			meeting.setWorkType(Constants.WORK_TYPE_MEETING);
			meeting.setLineNumber(1);
			meeting.setPlanLineNumber(1);
			weeklyData.put(Constants.WORK_TYPE_MEETING,meeting);
			//初始化其他数据
			TDaily other = new TDaily();
			other.setWorkType(Constants.WORK_TYPE_OTHER);
			other.setLineNumber(1);
			other.setPlanLineNumber(1);
			weeklyData.put(Constants.WORK_TYPE_OTHER,other);

			for(int j=0;j<dailyList.size();j++){
				TDaily weekly = dailyList.get(j);
				int[] lineNumberArray = {26,21,57};
				if(Constants.WORK_TYPE_ANALYSIS.equals(weekly.getWorkType())){//需求分析
					dailyToWeekly(Constants.WORK_TYPE_ANALYSIS,weeklyData,weekly,lineNumberArray);
				}else if(Constants.WORK_TYPE_DESIGN.equals(weekly.getWorkType())){//设计
					dailyToWeekly(Constants.WORK_TYPE_DESIGN,weeklyData,weekly,lineNumberArray);
				}else if(Constants.WORK_TYPE_DEVELOPMENT.equals(weekly.getWorkType())){//开发
					dailyToWeekly(Constants.WORK_TYPE_DEVELOPMENT,weeklyData,weekly,lineNumberArray);
				}else if(Constants.WORK_TYPE_TEST.equals(weekly.getWorkType())){//测试
					dailyToWeekly(Constants.WORK_TYPE_TEST,weeklyData,weekly,lineNumberArray);
				}else if(Constants.WORK_TYPE_VERSION.equals(weekly.getWorkType())){//版本发布
					dailyToWeekly(Constants.WORK_TYPE_VERSION,weeklyData,weekly,lineNumberArray);
				}else if(Constants.WORK_TYPE_OPERATION.equals(weekly.getWorkType())){//运维支持
					dailyToWeekly(Constants.WORK_TYPE_OPERATION,weeklyData,weekly,lineNumberArray);
				}else if(Constants.WORK_TYPE_MEETING.equals(weekly.getWorkType())){//会议
					dailyToWeekly(Constants.WORK_TYPE_MEETING,weeklyData,weekly,lineNumberArray);
				}else if(Constants.WORK_TYPE_OTHER.equals(weekly.getWorkType())){//其他
					dailyToWeekly(Constants.WORK_TYPE_OTHER,weeklyData,weekly,lineNumberArray);
				}
			}

			List<TDaily> thisWeekList = new ArrayList<>();
			for (String key : weeklyData.keySet()) {
				thisWeekList.add(weeklyData.get(key));
			}

			Map<String, Object> data = new HashMap<>();
			data.put("thisWeeks", thisWeekList);
			data.put("name", users.get(i).getUserName());
			data.put("startDate",startDate);
			data.put("endDate", endDate);
			data.put("sheetTitle",users.get(i).getUserName());
			reportList.add(data);
		}
		return reportList;
	}

	/**
	 * 导出长安信托项目周报
	 */
	public List<TDaily> caitcProjectWeeklyExport(List<TUser> users,String startDate,String endDate){
		List<TDaily> thisWeekList = new ArrayList<>();
		List<TDaily> list = new ArrayList<>();
		//1.获取项目周报数据
		for(int i=0;i<users.size();i++){
			List<TDaily> dailyList = tDailyMapper.selectDailyListByUserIdAndDailyDate(users.get(i).getUserId(),startDate,endDate);
			list.addAll(dailyList);
		}
		//2.组装项目周报数据
		for(int i=0;i<list.size();i++){
			TDaily weekly = list.get(i);
			int[] lineNumberArray = {26,26,26};
			dailyToProjectWeekly(weekly,lineNumberArray);
			thisWeekList.add(weekly);
		}
		return thisWeekList;
	}

	/**
	 * 根据日报数据生成周报数据
	 */
	public void dailyToWeekly(String key,Map<String,TDaily> weeklyData,TDaily weekly,int[] lineNumberArray){
		int workContentNum = 0;//日报内容所占行数
		int problemNum = 0;//问题所占行数
		//组装日报内容和完成情况数据（日报内容：一行只能放下26个字）
		if(StringUtils.isEmpty((weeklyData.get(key)).getDailyContent())){//没有数据
			if(StringUtils.isNotEmpty(weekly.getDailyContent())){
				weekly.setDailyContent("1、" + weekly.getDailyContent());
				//计算当前日报内容所占行数
				int lastNum = StringUtil.stringRowNum(weekly.getDailyContent(),lineNumberArray[0]);
				weekly.setExecutionJoint(weekly.getExecutionShow());
				for(int x=1;x<lastNum;x++){
					weekly.setExecutionJoint(weekly.getExecutionJoint() + "&#10;");
				}
			}
		}else{//有数据
			String workContent = (weeklyData.get(key)).getDailyContent();
			String executionJoint = (weeklyData.get(key)).getExecutionJoint();
			if(workContent.indexOf("&#10;")>0){//不止一条数据
				//计算出已有日报内容序号
				int dataNum = StringUtil.dailyNo(workContent);
				if(StringUtils.isNotEmpty(weekly.getDailyContent())){
					//计算出当前日报内容行数
					int lastNum = StringUtil.stringRowNum((dataNum+1) + "、"+ weekly.getDailyContent(),lineNumberArray[0]);
					weekly.setDailyContent(workContent+"&#10;"+ (dataNum+1) +"、"+weekly.getDailyContent());
					weekly.setExecutionJoint(executionJoint+"&#10;"+ weekly.getExecutionShow());
					for(int x=1;x<lastNum;x++){
						weekly.setExecutionJoint(weekly.getExecutionJoint() + "&#10;");
					}
				}

			}else{//只有一条数据
				if(StringUtils.isNotEmpty(weekly.getDailyContent())){
					//计算出当前日报内容行数
					int lastNum = StringUtil.stringRowNum("2、" + weekly.getDailyContent(),lineNumberArray[0]);
					weekly.setDailyContent(workContent+"&#10;2、"+weekly.getDailyContent());
					weekly.setExecutionJoint(executionJoint+"&#10;"+ weekly.getExecutionShow());
					for(int x=1;x<lastNum;x++){
						weekly.setExecutionJoint(weekly.getExecutionJoint() + "&#10;");
					}
				}
			}
		}

		//组装问题数据：一行只能放下21个字
		if(StringUtils.isEmpty((weeklyData.get(key)).getProblem())){//没数据
			if(StringUtils.isNotEmpty(weekly.getProblem())){
				weekly.setProblem("1、"+ weekly.getProblem());
			}
		}else{//有数据
			String problem = (weeklyData.get(key)).getProblem();
			if(problem.indexOf("&#10;")>0){//不止一条数据
				//计算出已有问题序号
				int dataNum = StringUtil.dailyNo(problem);
				if(StringUtils.isNotEmpty(weekly.getProblem())){
					weekly.setProblem(problem + "&#10;" + (dataNum + 1) + "、" + weekly.getProblem());
				}else{
					weekly.setProblem(problem);
				}
			}else{//只有一条数据
				if(StringUtils.isNotEmpty(weekly.getProblem())){
					weekly.setProblem(problem+"&#10;2、"+weekly.getProblem());
				}else{
					weekly.setProblem(problem);
				}
			}
		}

		//组装下周计划数据：一行只能放下57个字
		if(StringUtils.isEmpty((weeklyData.get(key)).getNextWorkPlan())){//没数据
			if(StringUtils.isNotEmpty(weekly.getNextWorkPlan())){
				weekly.setNextWorkPlan("1、"+ weekly.getNextWorkPlan());
				weekly.setPlanLineNumber(StringUtil.dailyRowNum(weekly.getNextWorkPlan(),lineNumberArray[2]));
			}else{
				weekly.setPlanLineNumber(1);
			}
		}else{//有数据
			String plan = (weeklyData.get(key)).getNextWorkPlan();
			if(plan.indexOf("&#10;")>0){//不止一条数据
				//计算出已有计划序号
				int dataNum = StringUtil.dailyNo(plan);
				if(StringUtils.isNotEmpty(weekly.getNextWorkPlan())){
					weekly.setNextWorkPlan(plan + "&#10;" + (dataNum + 1) + "、" + weekly.getNextWorkPlan());
				}else{
					weekly.setNextWorkPlan(plan);
				}
			}else{//只有一条数据
				if(StringUtils.isNotEmpty(weekly.getNextWorkPlan())){
					weekly.setNextWorkPlan(plan+"&#10;2、"+weekly.getNextWorkPlan());
				}else{
					weekly.setNextWorkPlan(plan);
				}
			}
			weekly.setPlanLineNumber(StringUtil.dailyRowNum(weekly.getNextWorkPlan(),lineNumberArray[2]));
		}


		//比较日报内容和问题所占行数，取出最大行数存入对象中。
		//计算出所有日报内容行数
		workContentNum = StringUtil.dailyRowNum(weekly.getDailyContent(),lineNumberArray[0]);
		//计算出所有问题内容行数
		problemNum = StringUtil.dailyRowNum(weekly.getProblem(),lineNumberArray[1]);
		if(workContentNum>=problemNum){
			weekly.setLineNumber(workContentNum);
		}else{
			weekly.setLineNumber(problemNum);
		}

		weeklyData.put(key,weekly);
	}

	/**
	 * 根据日报数据生成项目周报数据
	 */
	public void dailyToProjectWeekly(TDaily weekly,int[] lineNumberArray) {
		//组装日报内容（日报内容：一行只能放下26个字）
		if(StringUtils.isNotEmpty(weekly.getDailyContent())){
			//计算当前日报内容所占行数
			int lineNum = StringUtil.stringRowNum(weekly.getDailyContent(),lineNumberArray[0]);
			weekly.setLineNumber(lineNum);
		}else{
			weekly.setLineNumber(1);
		}

		//组装问题数据：一行只能放下26个字
		if(StringUtils.isNotEmpty(weekly.getProblem())){
			//计算问题内容所占行数
			int lineNum = StringUtil.stringRowNum(weekly.getProblem(),lineNumberArray[1]);
			weekly.setLineNumber(lineNum);
		}

		//组装下周计划数据：一行只能放下26个字
		if(StringUtils.isNotEmpty(weekly.getNextWorkPlan())){
			//计算下周计划所占行数
			int lineNum = StringUtil.stringRowNum(weekly.getNextWorkPlan(),lineNumberArray[2]);
			weekly.setPlanLineNumber(lineNum);
		}
	}
}
