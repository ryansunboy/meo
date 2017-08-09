package com.asiainfo.meo.system.common.app.model.entity;

// Generated 2015-3-25 16:30:00 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SysOperatorDef generated by hbm2java
 */
@Entity
@Table(name = "SYS_OPERATOR_DEF")
public class SysOperatorDef implements java.io.Serializable
{
    
    /**                                                                           
      * @Fields serialVersionUID : (用一句话描述这个变量表示什么)                                                                                                                                                                                                                                  
      */
    private static final long serialVersionUID = 1L;

    private long      operatorId;
    
    private String    name;
    
    private String    description;
    
    private Integer   sts;
    
    private Timestamp createDate;
    
    private Timestamp modifyDate;
    
    private Integer   operatorType;
    
    @Id
    @Column(name = "OPERATOR_ID", unique = true, nullable = false)
    public long getOperatorId()
    {
        return this.operatorId;
    }
    
    public void setOperatorId(long operatorId)
    {
        this.operatorId = operatorId;
    }
    
    @Column(name = "NAME", length = 128)
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    @Column(name = "DESCRIPTION", length = 512)
    public String getDescription()
    {
        return this.description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    @Column(name = "STS")
    public Integer getSts()
    {
        return this.sts;
    }
    
    public void setSts(Integer sts)
    {
        this.sts = sts;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE", length = 19)
    public Timestamp getCreateDate()
    {
        return this.createDate;
    }
    
    public void setCreateDate(Timestamp createDate)
    {
        this.createDate = createDate;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFY_DATE", length = 19)
    public Timestamp getModifyDate()
    {
        return this.modifyDate;
    }
    
    public void setModifyDate(Timestamp modifyDate)
    {
        this.modifyDate = modifyDate;
    }
    
    @Column(name = "OPERATOR_TYPE")
    public Integer getOperatorType()
    {
        return this.operatorType;
    }
    
    public void setOperatorType(Integer operatorType)
    {
        this.operatorType = operatorType;
    }
    
}