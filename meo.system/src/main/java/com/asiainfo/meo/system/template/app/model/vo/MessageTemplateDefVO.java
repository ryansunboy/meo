package com.asiainfo.meo.system.template.app.model.vo;

public class MessageTemplateDefVO implements java.io.Serializable
{
    
    private Long                templateId;
    
    private String              templateName;
    
    private String              templateContent;
    
    private String              templateDesc;
    
    private Long                operatorId;

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

    public String getTemplateContent()
    {
        return templateContent;
    }

    public void setTemplateContent(String templateContent)
    {
        this.templateContent = templateContent;
    }

    public String getTemplateDesc()
    {
        return templateDesc;
    }

    public void setTemplateDesc(String templateDesc)
    {
        this.templateDesc = templateDesc;
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
