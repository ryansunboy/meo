package com.asiainfo.meo.common.core.sequence.serial.model.entity;

import java.sql.Timestamp;

public class SysSequence
{
    private String sequenceName;
    private long startAt;
    private long lastVal;
    private int incrementBy;
    private int cache;
    private Timestamp updateDate;
    
    public String getSequenceName()
    {
        return sequenceName;
    }
    public void setSequenceName(String sequenceName)
    {
        this.sequenceName = sequenceName;
    }
    public long getStartAt()
    {
        return startAt;
    }
    public void setStartAt(long startAt)
    {
        this.startAt = startAt;
    }
    public int getCache()
    {
        return cache;
    }
    public void setCache(int cache)
    {
        this.cache = cache;
    }
    public Timestamp getUpdateDate()
    {
        return updateDate;
    }
    public void setUpdateDate(Timestamp updateDate)
    {
        this.updateDate = updateDate;
    }
    public long getLastVal()
    {
        return lastVal;
    }
    public void setLastVal(long lastVal)
    {
        this.lastVal = lastVal;
    }
    public int getIncrementBy()
    {
        return incrementBy;
    }
    public void setIncrementBy(int incrementBy)
    {
        this.incrementBy = incrementBy;
    }
    
}
