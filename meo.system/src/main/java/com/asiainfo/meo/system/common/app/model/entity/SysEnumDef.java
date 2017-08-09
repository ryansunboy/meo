package com.asiainfo.meo.system.common.app.model.entity;

// Generated 2015-3-25 16:30:00 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

/**
 * SysEnumDef generated by hbm2java
 */

public class SysEnumDef implements java.io.Serializable
{
    
    private static final long   serialVersionUID    = 3340583786690707486L;
    
    public static final Integer STS_ENUM_ACTIVATE   = 1;
    
    public static final Integer STS_ENUM_DEACTIVATE = 2;
    
    public static final String  CAMPAGIN_TYPE       = "5";
    
    public static final String  CHARGE_UNIT         = "19";
    
    public static final String  PARTNER_TYPE        = "17";
    
    private long                enumId;
    
    private String              enumCode;
    
    private String              enumType;
    
    private String              enumName;
    
    private String              dispName;
    
    private Integer             sts;
    
    private Timestamp           createDate;
    
    private Timestamp           modifyDate;
    
    private Long                operatorId;
    
    public long getEnumId()
    {
        return enumId;
    }
    
    public void setEnumId(long enumId)
    {
        this.enumId = enumId;
    }
    
    public String getEnumCode()
    {
        return enumCode;
    }
    
    public void setEnumCode(String enumCode)
    {
        this.enumCode = enumCode;
    }
    
    public String getEnumType()
    {
        return enumType;
    }
    
    public void setEnumType(String enumType)
    {
        this.enumType = enumType;
    }
    
    public String getEnumName()
    {
        return enumName;
    }
    
    public void setEnumName(String enumName)
    {
        this.enumName = enumName;
    }
    
    public String getDispName()
    {
        return dispName;
    }
    
    public void setDispName(String dispName)
    {
        this.dispName = dispName;
    }
    
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
