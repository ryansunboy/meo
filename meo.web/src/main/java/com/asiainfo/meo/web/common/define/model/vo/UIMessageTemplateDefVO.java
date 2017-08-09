package com.asiainfo.meo.web.common.define.model.vo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

public class UIMessageTemplateDefVO implements java.io.Serializable
{
    @Valid
    @NotNull(groups={Update.class})
    private Long                templateId;
    
    @Valid
    @NotEmpty(groups={Insert.class, Update.class})
    private String              templateName;
    
    @Valid
    @NotEmpty(groups={Insert.class, Update.class})
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
