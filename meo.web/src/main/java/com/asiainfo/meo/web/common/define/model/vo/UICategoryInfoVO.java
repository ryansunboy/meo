package com.asiainfo.meo.web.common.define.model.vo;

public class UICategoryInfoVO
{
    private String name;
    
    private String description;
    
    private Long categoryId;
    
    private String createDate;
    
    private String modifyDate;
    
    private Long operationId;
    
    private Long parentCateId;

    //added by zhengzy 2015-9-10 15:41:53
    private String iconURL;
    
    public String getIconURL()
    {
        return iconURL;
    }

    public void setIconURL(String iconURL)
    {
        this.iconURL = iconURL;
    }

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
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public String getModifyDate()
    {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate)
    {
        this.modifyDate = modifyDate;
    }

    public Long getOperationId()
    {
        return operationId;
    }

    public void setOperationId(Long operationId)
    {
        this.operationId = operationId;
    }

    public Long getParentCateId()
    {
        return parentCateId;
    }

    public void setParentCateId(Long parentCateId)
    {
        this.parentCateId = parentCateId;
    }
    
}
