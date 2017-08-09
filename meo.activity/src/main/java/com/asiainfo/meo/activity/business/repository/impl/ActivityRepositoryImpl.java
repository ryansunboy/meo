package com.asiainfo.meo.activity.business.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.activity.business.model.entity.CmActivityRecordHis;
import com.asiainfo.meo.activity.business.model.entity.CmCampaignActivity;
import com.asiainfo.meo.activity.business.model.entity.CmCampaignActivitySummary;
import com.asiainfo.meo.activity.business.model.entity.CmRewardActivity;
import com.asiainfo.meo.activity.business.repository.ActivityRepository;
import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.SequenceUtil;
import com.asiainfo.meo.common.core.utils.ValidateUtil;

public class ActivityRepositoryImpl implements ActivityRepository
{
    
    @Resource
    HibernateRepository repository;
    
    public void updateActivitySummary(CmCampaignActivitySummary summary)
    {
        if (ValidateUtil.isNotEmpty(summary))
        {
            DetachedCriteria criteria = DetachedCriteria.forClass(CmCampaignActivitySummary.class);
            criteria.add(Restrictions.eq("actionId", summary.getActionId()));
            criteria.add(Restrictions.eq("campaignNo", summary.getCampaignNo()));
            criteria.add(Restrictions.eq("summaryType", summary.getSummaryType()));
            criteria.add(Restrictions.eq("sts", CmCampaignActivitySummary.STS_ACTIVATE));
            CmCampaignActivitySummary activitySummary = repository.findUniqueObjectByCriteria(criteria);
            if (ValidateUtil.isEmpty(activitySummary))
            {
                summary.setCreateDate(DateTimeUtil.getNow());
                summary.setId(SequenceUtil.getSequence(CmCampaignActivitySummary.ACTIVITY_SUMMARY_SEQ));
                repository.saveObject(summary);
            }
            else
            {
                repository.executeByNativeSql("UPDATE meo.cm_campaign_activity_summary t SET t.SUMMARY_VALUE=t.SUMMARY_VALUE+"
                        + summary.getSummaryValue()+ " WHERE t.ID=?", summary.getId());
            }
            
        }
        
    }
    
    public void saveRewardActivity(CmRewardActivity rewardActivity)
    {
        if (ValidateUtil.isNotEmpty(rewardActivity))
        {
            rewardActivity.setDoneTime(DateTimeUtil.getNow());
            rewardActivity.setRewardId(SequenceUtil.getSequence(CmRewardActivity.REWARD_ACTIVITY_SEQ));
            repository.saveObject(rewardActivity);
        }
        
    }
    
    @Override
    public Integer getCountParticipantOfCampaignAppDownload(final String campaignNo, final Long custId, final String actionId)
    {
        List<Object> vs = (List<Object>) repository.findByNamedQuery("meo.activity.getCountParticipantOfCampaignAppDownload",
                campaignNo, custId, actionId);
        if (ValidateUtil.isNotEmpty(vs))
        {
            Integer participant = null;
            if (ValidateUtil.isNotNull(vs.get(0))|| ValidateUtil.isNotEmpty(vs.get(0)))
            {
                participant = new Integer(vs.get(0).toString());
            }
            return participant;
        }
        
        return null;
    }
    
    @Override
    public void createActivityRecordHis(CmActivityRecordHis record)
    {
        record.setDoneTime(DateTimeUtil.getNow());
        record.setHisId(SequenceUtil.getSequence(CmActivityRecordHis.ACTIVITY_RECORD_HIS_ID_SEQ));
        repository.saveObject(record);
        
    }
    
    @Override
    public void saveCampaignActivity(CmCampaignActivity campaignActivity)
    {
        if (ValidateUtil.isNotEmpty(campaignActivity))
        {
            campaignActivity.setDoneTime(DateTimeUtil.getNow());
            campaignActivity.setCampaignActivityId(SequenceUtil.getSequence(CmCampaignActivity.SEQ_CAMPAIGN_ACTIVITY_ID));
            repository.saveObject(campaignActivity);
        }
    }
    
    @Override
    public CmActivityRecordHis getCmActivityRecordHis(Long activityId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CmActivityRecordHis.class);
        criteria.add(Restrictions.eq("activityId", activityId));
        return (CmActivityRecordHis) repository.findUniqueObjectByCriteria(criteria);
    }
    
    @Override
    public CmCampaignActivity getCmCampaignActivityByCodition(String campaignNo, Long custId, String actionId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CmCampaignActivity.class);
        criteria.add(Restrictions.eq("campaignNo", campaignNo));
        criteria.add(Restrictions.eq("custId", custId));
        criteria.add(Restrictions.eq("actionId", actionId));
        List<CmCampaignActivity> list = (List<CmCampaignActivity>) repository.findByCriteria(criteria);
        return ValidateUtil.isNotEmpty(list) ? list.get(0) : null;
    }
    
}
