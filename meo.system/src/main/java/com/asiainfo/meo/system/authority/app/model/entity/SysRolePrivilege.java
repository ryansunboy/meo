package com.asiainfo.meo.system.authority.app.model.entity;

// Generated 2015-5-11 19:31:42 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

/**
 * SysRolePrivilege generated by hbm2java
 */
public class SysRolePrivilege implements java.io.Serializable
{
    
    /**                                                                           
      * @Fields serialVersionUID : (用一句话描述这个变量表示什么)                                                                                                                                                                                                                                  
      */
    private static final long serialVersionUID = 1L;

    private long      id;
    
    private Long      roleId;
    
    private Long      privId;
    
    private Integer   sts;
    
    private Timestamp createDate;
    
    private Timestamp modifyDate;
    
    public static final Integer STS_VALID = 1;
    
    public  static final Integer STS_INVALID = 2;
    
    public long getId()
    {
        return this.id;
    }
    
    public void setId(long id)
    {
        this.id = id;
    }
    
    public Long getRoleId()
    {
        return this.roleId;
    }
    
    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }
    
    public Long getPrivId()
    {
        return this.privId;
    }
    
    public void setPrivId(Long privId)
    {
        this.privId = privId;
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