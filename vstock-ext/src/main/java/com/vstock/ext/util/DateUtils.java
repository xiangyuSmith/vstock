package com.vstock.ext.util;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by administor on 2016/5/11.
 */
public class DateUtils {
    /**
     *
     * addDaysToDate:(增加几天到当前的Date). <br/>
     *
     * @param date
     * @param numDays
     * @return
     * @since JDK 1.7
     */
    public static Date addDaysToDate(Date date, int numDays) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, numDays);
        return c.getTime();
    }

    /**
     *
     * addDaysToDate:(增加几天到当前的Date). <br/>
     *
     * @paramdate
     * @param numDays
     * @return
     * @since JDK 1.7
     */
    public static Date addDaysToStringDate(String sDate, int numDays,
                                           String formate) {
        if (sDate == null) {
            return null;
        }
        Date date = getDate(sDate, formate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, numDays);
        return c.getTime();
    }

    /**
     *
     * addHoursToDate:(增加几个小时到当前Date). <br/>
     *
     * @param date
     * @param numHours
     * @return
     * @since JDK 1.7
     */
    public static Date addHoursToDate(Date date, int numHours) {
        if (date == null) {
            return null;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, numHours);

        return c.getTime();
    }

    /**
     *
     * addMinutesToDate:(增加几分钟到当前Date). <br/>
     *
     * @param *date
     * @param *numHours
     * @return
     * @since JDK 1.7
     */
    public static Date addMinutesToDate(Date date, int numMins) {
        if (date == null) {
            return null;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, numMins);

        return c.getTime();
    }

    /**
     *
     * addMonthsToDate:(增加几个月到当前Date). <br/>
     *
     * @param *date
     * @param *numHours
     * @return
     * @since JDK 1.7
     */
    public static Date addMonthsToDate(Date date, int numMonths) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, numMonths);
        return c.getTime();
    }

    /**
     *
     * addYearsToDate:(增加几年到当前Date). <br/>
     *
     * @param date
     * @param *numHours
     * @return
     * @since JDK 1.7
     */
    public static Date addYearsToDate(Date date, int numYears) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, numYears);
        return c.getTime();
    }

    /**
     *
     * compareDateDown:比较两个日期返回小的. <br/>
     *
     * @author:MINGYONGZHANG Date: 2016年3月1日 下午2:57:30
     * @return
     * @since JDK 1.7
     */
    public static String compareDateDown(String oneDate, String anotherDate) {
        String[] compareDates = { oneDate, anotherDate };
        Arrays.sort(compareDates);
        return compareDates[0];
    }

    /**
     *
     * compareDateUp:比较两个日期返回大的. <br/>
     *
     * @author:MINGYONGZHANG Date: 2016年3月1日 下午2:58:03
     * @return
     * @since JDK 1.7
     */
    public static String compareDateUp(String oneDate, String anotherDate) {
        String[] compareDates = { oneDate, anotherDate };
        Arrays.sort(compareDates);
        return compareDates[1];
    }

    /**
     *
     * dateToString:(按照默认模式将时间转化为String. <br/>
     *
     * @param *date
     * @param *numHours
     * @return
     * @since JDK 1.7
     */
    public static String dateToString(Date date) {
        return new SimpleDateFormat(DEFAULT_FORMAT).format(date);
    }

    /**
     *
     * dateToString:(按照传入的模式将时间转化为String. <br/>
     *
     * @param *date
     * @param *numHours
     * @return
     * @since JDK 1.7
     */
    public static String dateToString(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     *
     * getCurrentAllDays:(获得两个时间的天数). <br/>
     *
     * @author:Shipeng Tang Date: 2015年12月31日 上午9:53:28
     * @param dateFrom
     * @param dateTo
     * @param isEgnoreTime
     *            是否忽略时间
     * @return
     * @since JDK 1.7
     */
    public static final long getCurrentAllDays(Date dateFrom, Date dateTo,
                                               boolean isEgnoreTime) {
        long days = 0l;
        if (isEgnoreTime) { // 將時間置0
            dateFrom = getDate(dateFrom, 0, 0, 0);
            dateTo = getDate(dateTo, 0, 0, 0);
        }
        if (dateFrom.before(dateTo)) {
            days = (dateTo.getTime() - dateFrom.getTime()) / 1000 / 60 / 60
                    / 24;
        } else {
            days = (dateFrom.getTime() - dateTo.getTime()) / 1000 / 60 / 60
                    / 24;
        }
        return days;

    }

    /**
     *
     * getCurrentTimeAs14String:(获取当前格式化String时间). <br/>
     *
     * @return
     * @since JDK 1.7
     */
    public static String getCurrentTimeAs14String() {
        return new SimpleDateFormat(FORMAT_14).format(new Date());
    }

    /**
     *
     * getCurrentTimeAs14String:(获取当前格式化String时间). <br/>
     *
     * @return
     * @since JDK 1.7
     */
    public static String getCurrentTimeAsString() {
        return new SimpleDateFormat(DEFAULT_FORMAT).format(new Date());
    }

    /**
     *
     * getDate:(获取当前指定时间的Date). <br/>
     *
     * @author:Shipeng Tang Date: 2015年12月31日 上午9:56:44
     * @param date
     * @param hour
     * @param min
     * @param sec
     * @return
     * @since JDK 1.7
     */
    public static Date getDate(Date date, int hour, int min, int sec) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, min);
        cal.set(Calendar.SECOND, sec);
        return cal.getTime();
    }

    /**
     *
     * getDate:(获取指定年月日的时间). <br/>
     *
     * @param year
     * @param month
     * @param day
     * @return
     * @since JDK 1.7
     */
    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, 0, 0, 0);
        return cal.getTime();
    }

    /**
     *
     * getDate:(将字符串转化为date). <br/>
     *
     * @param dateStr
     * @param formate
     * @return
     * @since JDK 1.7
     */
    public static Date getDate(String dateStr, String formate) {
        SimpleDateFormat sdf = new SimpleDateFormat(formate);
        Date date = new Date();
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     *
     * getDateHaoMiao:(). <br/>
     *
     * @param dateStr
     * @param formate
     * @return
     * @since JDK 1.7
     */
    public static long getDateHaoMiao(String dateStr, String formate) {
        SimpleDateFormat sdf = new SimpleDateFormat(formate);
        long mseldate = 0;
        try {
            mseldate = sdf.parse(dateStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mseldate;
    }

    public static long getDateMesl(Date date) {
        long mselDate = 0;
        mselDate = date.getTime();
        return mselDate;
    }

    public static final int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE);

    }

    public static long getDays(String s1, String s2, String format,
                               boolean isEqnore) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        // df.setTimeZone(TimeZone.getTimeZone("GMT-8:00"));
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = df.parse(s1);
            d2 = df.parse(s2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getCurrentAllDays(d1, d2, isEqnore);
    }

    public static final Date getFirstDayOfTheYear(Date date) {
        Calendar cal = Calendar.getInstance();
        int year = getYear(date);
        cal.set(year, 0, 1);
        return cal.getTime();
    }

    public static final int getLastDayOfTheMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static final int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);

    }

    public static final int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);

    }

    public static int hqxcts(String s1, String s2, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = df.parse(s1);
            d2 = df.parse(s2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        int betweenDays = c2.get(Calendar.DAY_OF_YEAR)
                - c1.get(Calendar.DAY_OF_YEAR);
        for (int i = 0; i < betweenYears; i++) {
            c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
            betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
        }
        return betweenDays;
    }

    public static boolean isEndOfTheMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (cal.get(Calendar.DATE) == maxDay) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEndOfTheYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if ((cal.get(Calendar.MONTH) + 1 == 12)
                && (cal.get(Calendar.DATE) == 31)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isLeap(Date d) {
        int y = getYear(d);
        return ((y % 4) == 0 && (y % 100) != 0) || (y % 400 == 0);
    }

    public static final boolean isStartOfTheMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        if (cal.get(Calendar.DATE) == 1) {
            return true;
        }

        return false;
    }

    public static boolean isToDay(String dateStr, String formate) {
        SimpleDateFormat sdf = new SimpleDateFormat(formate);
        String todayStr = sdf.format(new Date());
        if (todayStr != null && !todayStr.equals("") && dateStr != null
                && !dateStr.equals("") && dateStr.equals(todayStr)) {
            return true;
        } else {
            return false;
        }
    }

    public static String getNowYearJidu(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar now = Calendar.getInstance();
        //年份
        String year = String.valueOf( now.get(Calendar.YEAR));
        //月份
        int month = now.get(Calendar.MONTH) + 1;
        //季度
        String jidu = "";
        if(month == 1 || month ==2 || month ==3){
            jidu = "Q1";
        }
        else if(month == 4 || month ==5 || month ==6){
            jidu = "Q2";
        }
        else if(month == 7 || month ==8 || month ==9){
            jidu = "Q3";
        }
        else if(month == 10 || month ==11 || month ==12){
            jidu = "Q4";
        }
        return year+jidu;
    }

    public static void main(String args[]) throws ParseException {
        String systime = DateUtils.getCurrentTimeAsString();
        systime = DateUtils.dateToString(
                DateUtils.parseToDateTime(systime, "yyyy-MM-dd"), "yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Date date = calendar.getTime();
        System.out.println(DateUtils.dateToString(date, "yyyyMMdd000000"));
        calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.add(Calendar.DATE, 6);
        Date date1 = calendar.getTime();
        System.out.println(DateUtils.dateToString(date1, "yyyyMMdd000000"));
        calendar.add(Calendar.DATE, 7);
        Date date2 = calendar.getTime();
        System.out.println(DateUtils.dateToString(date2, "yyyyMMdd000000"));
        calendar.add(Calendar.DATE, 7);
        Date date3 = calendar.getTime();
        System.out.println(DateUtils.dateToString(date3, "yyyyMMdd000000"));
        calendar.add(Calendar.DATE, 7);
        Date date4 = calendar.getTime();
        System.out.println(DateUtils.dateToString(date4, "yyyyMMdd000000"));
        System.out.println(systime);
        System.out.println("****************************************************************************************************************************");
        System.out.println(getNowYearJidu());

        // System.out.println(parseTo14DateTime(("2010-12-30"), "yyyy-MM-dd"));
    }

    public static String parseStrDateTimeToString(String strDate,
                                                  String pattern, String format) {
        return dateToString(parseToDateTime(strDate, pattern), format);
    }

    public static Date parseToDateTime(String strDate, String pattern) {
        try {
            return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(
                    pattern).parse(strDate);
        } catch (ParseException e) {
            // e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间减去30天
     *
     * @Date 2016.06.30 下午 17:20:08
     * @param dates
     * @return
     */
    public static Date wantToLose(Date dates,int day){
        SimpleDateFormat dft = new SimpleDateFormat(DEFAULT_FORMAT);
        Calendar date = Calendar.getInstance();
        date.setTime(dates);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - day);
        try {
            dates = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dates;
    }

    public static String dateTime(String dateTime){
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = dateFormater.parse(dateTime);
        }catch (Exception ex){
            System.out.print(ex.getMessage());
        }
        return dateFormater.format(date);
    }

    private final static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final static String FORMAT_14 = "yyyyMMddHHmmss";

    /**
     * 日期格式字符串转换成时间戳
     * @param date_str 字符串日期
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str,String format){
        try {
            if("".equals(date_str)){
                return "";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime()/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
