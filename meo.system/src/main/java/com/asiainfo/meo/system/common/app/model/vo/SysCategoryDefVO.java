package com.asiainfo.meo.system.common.app.model.vo;

// Generated 2015-3-25 16:30:00 by Hibernate Tools 3.4.0.CR1


/**
 * SysCategoryDef generated by hbm2java
 */
public class SysCategoryDefVO implements java.io.Serializable
{
    private Long                categoryId;
    
    private String              name;
    
    private String              description;

    private Long                operatorId;
    
    private Long                parentCateId;
    
    public Long getCategoryId()
    {
        return categoryId;
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
    
}
