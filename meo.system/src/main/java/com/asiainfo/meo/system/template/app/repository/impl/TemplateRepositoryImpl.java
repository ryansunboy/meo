package com.asiainfo.meo.system.template.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.SequenceUtil;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.system.template.app.model.entity.SysMessageTemplateDef;
import com.asiainfo.meo.system.template.app.model.entity.SysTemplateParamDef;
import com.asiainfo.meo.system.template.app.model.entity.SysTemplateParamRule;
import com.asiainfo.meo.system.template.app.repository.TemplateRepository;

public class TemplateRepositoryImpl implements TemplateRepository
{
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    @Override
    public void createSysMessageTemplateDef(SysMessageTemplateDef template)
    {
        template.setCreateDate(DateTimeUtil.getNow());
        template.setSts(SysMessageTemplateDef.STS_VALID);
        template.setTemplateId(SequenceUtil.getSequence(SysMessageTemplateDef.SEQ_SYS_MESSAGE_TEMPLATE));
        hibernateRepository.saveObject(template);
        
    }
    
    @Override
    public void modifySysMessageTemplateDef(SysMessageTemplateDef template)
    {
        template.setModifyDate(DateTimeUtil.getNow());
        hibernateRepository.updateObject(template);
        
    }
    
    public void deleteSysMessageTemplateDef(SysMessageTemplateDef template)
    {
        hibernateRepository.deleteObject(template);
    }
    
    @Override
    public SysMessageTemplateDef getSysMessageTemplateDefByTemplateId(Long templateId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysMessageTemplateDef.class)
                .add(Restrictions.eq("templateId", templateId))
                .add(Restrictions.eq("sts", SysMessageTemplateDef.STS_VALID));
        return hibernateRepository.findUniqueObjectByCriteria(criteria);
    }
    
    @Override
    public void createSysTemplateParamDefine(SysTemplateParamDef templateParam)
    {
        templateParam.setCreateDate(DateTimeUtil.getNow());
        templateParam.setSts(SysTemplateParamDef.STS_VALID);
        templateParam.setParamId(SequenceUtil.getSequence(SysTemplateParamDef.SEQ_SYS_TEMPLATE_PARAM));
        hibernateRepository.saveObject(templateParam);
        
    }
    
    @Override
    public void modifySysTemplateParamDefine(SysTemplateParamDef templateParam)
    {
        templateParam.setModifyDate(DateTimeUtil.getNow());
        hibernateRepository.updateObject(templateParam);
        
    }
    
    public void deleteSysTemplateParamDefine(SysTemplateParamDef templateParam)
    {
        hibernateRepository.deleteObject(templateParam);
    }
    
    @Override
    public SysTemplateParamDef getSysTemplateParamDefineByParamId(Long paramId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysTemplateParamDef.class)
                .add(Restrictions.eq("paramId", paramId))
                .add(Restrictions.eq("sts", SysTemplateParamDef.STS_VALID));
        return hibernateRepository.findUniqueObjectByCriteria(criteria);
    }

    @Override
    public SysTemplateParamRule getSysTemplateParamRuleByParamId(Long paramId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysTemplateParamRule.class)
                .add(Restrictions.eq("paramId", paramId))
                .add(Restrictions.eq("sts", SysTemplateParamRule.STS_VALID));
        return hibernateRepository.findUniqueObjectByCriteria(criteria);
    }

    @Override
    public String getResultBySql(String sql, String param)
    {
       return hibernateRepository.findUniqueObjectByNativeSql(sql, param);
    }

    @Override
    public List<SysMessageTemplateDef> getMessageTemplateList(Integer pageNo, Integer pageSize, Long templateId, String templateName)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysMessageTemplateDef.class)
                .add(Restrictions.eq("sts", SysTemplateParamRule.STS_VALID));
        if (ValidateUtil.isNotEmpty(templateId))
        {
            criteria.add(Restrictions.sqlRestriction("CAST({alias}.TEMPLATE_ID AS CHAR) like ?", "%"+ templateId+ "%",
                    StandardBasicTypes.STRING));
        }
        if (ValidateUtil.isNotEmpty(templateName))
        {
            criteria.add(Restrictions.like("templateName", "%"+templateName+"%"));
        }
        criteria.addOrder(Property.forName("templateId").desc());
        return (List<SysMessageTemplateDef>)hibernateRepository.findByCriteria(criteria, (pageNo - 1) * pageSize, pageSize);
    }

    @Override
    public Integer getMessageTemplateTotalSize()
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysMessageTemplateDef.class)
                .add(Restrictions.eq("sts", SysTemplateParamRule.STS_VALID));
        List<SysMessageTemplateDef> list =  (List<SysMessageTemplateDef>)hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(list) ? 0 : list.size();
    }
    
    @Override
    public List<SysTemplateParamDef> getSysTemplateParamList(Integer pageNo, Integer pageSize, Long paramId, String paramName)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysTemplateParamDef.class)
                .add(Restrictions.eq("sts", SysTemplateParamDef.STS_VALID));
        if (ValidateUtil.isNotEmpty(paramId))
        {
            criteria.add(Restrictions.sqlRestriction("CAST({alias}.PARAM_ID AS CHAR) like ?", "%"+ paramId+ "%",
                    StandardBasicTypes.STRING));
        }
        if (ValidateUtil.isNotEmpty(paramName))
        {
            criteria.add(Restrictions.like("paramName", "%"+paramName+"%"));
        }
        return (List<SysTemplateParamDef>)hibernateRepository.findByCriteria(criteria, (pageNo - 1) * pageSize, pageSize);
    }

    @Override
    public Integer getSysTemplateParamTotalSize()
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysTemplateParamDef.class)
                .add(Restrictions.eq("sts", SysTemplateParamDef.STS_VALID));
        List<SysTemplateParamDef> list =  (List<SysTemplateParamDef>)hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(list) ? 0 : list.size();
    }
    
}
