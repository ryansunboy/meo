package com.asiainfo.meo.campaign.profile.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.campaign.profile.app.model.entity.RewardActivity;
import com.asiainfo.meo.campaign.profile.app.repository.RewardActivityRepository;
import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.utils.ValidateUtil;

public class RewardActivityRepositoryImpl implements RewardActivityRepository
{
   
    private final static String SEQ_REWARD_ACTIVITY_ID = "SEQ_REWARD_ACTIVITY_ID";
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    public List<RewardActivity> getRewardActivityList(Long custId, Integer pageSize, Integer pageNo)
    {
       DetachedCriteria criteria = DetachedCriteria.forClass(RewardActivity.class);
       criteria.add(Restrictions.eq("custId", custId));
       return  (List<RewardActivity>)hibernateRepository.findByCriteria(criteria,pageSize* (pageNo- 1), pageSize);
    }
    
    public Integer getRewardActivityListTotalSize(Long custId)
    {
       DetachedCriteria criteria = DetachedCriteria.forClass(RewardActivity.class);
       criteria.add(Restrictions.eq("custId", custId));
       List<RewardActivity> list = (List<RewardActivity>)hibernateRepository.findByCriteria(criteria);
       return ValidateUtil.isEmpty(list) ? 0 : list.size() ;
    }
    
}
