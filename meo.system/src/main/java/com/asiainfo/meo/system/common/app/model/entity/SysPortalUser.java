package com.asiainfo.meo.system.common.app.model.entity;

// Generated 2015-4-9 10:57:23 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

/**
 * SysPortalUser generated by hbm2java
 */
public class SysPortalUser implements java.io.Serializable
{
    
    /**
     * @Fields serialVersionUID : (用一句话描述这个变量表示什么)
     */
    private static final long   serialVersionUID    = 1L;
    
    private long                userId;
    
    private Long                objectId;
    
    private Integer             objectType;
    
    private Timestamp           modifyTime;
    
    private Integer             sts;
    
    private Timestamp           createDate;
    
    private Long                operatorId;
    
    public static final Integer STS_ACTIVATE        = 1;
    
    public static final Integer STS_DEACTIVATE      = 2;
    
    public static final Integer OBJECT_TYPE_PARTNER = 1;
    
    public static final Integer OBJECT_TYPE_SYSTEM  = 2;
    
    // public static final Integer OBJECT_TYPE_SALTER = 2;
    //
    // public static final Integer OBJECT_TYPE_ADMIN = 3;
    //
    // public static final Integer OBJECT_TYPE_CAMPAIGN_DESINER = 4;
    //
    // public static final Integer OBJECT_TYPE_MARKETING = 5;
    
    public static final String  SEQ_SYS_PORTAL_USER = "SEQ_SYS_PORTAL_USER_USER_ID";
    
    public long getUserId()
    {
        return this.userId;
    }
    
    public void setUserId(long userId)
    {
        this.userId = userId;
    }
    
    public Long getObjectId()
    {
        return this.objectId;
    }
    
    public void setObjectId(Long objectId)
    {
        this.objectId = objectId;
    }
    
    public Integer getObjectType()
    {
        return this.objectType;
    }
    
    public void setObjectType(Integer objectType)
    {
        this.objectType = objectType;
    }
    
    public Timestamp getModifyTime()
    {
        return this.modifyTime;
    }
    
    public void setModifyTime(Timestamp modifyTime)
    {
        this.modifyTime = modifyTime;
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
    
    public Long getOperatorId()
    {
        return this.operatorId;
    }
    
    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
    }
    
}