package com.asiainfo.meo.web.partner.model.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

/**
 * 
  * @Description: contract File template vo
  * @Description: contract File template vo                  
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author zhengzy                                                                                                                                                                                                                                                                           
  * @Date 2015年7月9日 下午12:11:31 
  * @version 1.0
 */
public class UIContractTemplateVO
{
    @NotNull(groups={Update.class})
    private Long templateId;
    
    @NotEmpty(groups={Insert.class,Update.class})
    private String templateName;
    
    private String templateDesc;
    
    @NotEmpty(groups={Insert.class})
    private String templateUrl;
    
    private Long   createDate;
    
    //@NotNull(groups={Update.class})
    private Long modifyDate;
    
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
    
    public String getTemplateDesc()
    {
        return templateDesc;
    }
    
    public void setTemplateDesc(String templateDesc)
    {
        this.templateDesc = templateDesc;
    }
    
    public String getTemplateUrl()
    {
        return templateUrl;
    }
    
    public void setTemplateUrl(String templateUrl)
    {
        this.templateUrl = templateUrl;
    }
    
    public Long getCreateDate()
    {
        return createDate;
    }
    
    public void setCreateDate(Long createDate)
    {
        this.createDate = createDate;
    }

    public Long getModifyDate()
    {
        return modifyDate;
    }

    public void setModifyDate(Long modifyDate)
    {
        this.modifyDate = modifyDate;
    }
}
