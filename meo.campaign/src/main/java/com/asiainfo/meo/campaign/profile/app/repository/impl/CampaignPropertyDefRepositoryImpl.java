package com.asiainfo.meo.campaign.profile.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignPropertyDef;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignPropertyDefQueryConditionVO;
import com.asiainfo.meo.campaign.profile.app.repository.CampaignPropertyDefRepository;
import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;

public class CampaignPropertyDefRepositoryImpl implements CampaignPropertyDefRepository
{
    private final static String SEQ_CAMPAIGN_PROPERTY_DEF_ID = "SEQ_CAMPAIGN_PROPERTY_DEF_ID";
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    /**
     * @Description: 获得campaign属性定义信息
     * @Description: get campaign property
     * @author zhaozx
     * @param propertyKey
     * @return
     */
    public CampaignPropertyDef getCampaignPropertyDef(long propertyKey)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignPropertyDef.class);
        criteria.add(Restrictions.eq("propertyKey", propertyKey));
        return hibernateRepository.findUniqueObjectByCriteria(criteria);
    }
    
    public CampaignPropertyDef getCampaignPropertyDefByCode(String propertyCode, int campaignType)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignPropertyDef.class);
        criteria.add(Restrictions.eq("propertyCode", propertyCode));
        criteria.add(Restrictions.eq("campaignType", campaignType));
        List<CampaignPropertyDef> list = (List<CampaignPropertyDef>) hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(list) ? null : list.get(0);
    }
    
    @Override
    public List<CampaignPropertyDef> getMandatoryPropertyCampaignPropertyDefByCampaignType(int campaignType)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignPropertyDef.class);
        criteria.add(Restrictions.eq("mandatory", CampaignPropertyDef.IS_MANDATORY));
        criteria.add(Restrictions.eq("campaignType", campaignType));
        criteria.add(Restrictions.eq("sts", CampaignPropertyDef.STS_VALID));
        return (List<CampaignPropertyDef>) hibernateRepository.findByCriteria(criteria);
    }
    
    private DetachedCriteria getCampaignPropertyDefDetachedCriteria(
            final CampaignPropertyDefQueryConditionVO conditionVO)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignPropertyDef.class);
        criteria.add(Restrictions.eq("sts", CampaignPropertyDef.STS_VALID));
        if (conditionVO!= null && conditionVO.getCampaignType()!=null)
        {
            criteria.add(Restrictions.eq("campaignType", conditionVO.getCampaignType()));
        }
        return criteria;
    }
    
    @Override
    public List<CampaignPropertyDef> getCampaignPropertyDefByCampaignType(
            final CampaignPropertyDefQueryConditionVO conditionVO,
            final Integer pageSize,
            final Integer pageNo)
    {
        DetachedCriteria criteria = getCampaignPropertyDefDetachedCriteria(conditionVO);
        return (List<CampaignPropertyDef>) hibernateRepository.findByCriteria(criteria,
                (pageNo - 1) * pageSize, pageSize);
    }
    
    @Override
    public Integer getCampaignPropertyDefByCampaignTypeTotalSize(
            final CampaignPropertyDefQueryConditionVO conditionVO)
    {
        DetachedCriteria criteria = getCampaignPropertyDefDetachedCriteria(conditionVO);
        final List<CampaignPropertyDef> list = (List<CampaignPropertyDef>) hibernateRepository.
                findByCriteria(criteria);
        return ValidateUtil.isNotEmpty(list)?list.size():null;
    }
    
    @Override
    public void addCampaignPropertyDef(CampaignPropertyDef campaignPropertyDef)
    {
        Long propertyKey = ServiceLocatorFactory.getService(Sequence.class).next(SEQ_CAMPAIGN_PROPERTY_DEF_ID);
        campaignPropertyDef.setPropertyKey(propertyKey);
        campaignPropertyDef.setCreateDate(DateTimeUtil.getNow());
        hibernateRepository.saveObject(campaignPropertyDef);
    }

    @Override
    public void modifyCampaignPropertyDef(CampaignPropertyDef campaignPropertyDef)
    {
        campaignPropertyDef.setModifyDate(DateTimeUtil.getNow());
        hibernateRepository.updateObject(campaignPropertyDef);
    }    
}
