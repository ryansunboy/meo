package com.asiainfo.meo.system.authority.app.model.entity;

// Generated 2015-4-3 16:25:17 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

/**
 * SysGroup generated by hbm2java
 */
public class SysGroup implements java.io.Serializable
{
    
    private long      groupId;
    
    private Long      parentId;
    
    private String    name;
    
    private Integer   sts;
    
    private Timestamp createDate;
    
    private Timestamp modifyDate;
    
    public long getGroupId()
    {
        return this.groupId;
    }
    
    public void setGroupId(long groupId)
    {
        this.groupId = groupId;
    }
    
    public Long getParentId()
    {
        return this.parentId;
    }
    
    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public Integer getSts()
    {
        return this.sts;
    }
    
    public void setSts(Integer sts)
    {
        this.sts = sts;
    }
    
    public Timestamp getCreateDate()
    {
        return this.createDate;
    }
    
    public void setCreateDate(Timestamp createDate)
    {
        this.createDate = createDate;
    }
    
    public Timestamp getModifyDate()
    {
        return this.modifyDate;
    }
    
    public void setModifyDate(Timestamp modifyDate)
    {
        this.modifyDate = modifyDate;
    }
    
}
