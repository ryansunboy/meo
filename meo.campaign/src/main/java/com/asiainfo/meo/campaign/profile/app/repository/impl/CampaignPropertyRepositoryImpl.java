package com.asiainfo.meo.campaign.profile.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignProperty;
import com.asiainfo.meo.campaign.profile.app.repository.CampaignPropertyRepository;
import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;

public class CampaignPropertyRepositoryImpl implements CampaignPropertyRepository
{
    private final static String SEQ_CAMPAIGN_PROPERTY_ID = "SEQ_CAMPAIGN_PROPERTY_ID";
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    /**
     * @Description: 通过campaignId查找campaign的属性
     * @Description: get campaign property by campaign id
     * @author zhaozx
     * @param campaignId
     * @return
     */
    public List<CampaignProperty> getCampaignPropertyByCampaignId(long campaignId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignProperty.class).add(
                Restrictions.eq("campaignId", campaignId)).add(Restrictions.eq("sts", CampaignProperty.STS_VALID));
        List<CampaignProperty> campaignPropertyList = (List<CampaignProperty>) hibernateRepository.findByCriteria(criteria);
        return campaignPropertyList;
    }
    
    public CampaignProperty saveCampaignProperty(CampaignProperty campaignProperty)
    {
        campaignProperty.setPropertyId(ServiceLocatorFactory.getService(Sequence.class).next(SEQ_CAMPAIGN_PROPERTY_ID));
        hibernateRepository.saveObject(campaignProperty);
        return getCampaignPropertyById(campaignProperty.getPropertyId());
    }
    
    public CampaignProperty getCampaignPropertyById(Long id)
    {
        return hibernateRepository.get(CampaignProperty.class, id);
    }

    @Override
    public CampaignProperty updateCampaignProperty(CampaignProperty campaignProperty)
    {
        hibernateRepository.updateObject(campaignProperty);
        return getCampaignPropertyById(campaignProperty.getPropertyId()); 
    }

    @Override
    public void deleteCampaignProperty(Long id)
    {
        CampaignProperty campaignProperty = getCampaignPropertyById(id);
        hibernateRepository.deleteObject(campaignProperty);
    }


    @Override
    public List<CampaignProperty> getCampaignPropertyListByCampaignId(Long campaignId)
    {
        DetachedCriteria  criteria=DetachedCriteria.forClass(CampaignProperty.class).add(Restrictions.eq("campaignId", campaignId)).add(Restrictions.eq("sts", CampaignProperty.STS_VALID));
        List<CampaignProperty> campaignPropertyList=(List<CampaignProperty>) hibernateRepository.findByCriteria(criteria);
        return campaignPropertyList;
    }
}
