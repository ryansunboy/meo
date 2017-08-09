package com.asiainfo.meo.system.template.app.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.system.define.SysErrorCodeDefine;
import com.asiainfo.meo.system.template.app.bo.TemplateBO;
import com.asiainfo.meo.system.template.app.model.entity.SysMessageTemplateDef;
import com.asiainfo.meo.system.template.app.model.entity.SysTemplateParamDef;
import com.asiainfo.meo.system.template.app.model.vo.MessageTemplateDefVO;
import com.asiainfo.meo.system.template.app.model.vo.TemplateParamDefVO;
import com.asiainfo.meo.system.template.app.repository.TemplateRepository;

public class TemplateBOImpl implements TemplateBO
{
    @Resource
    TemplateRepository templateRepository;
    @Override
    public void createSysMessageTemplateDef(MessageTemplateDefVO template)
    {
        SysMessageTemplateDef sysMessageTemplateDef = new SysMessageTemplateDef();
        sysMessageTemplateDef.setTemplateName(template.getTemplateName());
        sysMessageTemplateDef.setTemplateContent(template.getTemplateContent());
        sysMessageTemplateDef.setTemplateDesc(template.getTemplateDesc());
        sysMessageTemplateDef.setOperatorId(template.getOperatorId());
        templateRepository.createSysMessageTemplateDef(sysMessageTemplateDef);
        
    }

    @Override
    public void modifySysMessageTemplateDef(MessageTemplateDefVO template)
    {
        SysMessageTemplateDef  sysMessageTemplateDef = templateRepository.getSysMessageTemplateDefByTemplateId(template.getTemplateId());
        if (ValidateUtil.isEmpty(sysMessageTemplateDef))
        {
            throw new MeoException(SysErrorCodeDefine.MESSAGE_TEMPLATE_DOES_NOT_EXIST, new Object[]{template.getTemplateId()});
        }
        sysMessageTemplateDef.setTemplateName(template.getTemplateName());
        sysMessageTemplateDef.setTemplateContent(template.getTemplateContent());
        if (template.getTemplateDesc() != null)
        {
            sysMessageTemplateDef.setTemplateDesc(template.getTemplateDesc());
        }
        templateRepository.modifySysMessageTemplateDef(sysMessageTemplateDef);
    }
    
    public void deleteSysMessageTemplateDef(Long templateId)
    {
        SysMessageTemplateDef  sysMessageTemplateDef = templateRepository.getSysMessageTemplateDefByTemplateId(templateId);
        if (ValidateUtil.isEmpty(sysMessageTemplateDef))
        {
            throw new MeoException(SysErrorCodeDefine.MESSAGE_TEMPLATE_DOES_NOT_EXIST, new Object[]{templateId});
        }
        templateRepository.deleteSysMessageTemplateDef(sysMessageTemplateDef);
    }

    @Override
    public MessageTemplateDefVO getSysMessageTemplateDefByTemplateId(Long templateId)
    {
        SysMessageTemplateDef sysMessageTemplateDef = templateRepository.getSysMessageTemplateDefByTemplateId(templateId);
        MessageTemplateDefVO messageTemplateDefVO = new MessageTemplateDefVO();
        if (ValidateUtil.isEmpty(sysMessageTemplateDef))
        {
            return null;
        }
        messageTemplateDefVO.setTemplateId(templateId);
        messageTemplateDefVO.setTemplateName(sysMessageTemplateDef.getTemplateName());
        messageTemplateDefVO.setTemplateContent(sysMessageTemplateDef.getTemplateContent());
        messageTemplateDefVO.setTemplateDesc(sysMessageTemplateDef.getTemplateDesc());
        return messageTemplateDefVO;
    }

    @Override
    public void createSysTemplateParamDefine(TemplateParamDefVO templateParam)
    {
        SysTemplateParamDef sysTemplateParamDef = new SysTemplateParamDef();
        sysTemplateParamDef.setOperatorId(templateParam.getOperatorId());
        sysTemplateParamDef.setParamName(templateParam.getParamName());
        sysTemplateParamDef.setParamDesc(templateParam.getParamDesc());
        templateRepository.createSysTemplateParamDefine(sysTemplateParamDef);
    }

    @Override
    public void modifySysTemplateParamDefine(TemplateParamDefVO templateParam)
    {
        SysTemplateParamDef  sysTemplateParamDef = templateRepository.getSysTemplateParamDefineByParamId(templateParam.getParamId());
        if (ValidateUtil.isEmpty(sysTemplateParamDef))
        {
            throw new MeoException(SysErrorCodeDefine.TEMPLATE_PARAMETER_DOES_NOT_EXIST, new Object[]{templateParam.getParamId()});
        }
        sysTemplateParamDef.setParamName(templateParam.getParamName());
        if (templateParam.getParamDesc() != null)
        {
            sysTemplateParamDef.setParamDesc(templateParam.getParamDesc());
        }
        templateRepository.modifySysTemplateParamDefine(sysTemplateParamDef);
    }

    @Override
    public SysTemplateParamDef getSysTemplateParamDefineByParamId(Long paramId)
    {
        return templateRepository.getSysTemplateParamDefineByParamId(paramId);
    }

    @Override
    public String generateActivityMessageByActionId(Long actionId, String... params)
    {
        Long templateId = 1L;
        SysMessageTemplateDef template = templateRepository.getSysMessageTemplateDefByTemplateId(templateId);
        String templateContext = template.getTemplateContent();
        for (String param : params)
        {
            int begin = templateContext.indexOf("<");
            int end = templateContext.indexOf(">");
            String paramId = templateContext.substring(begin+1, end);
            templateContext=templateContext.replaceFirst("<"+paramId+">", param);
            /*SysTemplateParamRule rule = templateRepository.getSysTemplateParamRuleByParamId(Long.valueOf(paramId));
            if(rule.getRuleScriptType().equals(SysTemplateParamRule.SCRIPT_TYPE_SQL)){
                String sql = rule.getScriptContext();
               String replaceFiled =  templateRepository.getResultBySql(sql,param);
               templateContext=templateContext.replaceFirst("<"+paramId+">", replaceFiled);
            }*/
            
        }
        return templateContext;
    }

    @Override
    public PageInfo<MessageTemplateDefVO> getMessageTemplateList(Integer pageNo, Integer pageSize, Long templateId, String templateName)
    {
        PageInfo<MessageTemplateDefVO> page = new PageInfo<MessageTemplateDefVO>();
        List<SysMessageTemplateDef> sysMessageTemplateDefList = templateRepository.getMessageTemplateList(pageNo, pageSize, templateId, templateName);
        Integer totalSize = templateRepository.getMessageTemplateTotalSize();
        List<MessageTemplateDefVO> list = new ArrayList<MessageTemplateDefVO>();
        for (SysMessageTemplateDef sysMessageTemplateDef : sysMessageTemplateDefList)
        {
            MessageTemplateDefVO messageTemplateDefVO = new MessageTemplateDefVO();
            messageTemplateDefVO.setTemplateId(sysMessageTemplateDef.getTemplateId());
            messageTemplateDefVO.setTemplateContent(sysMessageTemplateDef.getTemplateContent());
            messageTemplateDefVO.setTemplateName(sysMessageTemplateDef.getTemplateName());
            messageTemplateDefVO.setTemplateDesc(sysMessageTemplateDef.getTemplateDesc());
            list.add(messageTemplateDefVO);
        }
        return page.createPageInfo(pageNo, pageSize, totalSize, list);
                
    }
    
    public void deleteSysTemplateParamDefine(Long paramId)
    {
        SysTemplateParamDef  sysTemplateParamDef = templateRepository.getSysTemplateParamDefineByParamId(paramId);
        if (ValidateUtil.isEmpty(sysTemplateParamDef))
        {
            throw new MeoException(SysErrorCodeDefine.TEMPLATE_PARAMETER_DOES_NOT_EXIST, new Object[]{paramId});
        }
        templateRepository.deleteSysTemplateParamDefine(sysTemplateParamDef);
    }

    @Override
    public PageInfo<TemplateParamDefVO> getSysTemplateParamList(Integer pageNo, Integer pageSize, Long paramId, String paramName)
    {
        PageInfo<TemplateParamDefVO> page = new PageInfo<TemplateParamDefVO>();
        List<SysTemplateParamDef> sysTemplateParamDefList = templateRepository.getSysTemplateParamList(pageNo, pageSize, paramId, paramName);
        Integer totalSize = templateRepository.getSysTemplateParamTotalSize();
        List<TemplateParamDefVO> list = new ArrayList<TemplateParamDefVO>();
        for (SysTemplateParamDef sysTemplateParamDef : sysTemplateParamDefList)
        {
            TemplateParamDefVO templateParamDefVO = new TemplateParamDefVO();
            templateParamDefVO.setParamId(sysTemplateParamDef.getParamId());
            templateParamDefVO.setParamName(sysTemplateParamDef.getParamName());
            templateParamDefVO.setParamDesc(sysTemplateParamDef.getParamDesc());
            list.add(templateParamDefVO);
        }
        return page.createPageInfo(pageNo, pageSize, totalSize, list);
    }

   
    
}
