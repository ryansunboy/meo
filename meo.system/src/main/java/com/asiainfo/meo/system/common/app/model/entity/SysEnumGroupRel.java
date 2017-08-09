package com.asiainfo.meo.system.common.app.model.entity;

import java.sql.Timestamp;

public class SysEnumGroupRel implements java.io.Serializable
{
    
    /**
     * @Fields serialVersionUID : (用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;
    
    private Long              relId;
    
    private Long              groupId;
    
    private Long              enumId;
    
    private Integer           sts;
    
    private Timestamp         createDate;
    
    private Timestamp         modifyDate;
    
    private Long              operatorId;
    
    public static final int   STS_VALID        = 1;
    
    public static final int   STS_INVALID      = 2;
    
    public long getRelId()
    {
        return this.relId;
    }
    
    public void setRelId(long relId)
    {
        this.relId = relId;
    }
    
    public long getGroupId()
    {
        return this.groupId;
    }
    
    public void setGroupId(long groupId)
    {
        this.groupId = groupId;
    }
    
    public long getEnumId()
    {
        return this.enumId;
    }
    
    public void setEnumId(long enumId)
    {
        this.enumId = enumId;
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
    
    public long getOperatorId()
    {
        return this.operatorId;
    }
    
    public void setOperatorId(long operatorId)
    {
        this.operatorId = operatorId;
    }
    
}