package com.asiainfo.meo.web.notification.model.vo;

import java.io.Serializable;

public class UITimeVO implements Serializable
{

    /**                                                                           
      * @Fields serialVersionUID : (用一句话描述这个变量表示什么)                                                                                                                                                                                                                                  
      */
    private static final long serialVersionUID = 7248330669883906808L;
    
    private Integer year;
    
    private Integer month;
    
    private Integer day;    
    
    private Integer week;
    
    private Integer hour;
    
    private Integer minute;
    
    private Integer second;

    public Integer getYear()
    {
        return year;
    }

    public void setYear(Integer year)
    {
        this.year = year;
    }

    public Integer getMonth()
    {
        return month;
    }

    public void setMonth(Integer month)
    {
        this.month = month;
    }

    public Integer getDay()
    {
        return day;
    }

    public void setDay(Integer day)
    {
        this.day = day;
    }

    public Integer getWeek()
    {
        return week;
    }

    public void setWeek(Integer week)
    {
        this.week = week;
    }

    public Integer getHour()
    {
        return hour;
    }

    public void setHour(Integer hour)
    {
        this.hour = hour;
    }

    public Integer getMinute()
    {
        return minute;
    }

    public void setMinute(Integer minute)
    {
        this.minute = minute;
    }

    public Integer getSecond()
    {
        return second;
    }

    public void setSecond(Integer second)
    {
        this.second = second;
    }
    
}
