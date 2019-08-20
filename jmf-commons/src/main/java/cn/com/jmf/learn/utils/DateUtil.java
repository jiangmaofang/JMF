package cn.com.jmf.learn.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    public static final String COMMON_DATE_FORMAT = "yyyy-MM-dd";

    public static final String COMMON_DATE_FORMAT_CHINA_YM = "yyyy年MM月";

    public static final String COMMON_DATE_FORMAT_YM = "yyyy-MM";

    public static String simpleFormat(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static Date simpleParse(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(date);
        } catch (Exception e) {

        }

        return null;
    }
    
    public static Date simpleParsePatern(String date,String patern) {
        SimpleDateFormat format = new SimpleDateFormat(patern);
        try {
            return format.parse(date);
        } catch (Exception e) {

        }

        return null;
    }

    public static String fullFormat(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date);
    }

    /**
     * Get a diff between two dates
     *
     * @param date1    the oldest date
     * @param date2    the newest date
     * @param timeUnit the unit in which you want the diff
     * @return the diff value, in the provided unit
     */
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    /**
     * 时间偏移处理
     *
     * @author ferren
     */
    public static Date dayCalculate(Date date, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, num);
        return cal.getTime();
    }

    /**
     * 格式化日期,让当前日期只精确到天,或其他用途
     *
     * @param date 待格式化的时间
     * @return 格式化的时间
     * @date 2017年12月17日
     * @format format 待格式化的时间格式
     */
    public static Date formatDate(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(format);
        String formatStr = formatDate.format(date);
        try {
            date = formatDate.parse(formatStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 格式化日期,让当前日期只精确到天,或其他用途
     *
     * @param date 待格式化的时间
     * @return 格式化的时间
     * @date 2017年12月17日
     * @format format 待格式化的时间格式
     */
    public static String formatDateToStr(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(format);
        String formatStr = formatDate.format(date);
        return formatStr;
    }

    /**
     * 格式化日期,让当前日期只精确到天,或其他用途
     *
     * @param date 待格式化的时间
     * @return 格式化的时间
     * @date 2017年12月17日
     * @format format 待格式化的时间格式
     */
    public static Date formatStrToDate(String dateStr, String format) {
        SimpleDateFormat formatDate = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = formatDate.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取当月第一天
     *
     * @return
     */
    public static Date getMonthStartDate() {
        //获取当前月第一天：
        Calendar calstr = Calendar.getInstance();
        //本月
        calstr.add(Calendar.MONTH, 0);
        //设置为1号为本月第一天
        calstr.set(Calendar.DAY_OF_MONTH, 1);
        Date time = calstr.getTime();
        Date date = formatDate(time, COMMON_DATE_FORMAT);
        return date;
    }

    /**
     * 获取当月第一天
     *
     * @return
     */
    public static Date getMonthEndDate() {
        //获取当前月最后一天
        Calendar calast = Calendar.getInstance();
        //设置当月为最后一天
        calast.set(Calendar.DAY_OF_MONTH, calast.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date time = calast.getTime();
        Date date = formatDate(time, COMMON_DATE_FORMAT);
        return date;
    }

    // 获得某天最大时间 2018-03-20 23:59:59
    public static Date getEndOfDay(Date date) {
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(date);
        calendarEnd.set(Calendar.HOUR_OF_DAY, 23);
        calendarEnd.set(Calendar.MINUTE, 59);
        calendarEnd.set(Calendar.SECOND, 59);

        //防止mysql自动加一秒,毫秒设为0
        calendarEnd.set(Calendar.MILLISECOND, 0);
        return calendarEnd.getTime();

    }
}
