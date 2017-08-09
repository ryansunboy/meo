package com.asiainfo.meo.system.common.app.model.entity;

import java.sql.Timestamp;

public class SysEnumGroupDef implements java.io.Serializable
{
    
    /**
     * @Fields serialVersionUID : (用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;
    
    private Long              groupId;
    
    private String            groupCode;
    
    private String            groupName;
    
    private Integer           groupType;
    
    // private Long enumId;
    
    private Integer           sts;
    
    private Timestamp         createDate;
    
    private Timestamp         modifyDate;
    
    private Long              operatorId;
    
    public static final int   STS_VALID        = 1;
    
    public static final int   STS_INVALID      = 2;
    
    public static final int   CAMPAIGN_TYPE    = 1;
    
    public Long getGroupId()
    {
        return groupId;
    }
    
    public void setGroupId(Long groupId)
    {
        this.groupId = groupId;
    }
    
    public String getGroupCode()
    {
        return groupCode;
    }
    
    public void setGroupCode(String groupCode)
    {
        this.groupCode = groupCode;
    }
    
    public String getGroupName()
    {
        return groupName;
    }
    
    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }
    
    public Integer getGroupType()
    {
        return groupType;
    }
    
    public void setGroupType(Integer groupType)
    {
        this.groupType = groupType;
    }
    
    // public Long getEnumId()
    // {
    // return enumId;
    // }
    //
    // public void setEnumId(Long enumId)
    // {
    // this.enumId = enumId;
    // }
    
    public Integer getSts()
    {
        return sts;
    }
    
    public void setSts(Integer sts)
    {
        this.sts = sts;
    }
    
    public Timestamp getCreateDate()
    {
        return createDate;
    }
    
    public void setCreateDate(Timestamp createDate)
    {
        this.createDate = createDate;
    }
    
    public Timestamp getModifyDate()
    {
        return modifyDate;
    }
    
    public void setModifyDate(Timestamp modifyDate)
    {
        this.modifyDate = modifyDate;
    }
    
    public Long getOperatorId()
    {
        return operatorId;
    }
    
    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
    }
    
}