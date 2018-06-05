package com.adtec.daily.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @version V1.0
 * @Description: 日期公共类
 * @author: 胡浪
 * @date: 2018/3/12
 * @Copyright: 北京先进数通信息技术股份公司 http://www.adtec.com.cn
 */
public class DateUtil {

    /**
     * 日期转为星期
     * @param date
     * @return
     */
    public static String getWeek(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE",Locale.CHINESE);
        String week = sdf.format(date);
        return week;
    }

    /**
     * String转Date型 yyyy-MM-dd
     *
     * @param Str
     * @return
     */
    public static Date StrToDate(String Str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        Date ddate = null;
        try {
            ddate = sdf.parse(Str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ddate;
    }

    /**
     * String转Date型 HH:mm:ss
     *
     * @param Str
     * @return
     */
    public static Date StrToTime(String Str) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setLenient(false);
        Date ddate = null;
        try {
            ddate = sdf.parse(Str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ddate;
    }

    public static String getHourTime() {
        Date today=new Date();
        SimpleDateFormat f=new SimpleDateFormat("HH:mm:ss");
        String time = f.format(today);
        return time;
    }

    /**
     * 获取某个日期所在月份每天的日期
     * @param date
     * @return
     */
    public static List getDayListOfMonth(Date date) {
        List<String> list = new ArrayList();
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);
        int year = aCalendar.get(Calendar.YEAR);//年份
        int month = aCalendar.get(Calendar.MONTH) + 1;//月份
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        for (int i = 1; i <= day; i++) {
            String monthStr = "";
            String dayStr = "";
            if(month<10){
                monthStr = "0" + month;
            }else{
                monthStr = String.valueOf(month);
            }
            if(i<10){
                dayStr = "0" + i;
            }else{
                dayStr = String.valueOf(i);
            }

            String aDate = String.valueOf(year)+"-"+monthStr+"-"+dayStr;
            list.add(aDate);
        }
        return list;
    }

    /**
     * 校验工作日，周末，节假日
     * @param date 需要校验的日期
     * @return String 0-工作日，1-周末，2-节假日
     */
    public static String checkHoliday(Date date){
        SimpleDateFormat f=new SimpleDateFormat("yyyyMMdd");
        String dateStr = f.format(date);
        Map<String,Object> m = new HashMap<>();
        m.put("date",dateStr);
        String str = HttpClientUtil.sendPost("http://api.goseek.cn/Tools/holiday", m);
        JsonMapper json = new JsonMapper();
        Map<String,Object> map = json.fromJson(str, Map.class);
        return map.get("data").toString();
    }

    public static void main(String[] args) {
        checkHoliday(DateUtil.StrToDate("2018-04-22"));
        checkHoliday(new Date());
    }
}
