package com.asiainfo.meo.system.common.app.model.vo;

/**
 * 
  * @Description: contract File template vo
  * @Description: contract File template vo                  
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author zhengzy                                                                                                                                                                                                                                                                           
  * @Date 2015年7月9日 下午12:11:31 
  * @version 1.0
 */
public class ContractFileTemplateVO
{
    private Long      templateId;
    
    private String    templateName;
    
    private String    templateUrl;
    
    private String    templateDesc;
    
    private Long createDate;
    
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
