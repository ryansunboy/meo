package com.asiainfo.meo.common.core.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.asiainfo.meo.common.core.exception.MeoException;

public final class DateTimeUtil
{
    public static final String DEFAULT_DATE_TIME_PATTERN = "yyyyMMddHHmmss";
    
    public static final String DEFAULT_DATE_PATTERN = "yyyyMMdd";
    
    public static final SimpleDateFormat DEFAULT_DATE_TIME_FORMAT = new SimpleDateFormat(DEFAULT_DATE_TIME_PATTERN);
    
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
    
    public static final DateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    public static final DateFormat SDF2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static final String DAY_TIME_START = " 00:00:00";
    
    public static final String DAY_TIME_END = " 23:59:59";
    
    public static final int SECOND = 1000;
    
    public static final int MINUTE = 60 * SECOND;
    
    public static final int HOUR = 60 * MINUTE;
    
    public static final int DAY = 24 * HOUR;
    
    private DateTimeUtil()
    {
        
    };
    
    public static Timestamp getNow()
    {
        return new Timestamp(getCurrentTimeMillis());
    }
    
    public static long getCurrentTimeMillis()
    {
        return System.currentTimeMillis();
    }
    
    public static synchronized Date getDateTime(String dateTime) throws ParseException
    {
        return DEFAULT_DATE_TIME_FORMAT.parse(dateTime);
    }
    

    public static synchronized Date getDate(String date) throws ParseException
    {
        return DEFAULT_DATE_FORMAT.parse(date); 
    }
    
    /**
     * 
      * @Description: parsing the string to date with specified format
      * @modifyReason: 
      * @author Thanapol
      * @param date
      * @param format
      * @return
      * @throws ParseException
     */
    public static synchronized Date getDate2(final String date, final String format) throws ParseException
    {
        final DateFormat SDF = new SimpleDateFormat(format);
    	return SDF.parse(date);
    }
    
    public static Timestamp getDateTimeTimestamp(String dateTime) throws ParseException
    {
        return new Timestamp(getDate(dateTime).getTime());
    }
    
    public static Timestamp getDateTimestamp(String date) throws ParseException
    {
        return new Timestamp(getDate(date).getTime());
    }
    
    public static Calendar toCalendar(Date date)
    {
      Calendar c = Calendar.getInstance();
      c.setTime(date);
      return c;
    }
    
    public static long intervalDay(Date date1,Date date2)
    {
        return intervalDay(date1.getTime(), date2.getTime());
    }
    
    
    public static long intervalDay(Timestamp t1,Timestamp t2)
    {
        if(t1 == null || t2 == null)
        {
            throw new IllegalArgumentException("The date must not be null");
        }
        return intervalDay(t1.getTime(), t2.getTime());
    }
    
    public static long intervalDay(Calendar cal1 , Calendar cal2)
    {
        if ((cal1 == null) || (cal2 == null)) 
        {
          throw new IllegalArgumentException("The date must not be null");
        }
        return intervalDay(cal1.getTimeInMillis(),cal2.getTimeInMillis());
    }
    
    public static long intervalDay(long milllis1,long millis2)
    {
        long intervalTime = milllis1 - millis2;
        return Math.abs(intervalTime) / DAY;
    }
    
    
    public static boolean isSameDay(Calendar cal1, Calendar cal2)
    {
      if ((cal1 == null) || (cal2 == null)) 
      {
        throw new IllegalArgumentException("The date must not be null");
      }
      return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)) && (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) && (cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }
    
    
    public static boolean isSameDay(Date date1, Date date2)
    {
      if ((date1 == null) || (date2 == null))
      {
        throw new IllegalArgumentException("The date must not be null");
      }
      Calendar cal1 = Calendar.getInstance();
      cal1.setTime(date1);
    
      Calendar cal2 = Calendar.getInstance();
      cal2.setTime(date2);
     
      return isSameDay(cal1, cal2);
    }
    /**
     * 
      * @Description: 增加日期的天数。失败返回null。
      * @Description: add days on date
      * @modifyReason: 
      * @author liuyang
      * @param date
      * @param dayAmount
      * @return
     */
    public static Date addDay(Date date,int dayAmount){
        return addInteger(date,Calendar.DATE,dayAmount);
    }

    private static Date addInteger(Date date, int dateType, int amount)
    {
        if(ValidateUtil.isNotEmpty(date)){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType , amount);
            return calendar.getTime();
        }
        return null;
    }
    
    /**
     * 
      * @Description: 获取一天的开始时间
      * @Description: get the begin date of day
      * @modifyReason: 
      * @author liuyang
      * @param date
      * @return
     */
    public static Date getBeginDay(Date date){
        if(ValidateUtil.isNotEmpty(date)){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            return calendar.getTime();
        }
        return null;   
    }
    /**
     * 
      * @Description: 获取timestamp类型的开始时间
      * @Description: get timestamp type begin date of day
      * @modifyReason: 
      * @author liuyang
      * @param date
      * @return
     */
    public static Timestamp getBeginTimestampDay(Date date){
        Date oriDate =getBeginDay(date);
        
        return new Timestamp(oriDate.getTime());   
    }
    
    /**
     * 
      * @Description: 获取timestamp类型的结束时间
      * @Description:  get timestamp type end date of day
      * @modifyReason: 
      * @author liuyang
      * @param date
      * @return
     */
    public static Timestamp getEndTimestampDay(Date date){
        Date oriDate =getEndDay(date);
        
        return new Timestamp(oriDate.getTime());   
    }
    
    /**
     * 
      * @Description: 获得一天的结束时间
      * @Description: get the end date of day
      * @modifyReason: 
      * @author liuyang
      * @param date
      * @return
     */
    public static Date getEndDay(Date date){
        if(ValidateUtil.isNotEmpty(date)){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            return calendar.getTime();
        }
        return null; 
    }
    
  
    /**
     * 
      * @Description: 获得一天的结束时间 (timestamp格式)
      * @Description: get the end date of day
      * @modifyReason: 
      * @author zhengzy
      * @param time
      * @return
     */
    public static Timestamp getEndDayTimestamp(long time)
    {
        Timestamp ts = new Timestamp(time);
        String endTime = SDF2.format(Timestamp.valueOf(SDF.format(ts) + DAY_TIME_END));
        return Timestamp.valueOf(endTime);
   }
    
    /**
     * 
      * @Description: 获得一天的开始时间 (timestamp格式)
      * @Description: get the begin date of day
      * @modifyReason: 
      * @author zhengzy
      * @param time
      * @return
     */
    public static Timestamp getBeginDayTimestamp(long time)
    {
        Timestamp ts = new Timestamp(time);
        String newTime = SDF2.format(Timestamp.valueOf(SDF.format(ts) + DAY_TIME_START));
        return Timestamp.valueOf(newTime);
    }
    
    /**
     * 
      * @Description: 获得明天的开始时间 (timestamp格式)
      * @Description: get tomorrow begin datetime
      * @modifyReason: 
      * @author zhengzy
      * @param time
      * @return
     */
    public static Timestamp getNextDayStartTime(){
        Timestamp ts = new Timestamp(new Date().getTime() + DAY);
        String endTime = SDF2.format(Timestamp.valueOf(SDF.format(ts) + DAY_TIME_START));
        return Timestamp.valueOf(endTime);
    }
    
    /**
     * 
      * @Description: 获得前一天的結束时间 (timestamp格式)
      * @Description: get yesterday end datetime
      * @modifyReason: 
      * @author zhengzy
      * @param time
      * @return
     */
    public static Timestamp getYesterdayEndDateTime(long time)
    {
        Timestamp ts = new Timestamp(time - DAY);
        String endTime = SDF2.format(Timestamp.valueOf(SDF.format(ts) + DAY_TIME_END));
        return Timestamp.valueOf(endTime);
    }
    
    /**
     * 
      * @Description: 获取以今天为准的过去的n天的日期
      * @Description: 
      * @modifyReason: 
      * @author zhoujj
      * @param n
      * @return
     */
    public static List<Date> getBeforeNDays(Integer n)
    {
        if (n == null)
        {
            return null;
        }
        List<Date> dateList = new ArrayList<Date>();
        for (int i = 1; i <= n; i++)
        {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -i);
            String yesterday = SDF.format(cal.getTime());
            try
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d = sdf.parse(yesterday + DAY_TIME_START);
                dateList.add(d);
            }
            catch(ParseException e)
            {
                System.out.println(e);
                throw new MeoException("date parse error");
                
            }
        }
        return dateList;
    }
   
}
