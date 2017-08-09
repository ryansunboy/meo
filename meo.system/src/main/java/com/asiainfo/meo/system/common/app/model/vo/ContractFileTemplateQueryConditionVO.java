package com.asiainfo.meo.system.common.app.model.vo;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
  * @Description: The class contains query's condition of contract file template                
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author Thanapol                                                                                                                                                                                                                                                                           
  * @Date Aug 21, 2015 2:18:29 PM 
  * @version 1.0
 */
public class ContractFileTemplateQueryConditionVO
{
    private Long templateId;
    private String templateName;
    private Integer pageNo;
    private Integer pageSize;
    private Integer templateType;
    
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
    public Integer getPageNo()
    {
        return pageNo;
    }
    public void setPageNo(Integer pageNo)
    {
        this.pageNo = pageNo;
    }
    public Integer getPageSize()
    {
        return pageSize;
    }
    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }
    public Integer getTemplateType()
    {
        return templateType;
    }
    public void setTemplateType(Integer templateType)
    {
        this.templateType = templateType;
    }
}
