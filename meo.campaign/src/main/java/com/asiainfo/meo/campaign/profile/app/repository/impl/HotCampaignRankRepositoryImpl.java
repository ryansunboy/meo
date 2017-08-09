package com.asiainfo.meo.campaign.profile.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.campaign.profile.app.model.entity.HotCampaignRank;
import com.asiainfo.meo.campaign.profile.app.repository.HotCampaignRankRepository;
import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.utils.ValidateUtil;

public class HotCampaignRankRepositoryImpl implements HotCampaignRankRepository
{
    //private final static String SEQ_HOT_CAMPAIGN_RANK_ID = "SEQ_HOT_CAMPAIGN_RANK_ID";
    
    @Resource
    private HibernateRepository hibernateRepository;

    public HotCampaignRank get(Long campaignId)
    {
        return hibernateRepository.get(HotCampaignRank.class, campaignId);
    }

    public List<HotCampaignRank> getHotCampaignRankDescOrder(Integer categoryId, Integer pageSize, Integer pageNo)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(HotCampaignRank.class);
        if (ValidateUtil.isNotNull(categoryId))
            criteria.add(Restrictions.eq("categoryId", categoryId));
        criteria.addOrder(Order.desc("participateCount"));
        return (List<HotCampaignRank>)hibernateRepository.findByCriteria(criteria, pageSize* (pageNo- 1), pageSize);
    }
    
    public List<HotCampaignRank> getHotCampaignRankDescOrder(Integer categoryId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(HotCampaignRank.class);
        if (ValidateUtil.isNotNull(categoryId))
            criteria.add(Restrictions.eq("categoryId", categoryId));
        criteria.addOrder(Order.desc("participateCount"));
        return (List<HotCampaignRank>)hibernateRepository.findByCriteria(criteria);
    }

    @Override
    public void saveHotCampaignRank(HotCampaignRank hotCampaignRank)
    {
        hibernateRepository.saveObject(hotCampaignRank);
    }

    @Override
    public void updateHotCampaignRank(HotCampaignRank hotCampaignRank)
    {
        hibernateRepository.updateObject(hotCampaignRank);
    }
}
