package com.asiainfo.meo.system.common.app.model.entity;

// Generated 2015-3-25 16:30:00 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

/**
 * SysCategoryDef generated by hbm2java
 */
public class SysCategoryDef implements java.io.Serializable
{
    
    /**                                                                           
      * @Fields serialVersionUID : (用一句话描述这个变量表示什么)                                                                                                                                                                                                                                  
      */
    private static final long serialVersionUID = 1L;

    public static final Integer CAMPAGIN_TYPE = 1;
    
    public static final Integer PRODUCT_TYPE  = 2;
    
    public static final Integer STS_ACTIVE    = 1;
    
    public static final Integer STS_INACTIVE  = 2;
    
    private Long                categoryId;
    
    private String              name;
    
    private String              description;
    
    private Integer             sts;
    
    private Timestamp           createDate;
    
    private Timestamp           modifyDate;
    
    private Long                operatorId;
    
    private Long                parentCateId;
    
    private Integer             categoryType;
    
    private String              iconURL;
    
    public Integer getCategoryType()
    {
        return categoryType;
    }
    
    public void setCategoryType(Integer categoryType)
    {
        this.categoryType = categoryType;
    }
    
    public Long getCategoryId()
    {
        return this.categoryId;
    }
    
    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getDescription()
    {
        return this.description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
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
    
    public Long getOperatorId()
    {
        return this.operatorId;
    }
    
    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
    }
    
    public Long getParentCateId()
    {
        return this.parentCateId;
    }
    
    public void setParentCateId(Long parentCateId)
    {
        this.parentCateId = parentCateId;
    }

    public String getIconURL()
    {
        return iconURL;
    }

    public void setIconURL(String iconURL)
    {
        this.iconURL = iconURL;
    }
        
}
