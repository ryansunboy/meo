package com.asiainfo.meo.campaign.task.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignTaskDetail;
import com.asiainfo.meo.campaign.task.app.repository.CampaignTaskDetailRepository;
import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;

public class CampaignTaskDetailRepositoryImpl implements CampaignTaskDetailRepository
{
    @Resource
    private HibernateRepository hibernateRepository;
    
    private static final String SEQ_CAMPAIGN_TASK_DETAIL_ID = "SEQ_CAMPAIGN_TASK_DETAIL_ID";
    
    @Override
    public CampaignTaskDetail addCampaignTaskDetail(CampaignTaskDetail campaignTaskDetail)
    {
        Long detailId = ServiceLocatorFactory.getService(Sequence.class).next(SEQ_CAMPAIGN_TASK_DETAIL_ID);
        campaignTaskDetail.setDetailId(detailId);
        campaignTaskDetail.setCreateDate(DateTimeUtil.getNow());
        campaignTaskDetail.setSts(CampaignTaskDetail.STS_VALID);
        hibernateRepository.saveObject(campaignTaskDetail);
        return getCampaignTaskDetailByDetailId(detailId);
    }
    
    @Override
    public CampaignTaskDetail updateCampaignTaskDetail(CampaignTaskDetail campaignTaskDetail)
    {
        hibernateRepository.updateObject(campaignTaskDetail);
        campaignTaskDetail.setModifyDate(DateTimeUtil.getNow());
        return getCampaignTaskDetailByDetailId(campaignTaskDetail.getDetailId());
    }
    
    @Override
    public void deleteCampaignTaskDetailByDetailId(Long detailId)
    {
        CampaignTaskDetail campaignTaskDetail = getCampaignTaskDetailByDetailId(detailId);
        hibernateRepository.deleteObject(campaignTaskDetail);
    }
    
    @Override
    public CampaignTaskDetail getCampaignTaskDetailByDetailId(Long detailId)
    {
        return hibernateRepository.get(CampaignTaskDetail.class, detailId);
    }
    
    @Override
    public List<CampaignTaskDetail> getCampaignTaskDetailByCustIdAndCampaignNo(Long custId, String campaignNo)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignTaskDetail.class);
        criteria.add(Restrictions.eq("sts", CampaignTaskDetail.STS_VALID));
        criteria.add(Restrictions.eq("custId", custId));
        criteria.add(Restrictions.eq("campaignNo", campaignNo));
        return (List<CampaignTaskDetail>) hibernateRepository.findByCriteria(criteria);
    }
    
}
