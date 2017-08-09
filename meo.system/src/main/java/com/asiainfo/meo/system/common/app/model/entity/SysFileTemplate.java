package com.asiainfo.meo.system.common.app.model.entity;

import java.sql.Timestamp;

public class SysFileTemplate implements java.io.Serializable
{
    /**
     * @Fields serialVersionUID : (用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;
    
    private Long              templateId;
    
    private String            templateName;
    
    private String            templateUrl;
    
    private String            templateDesc;
    
    private Integer           templateType;
    
    private Long              operatorId;
    
    private Timestamp         createDate;
    
    private Timestamp         modifyDate;
    
    private Integer           sts;
    
    public static final int   STS_VALID        = 1;
    
    public static final int   STS_INVALID      = 2;
    
    public static final int   CONTRACT_TYPE    = 1;
    
    public Long getTemplateId()
    {
        return templateId;
    }
    
    public void setTemplateId(Long templateId)
    {
        this.templateId = templateId;
    }
    
    public String getTemplateName()
    {
        return templateName;
    }
    
    public void setTemplateName(String templateName)
    {
        this.templateName = templateName;
    }
    
    public String getTemplateUrl()
    {
        return templateUrl;
    }
    
    public void setTemplateUrl(String templateUrl)
    {
        this.templateUrl = templateUrl;
    }
    
    public String getTemplateDesc()
    {
        return templateDesc;
    }
    
    public void setTemplateDesc(String templateDesc)
    {
        this.templateDesc = templateDesc;
    }
    
    public Integer getTemplateType()
    {
        return templateType;
    }
    
    public void setTemplateType(Integer templateType)
    {
        this.templateType = templateType;
    }
    
    public Long getOperatorId()
    {
        return operatorId;
    }
    
    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
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
    
    public Integer getSts()
    {
        return sts;
    }
    
    public void setSts(Integer sts)
    {
        this.sts = sts;
    }
    
}