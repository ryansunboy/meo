package com.asiainfo.meo.system.template.app.bo;

import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.system.template.app.model.entity.SysTemplateParamDef;
import com.asiainfo.meo.system.template.app.model.vo.MessageTemplateDefVO;
import com.asiainfo.meo.system.template.app.model.vo.TemplateParamDefVO;

public interface TemplateBO
{
    /**
     * 
      * @Description: create message template
      * @modifyReason: 
      * @author liuyang
      * @param template
     */
    public void createSysMessageTemplateDef(MessageTemplateDefVO template);
    
    /**
     * 
      * @Description: modify message template
      * @modifyReason: 
      * @author liuyang
     */
    public void modifySysMessageTemplateDef(MessageTemplateDefVO template);
    
    /**
     * 
      * @Description: get message template by template id
      * @modifyReason: 
      * @author liuyang
      * @param templateId
      * @return
     */
    public MessageTemplateDefVO getSysMessageTemplateDefByTemplateId(Long templateId);
    
    /**
     * 
      * @Description: create template parameter define 
      * @modifyReason: 
      * @author liuyang
      * @param templateParam
     */
    public void createSysTemplateParamDefine(TemplateParamDefVO templateParam);
    
    /**
     * 
      * @Description: modify template parameter define
      * @modifyReason: 
      * @author liuyang
      * @param templateParam
     */
    public void modifySysTemplateParamDefine(TemplateParamDefVO templateParam);
    
    /**
     * 
      * @Description: get template parameter define by param id
      * @modifyReason: 
      * @author liuyang
      * @param paramId
      * @return
     */
    public SysTemplateParamDef getSysTemplateParamDefineByParamId(Long paramId); 
    
    public String generateActivityMessageByActionId(Long actionId,String... params);
    
    public void deleteSysMessageTemplateDef(Long templateId);
    
    public PageInfo<MessageTemplateDefVO> getMessageTemplateList(Integer pageNo, Integer pageSize, Long templateId, String templateName);

    public void deleteSysTemplateParamDefine(Long paramId);
    
    public PageInfo<TemplateParamDefVO> getSysTemplateParamList(Integer pageNo, Integer pageSize, Long paramId, String paramName);
}
