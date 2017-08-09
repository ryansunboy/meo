package com.asiainfo.meo.system.common.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;

import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.system.common.app.model.entity.SysFileTemplate;
import com.asiainfo.meo.system.common.app.model.vo.ContractFileTemplateQueryConditionVO;
import com.asiainfo.meo.system.common.app.repository.SysFileTemplateRepository;

public class SysFileTemplateRepositoryImpl implements SysFileTemplateRepository
{
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    private static final String SEQ_SYS_FILE_TEMPLATE_ID = "SEQ_SYS_FILE_TEMPLATE_ID";
    
    @Override
    public SysFileTemplate addSysFileTemplate(SysFileTemplate sysFileTemplate)
    {
        Long templateId = ((Sequence) ServiceLocatorFactory.getService(Sequence.class)).next(SEQ_SYS_FILE_TEMPLATE_ID);
        sysFileTemplate.setTemplateId(templateId);
        sysFileTemplate.setCreateDate(DateTimeUtil.getNow());
        sysFileTemplate.setOperatorId(BoContext.getBoContext().getUserId());
        hibernateRepository.saveObject(sysFileTemplate);
        return sysFileTemplate;
    }
    
    @Override
    public void deleteSysFileTemplateByTemplateId(Long templateId)
    {
        SysFileTemplate sysFileTemplate =  getSysFileTemplateByTemplateId(templateId);
        hibernateRepository.deleteObject(sysFileTemplate);
    }
    
    @Override
    public SysFileTemplate updateSysFileTemplate(SysFileTemplate sysFileTemplate)
    {
        sysFileTemplate.setModifyDate(DateTimeUtil.getNow());
        hibernateRepository.updateObject(sysFileTemplate);
        return sysFileTemplate;
    }
    
    private DetachedCriteria getSysFileTemplateDetachedCriteria(final ContractFileTemplateQueryConditionVO conditionVO)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysFileTemplate.class);
        criteria.add(Restrictions.eq("sts", SysFileTemplate.STS_VALID));
        if (ValidateUtil.isNotNull(conditionVO))
        {
            if (ValidateUtil.isNotEmpty(conditionVO.getTemplateType()))
            {
                criteria.add(Restrictions.eq("templateType", conditionVO.getTemplateType()));
            }
            if (ValidateUtil.isNotNull(conditionVO.getTemplateId()))
            {
                criteria.add(Restrictions.sqlRestriction("CAST({alias}.TEMPLATE_ID AS CHAR) like ?", "%"+ conditionVO.getTemplateId()+ "%",
                        StandardBasicTypes.STRING));
            }
            if (ValidateUtil.isNotEmpty(conditionVO.getTemplateName()))
            {
                criteria.add(Restrictions.like("templateName", "%"+conditionVO.getTemplateName()+"%"));
            }
        }
        criteria.addOrder(Property.forName("templateId").desc());
        return criteria;
    }
    
    @Override
    public List<SysFileTemplate> getSysFileTemplateByTemplateType(final ContractFileTemplateQueryConditionVO conditionVO,
            final Integer pageSize, final Integer pageNo)
    {
        DetachedCriteria criteria = getSysFileTemplateDetachedCriteria(conditionVO);  
        
        return (List<SysFileTemplate>) hibernateRepository.findByCriteria(criteria, (pageNo - 1) * pageSize, pageSize);
    }
    
    @Override
    public Integer getSysFileTemplateByTemplateTypeTotalSize(ContractFileTemplateQueryConditionVO conditionVO)
    {
        Integer totalSize = null;
        DetachedCriteria criteria = getSysFileTemplateDetachedCriteria(conditionVO);
        final List<SysFileTemplate> list = (List<SysFileTemplate>) hibernateRepository.findByCriteria(criteria);
        if (ValidateUtil.isNotEmpty(list))
        {
            totalSize = list.size();
        }
        return totalSize;
    }
    
    @Override
    public SysFileTemplate getSysFileTemplateByTemplateId(Long templateId)
    {
        return hibernateRepository.get(SysFileTemplate.class, templateId);
    }   
    
}
