package com.asiainfo.meo.system.authority.app.model.entity;

// Generated 2015-4-3 16:25:17 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

/**
 * SysRole generated by hbm2java
 */
public class SysRole implements java.io.Serializable
{
    
    /**                                                                           
      * @Fields serialVersionUID : (用一句话描述这个变量表示什么)                                                                                                                                                                                                                                  
      */
    private static final long serialVersionUID = 1L;

    private long      roleId;
    
    private String    roleName;
    
    private Integer   sts;
    
    private Timestamp createDate;
    
    private Timestamp modifyDate;
    
    public static final Integer STS_VALID = 1;
    
    public  static final Integer STS_INVALID = 2;
    
    public long getRoleId()
    {
        return this.roleId;
    }
    
    public void setRoleId(long roleId)
    {
        this.roleId = roleId;
    }
    
    public String getRoleName()
    {
        return this.roleName;
    }
    
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
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
