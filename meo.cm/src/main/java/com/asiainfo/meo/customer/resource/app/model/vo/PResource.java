package com.asiainfo.meo.customer.resource.app.model.vo;

// Generated 2015-3-2 14:55:03 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

public class PResource
{
    
    public static final String RESOURCE_STATUS = "27";
    public static final String RESOURCE_TYPE = "6";
    
    private long      resourceId;
    
    private String    resourceName;
    
    private Integer   resourceStsId;
    
    private String    resourceSts;
    
    private Timestamp cycleBeginDate;
    
    private Timestamp cycleEndDate;
    
    private String    description;

    public long getResourceId()
    {
        return resourceId;
    }

    public void setResourceId(long resourceId)
    {
        this.resourceId = resourceId;
    }

    public String getResourceName()
    {
        return resourceName;
    }

    public void setResourceName(String resourceName)
    {
        this.resourceName = resourceName;
    }

    public Integer getResourceStsId()
    {
        return resourceStsId;
    }

    public void setResourceStsId(Integer resourceStsId)
    {
        this.resourceStsId = resourceStsId;
    }

    public String getResourceSts()
    {
        return resourceSts;
    }

    public void setResourceSts(String resourceSts)
    {
        this.resourceSts = resourceSts;
    }

    public Timestamp getCycleBeginDate()
    {
        return cycleBeginDate;
    }

    public void setCycleBeginDate(Timestamp cycleBeginDate)
    {
        this.cycleBeginDate = cycleBeginDate;
    }

    public Timestamp getCycleEndDate()
    {
        return cycleEndDate;
    }

    public void setCycleEndDate(Timestamp cycleEndDate)
    {
        this.cycleEndDate = cycleEndDate;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
    
    
    
}
