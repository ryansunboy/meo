package com.asiainfo.meo.meo.notification.notification.profile.model;

import java.sql.Timestamp;

public class NotifySendParam implements java.io.Serializable
{
    
    /**
     * @Fields serialVersionUID : (用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;
    
    private Long              id;
    
    private Long              sendId;
    
    private Long              paramId;
    
    private String            paramValue;
    
    private Timestamp         doneTime;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getSendId()
    {
        return sendId;
    }

    public void setSendId(Long sendId)
    {
        this.sendId = sendId;
    }

    public Long getParamId()
    {
        return paramId;
    }

    public void setParamId(Long paramId)
    {
        this.paramId = paramId;
    }

    public String getParamValue()
    {
        return paramValue;
    }

    public void setParamValue(String paramValue)
    {
        this.paramValue = paramValue;
    }

    public Timestamp getDoneTime()
    {
        return doneTime;
    }

    public void setDoneTime(Timestamp doneTime)
    {
        this.doneTime = doneTime;
    }
    
}