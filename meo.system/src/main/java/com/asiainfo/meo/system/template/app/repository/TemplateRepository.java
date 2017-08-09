package com.asiainfo.meo.system.template.app.repository;

import java.util.List;

import com.asiainfo.meo.system.template.app.model.entity.SysMessageTemplateDef;
import com.asiainfo.meo.system.template.app.model.entity.SysTemplateParamDef;
import com.asiainfo.meo.system.template.app.model.entity.SysTemplateParamRule;

public interface TemplateRepository
{
    /**
     * 
      * @Description: create message template
      * @modifyReason: 
      * @author liuyang
      * @param template
     */
    public void createSysMessageTemplateDef(SysMessageTemplateDef template);
    
    /**
     * 
      * @Description: modify message template
      * @modifyReason: 
      * @author liuyang
     */
    public void modifySysMessageTemplateDef(SysMessageTemplateDef template);
    
    /**
     * 
      * @Description: get message template by template id
      * @modifyReason: 
      * @author liuyang
      * @param templateId
      * @return
     */
    public SysMessageTemplateDef getSysMessageTemplateDefByTemplateId(Long templateId);
    
    /**
     * 
      * @Description: create template parameter define 
      * @modifyReason: 
      * @author liuyang
      * @param templateParam
     */
    public void createSysTemplateParamDefine(SysTemplateParamDef templateParam);
    
    /**
     * 
      * @Description: modify template parameter define
      * @modifyReason: 
      * @author liuyang
      * @param templateParam
     */
    public void modifySysTemplateParamDefine(SysTemplateParamDef templateParam);
    
    /**
     * 
      * @Description: get template parameter define by param id
      * @modifyReason: 
      * @author liuyang
      * @param paramId
      * @return
     */
    public SysTemplateParamDef getSysTemplateParamDefineByParamId(Long paramId);
    
    /**
     * 
      * @Description: get the template param rule by param id
      * @modifyReason: 
      * @author liuyang
      * @param paramId
      * @return
     */
    public SysTemplateParamRule getSysTemplateParamRuleByParamId(Long paramId);
    /**
     * 
      * @Description: get result with sql.
      * @modifyReason: 
      * @author liuyang
      * @param sql
      * @param param
      * @return
     */
    public String getResultBySql(String sql, String param);
    
    public void deleteSysMessageTemplateDef(SysMessageTemplateDef template);
    
    public List<SysMessageTemplateDef> getMessageTemplateList(Integer pageNo, Integer pageSize, Long templateId, String templateName);
    
    public Integer getMessageTemplateTotalSize();
    
    public void deleteSysTemplateParamDefine(SysTemplateParamDef templateParam);
    
    public List<SysTemplateParamDef> getSysTemplateParamList(Integer pageNo, Integer pageSize, Long paramId, String paramName);
    
    public Integer getSysTemplateParamTotalSize();
    
}
