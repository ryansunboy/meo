package com.asiainfo.meo.campaign.profile.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignPriceRel;
import com.asiainfo.meo.campaign.profile.app.repository.CampaignPriceRelRepository;
import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;

public class CampaignPriceRelRepositoryImpl implements CampaignPriceRelRepository
{
    private final static String SEQ_CAMPAIGN_PRICE_REL_ID = "SEQ_CAMPAIGN_PRICE_REL_ID";
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    public CampaignPriceRel saveCampaignPriceRel(CampaignPriceRel campaignPriceRel)
    {
        campaignPriceRel.setId(ServiceLocatorFactory.getService(Sequence.class).next(SEQ_CAMPAIGN_PRICE_REL_ID));
        hibernateRepository.saveObject(campaignPriceRel);
        return getCampaignPriceRelById(campaignPriceRel.getId());
        
    }
    
    public CampaignPriceRel getCampaignPriceRelById(Long id)
    {
        return hibernateRepository.get(CampaignPriceRel.class, id);
    }
    
    @Override
    public List<CampaignPriceRel> getCampaignPriceRelsByCampaignId(Long campaignId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignPriceRel.class);
        criteria.add(Restrictions.eq("campaignId", campaignId)).add(Restrictions.eq("sts", CampaignPriceRel.STS_VALID));
        return (List<CampaignPriceRel>) hibernateRepository.findByCriteria(criteria);
    }
    
    @Override
    public void deleteCampaignPriceRel(Long id)
    {
        CampaignPriceRel campaignPriceRel = getCampaignPriceRelById(id);
        hibernateRepository.deleteObject(campaignPriceRel);
    }
    
    @Override
    public CampaignPriceRel updateCampaignPriceRel(CampaignPriceRel campaignPriceRel)
    {
        hibernateRepository.updateObject(campaignPriceRel);
        return getCampaignPriceRelById(campaignPriceRel.getId());
    }

    @Override
    public CampaignPriceRel getCampaignPriceRelByPriceIdAndCampaignId(Long priceId, Long campaignId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignPriceRel.class);
        criteria.add(Restrictions.eq("campaignId", campaignId)).add(Restrictions.eq("priceId", priceId)).add(Restrictions.eq("sts", CampaignPriceRel.STS_VALID));
        List<CampaignPriceRel> list = (List<CampaignPriceRel>) hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isNotEmpty(list)?list.get(0):null;
    }
}
