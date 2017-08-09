package com.asiainfo.meo.campaign.profile.app.repository.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.campaign.profile.app.model.entity.Campaign;
import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignActivitySummary;
import com.asiainfo.meo.campaign.profile.app.model.vo.CampaignBasicVO;
import com.asiainfo.meo.campaign.profile.app.repository.CampaignRepository;
import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;

public class CampaignRepositoryImpl implements CampaignRepository
{
    
    private final static String SEQ_CAMPAIGN_ID = "SEQ_CAMPAIGN_ID";
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    private DetachedCriteria createCampaignDetachedCriteria(List<Integer> campaignTypes,Integer categoryId, Long[] campaignIds, Timestamp currentTime)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Campaign.class);
        criteria.add(Restrictions.eq("campaignSts", Campaign.STS_ACTIVE));
        criteria.add(Restrictions.eq("sts", Campaign.STS_VALID));
        criteria.add(Restrictions.le("validDate", currentTime));
        criteria.add(Restrictions.ge("expiredDate", currentTime));
        if (ValidateUtil.isNotNull(categoryId))
        {
            criteria.add((Restrictions.eq("categoryId", categoryId)));
        }
        if (ValidateUtil.isNotEmpty(campaignIds))
        {
            criteria.add(Restrictions.in("campaignId", campaignIds));
        }
        criteria.addOrder(Order.desc("campaignBeginDate"));
        if(ValidateUtil.isNotEmpty(campaignTypes))
        {
            criteria.add(Restrictions.in("campaignType", campaignTypes));
        }
        return criteria;
    }
    
    public List<Campaign> getCampaignList(Timestamp currentTime,List<Integer> campaignTypes,Integer categoryId, Long[] campaignIds, Integer pageSize, Integer pageNo)
    {
        DetachedCriteria criteria = createCampaignDetachedCriteria(campaignTypes, categoryId, campaignIds, currentTime);
        return (List<Campaign>) hibernateRepository.findByCriteria(criteria, pageSize* (pageNo- 1), pageSize);
    }
    
    public int getCampaignListTotalSize(Timestamp currentTime,List<Integer> campaignTypes,Integer categoryId,Long[] campaignIds)
    {
        DetachedCriteria criteria = createCampaignDetachedCriteria(campaignTypes, categoryId, campaignIds, currentTime);
        List<Campaign> list = (List<Campaign>) hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isNotEmpty(list)?list.size():0; 
    }
    
    private DetachedCriteria createDetachedCriteria(CampaignBasicVO capaignCondition)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Campaign.class);
        if (ValidateUtil.isNotNull(capaignCondition))
        {
            if (ValidateUtil.isNotEmpty(capaignCondition.getCampaignType()))
            {
                criteria.add(Restrictions.eq("campaignType", capaignCondition.getCampaignType()));
            }
            if (ValidateUtil.isNotEmpty(capaignCondition.getCampaignNo()))
            {
                criteria.add(Restrictions.like("campaignNo", "%"+ capaignCondition.getCampaignNo()+ "%"));
            }
            if (ValidateUtil.isNotEmpty(capaignCondition.getValidDate()))
            {
                criteria.add(Restrictions.ge("validDate", capaignCondition.getValidDate()));
            }
            if (ValidateUtil.isNotEmpty(capaignCondition.getExpiredDate()))
            {
                criteria.add(Restrictions.le("expiredDate", capaignCondition.getExpiredDate()));
            }
            if (ValidateUtil.isNotEmpty(capaignCondition.getCampaignName()))
            {
                criteria.add(Restrictions.like("campaignName", "%"+ capaignCondition.getCampaignName()+ "%"));
            }
            if (ValidateUtil.isNotEmpty(capaignCondition.getCampaignStsId()))
            {
                criteria.add(Restrictions.le("campaignSts", capaignCondition.getCampaignStsId()));
            }
        }
        //criteria.setProjection(Projections.distinct(Projections.property("campaignNo")));
        criteria.addOrder(Order.desc("campaignId"));
        criteria.addOrder(Order.desc("campaignNo"));
        return criteria;
    }
    
    public int getCampaignBasicInfoTotalSize(CampaignBasicVO capaignCondition)
    {
        DetachedCriteria criteria = createDetachedCriteria(capaignCondition);
        List<Campaign> campaignList = (List<Campaign>) hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(campaignList)?0:campaignList.size();
    }
    
    public List<Campaign> getCampaignBasicInfo(CampaignBasicVO capaignCondition, Integer pageSize, Integer pageNo)
    {
        DetachedCriteria criteria = createDetachedCriteria(capaignCondition);
        return (List<Campaign>) hibernateRepository.findByCriteria(criteria, pageSize* (pageNo- 1),
                pageSize);
    }
    
    /**
     * @Description: 通过campaignId获得campaign信息
     * @Description: get campaign by campaign id
     * @author zhaozx
     * @param campaignId
     * @return
     */
    public Campaign getCampaignByCampaignId(long campaignId)
    {
        return hibernateRepository.get(Campaign.class, campaignId);
    }
    
    public Campaign getActiveCampaignByCampaignNo(String campaignNo)
    {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        DetachedCriteria criteria = DetachedCriteria.forClass(Campaign.class);
        criteria.add(Restrictions.eq("campaignNo", campaignNo));
        criteria.add(Restrictions.eq("campaignSts", Campaign.STS_ACTIVE));
        criteria.add(Restrictions.le("validDate", currentTime));
        criteria.add(Restrictions.ge("expiredDate", currentTime));
        List<Campaign> list = (List<Campaign>) hibernateRepository.findByCriteria(criteria);
        Campaign campaign = ValidateUtil.isEmpty(list) ? null : list.get(0);
        if (ValidateUtil.isNull(campaign))
        {
            DetachedCriteria criteria2 = DetachedCriteria.forClass(Campaign.class);
            criteria2.add(Restrictions.eq("campaignNo", campaignNo));
            criteria2.add(Restrictions.eq("campaignSts", Campaign.STS_ACTIVE));
            criteria2.add(Restrictions.ge("validDate", currentTime));
            List<Campaign> list2 = (List<Campaign>) hibernateRepository.findByCriteria(criteria2);
            campaign = ValidateUtil.isEmpty(list2) ? null : list2.get(0);
        }
        return campaign;
    }
    
    public Campaign addCampaign(Campaign campaign)
    {
        Long campaignId = ServiceLocatorFactory.getService(Sequence.class).next(SEQ_CAMPAIGN_ID);
        campaign.setCampaignId(campaignId);
        String campaignNo = campaign.getCampaignNo();
        if (ValidateUtil.isEmpty(campaignNo))
        {
            campaignNo = campaignId.toString();
            campaign.setCampaignNo(campaignNo);
        }
        String version = getNextVersion(campaignNo);
        campaign.setVersion(version);
        hibernateRepository.saveObject(campaign);
        return getCampaignByCampaignId(campaign.getCampaignId());        
    }
    
    public Campaign updateCampaign(Campaign campaign)
    {
        hibernateRepository.updateObject(campaign);
        return getCampaignByCampaignId(campaign.getCampaignId());
    }
    
    @Override
    public Campaign getCampaignByCampaignNoAndCampaignSts(String campaignNo, int campaignSts)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Campaign.class);
        criteria.add(Restrictions.eq("campaignNo", campaignNo));
        criteria.add(Restrictions.eq("campaignSts", campaignSts));
        criteria.add(Restrictions.eq("sts", Campaign.STS_VALID));
        List<Campaign> list = (List<Campaign>) hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(list) ? null : list.get(0);
    }
    
    @Override
    public List<Campaign> getCampaignListByCampaignNo(String campaignNo)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Campaign.class);
        criteria.add(Restrictions.eq("campaignNo", campaignNo));
        criteria.add(Restrictions.eq("sts", Campaign.STS_VALID));
        criteria.add(Restrictions.eq("isEdit", Campaign.STS_ISEDIT));
        return (List<Campaign>) hibernateRepository.findByCriteria(criteria);
    }
    
    public String getNextVersion(String campaignNo)
    {
        List<Object> vs = (List<Object>) hibernateRepository.findByNamedQuery("meo.campaign.getNextVersion", campaignNo);
        if (ValidateUtil.isNotEmpty(vs))
        {
            Object version = vs.get(0);
            if (ValidateUtil.isNull(version)|| ValidateUtil.isEmpty(version))
            {
                return "1";
            }
            return (Integer.valueOf(version.toString())+ 1)+ "";
        }
        else
        {
            return "1";
        }
    }
    
    @Override
    public List<Campaign> getActiveVersionCampaignByCampaignNo(String campaignNo)
    {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        DetachedCriteria criteria = DetachedCriteria.forClass(Campaign.class);
        criteria.add(Restrictions.eq("campaignNo", campaignNo));
        criteria.add(Restrictions.eq("campaignSts", Campaign.STS_ACTIVE));
        criteria.add(Restrictions.eq("sts", Campaign.STS_VALID));
        criteria.add(Restrictions.ge("expiredDate", currentTime));
        return (List<Campaign>) hibernateRepository.findByCriteria(criteria);
    }
    
    @Override
    public CampaignActivitySummary getCampaignSummaryByCampaignNoAndParticipantType(String campaignNo)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CampaignActivitySummary.class);
        criteria.add(Restrictions.eq("campaignNo", campaignNo));
        criteria.add(Restrictions.eq("summaryType", CampaignActivitySummary.PARTICIPANT_TYPE));
        return (CampaignActivitySummary) hibernateRepository.findUniqueObjectByCriteria(criteria);
    }
    
    @Override
    public Campaign getPreviousVersionCampaign(String campaignNo, String version)
    {
        version = (Integer.valueOf(version)- 1+ "");
        DetachedCriteria criteria = DetachedCriteria.forClass(Campaign.class);
        criteria.add(Restrictions.eq("campaignNo", campaignNo));
        criteria.add(Restrictions.eq("campaignSts", Campaign.STS_ACTIVE));
        criteria.add(Restrictions.eq("sts", Campaign.STS_VALID));
        criteria.add(Restrictions.eq("version", version));
        List<Campaign> list = (List<Campaign>) hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(list) ? null : list.get(0);
    }
    
    @Override
    public void updateCampaignEndDate(String campaignNo, Timestamp campaignEndDate)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Campaign.class);
        criteria.add(Restrictions.eq("campaignNo", campaignNo));
        List<Campaign> list = (List<Campaign>) hibernateRepository.findByCriteria(criteria);
        if (ValidateUtil.isNotEmpty(list))
        {
            for (Campaign cp : list)
            {
                cp.setCampaignEndDate(campaignEndDate);
                hibernateRepository.updateObject(cp);
            }
        }
    }
    
    @Override
    public List<Campaign> getCampaignListByCampaignTypesAndCategoryIds(List<Integer> campaignTypes, List<Integer> categoryIds)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Campaign.class);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        if (ValidateUtil.isNotEmpty(categoryIds))
        {
            criteria.add(Restrictions.in("categoryId", categoryIds));
        }
        if (ValidateUtil.isNotEmpty(campaignTypes))
        {
            criteria.add(Restrictions.in("campaignType", campaignTypes));
        }
        criteria.add(Restrictions.eq("campaignSts", Campaign.STS_ACTIVE));
        criteria.add(Restrictions.eq("sts", Campaign.STS_VALID));
        criteria.add(Restrictions.le("validDate", currentTime));
        criteria.add(Restrictions.ge("expiredDate", currentTime));
        return (List<Campaign>) hibernateRepository.findByCriteria(criteria);
    }

    @Override
    public List<Campaign> getCampaignList(Long currentTime)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Campaign.class);
        criteria.add(Restrictions.le("expiredDate", new Timestamp(currentTime)));
        criteria.add(Restrictions.not(Expression.eq("campaignSts", Campaign.STS_EXPIRED)));
        return (List<Campaign>) hibernateRepository.findByCriteria(criteria);
    }
    
    public List<Object> getAppDownLoad(Long custId, Integer pageNo, Integer pageSize)
    {
        List<Object> vs = (List<Object>) hibernateRepository.findByNamedQuery("meo.campaign.getAppDownLoad", custId, (pageNo -1) * pageSize , pageSize);
        return vs;
    }
    
    public Integer getAppDownLoadTotalSize(Long custId)
    {
        List<Object> vs = (List<Object>) hibernateRepository.findByNamedQuery("meo.campaign.getAppDownLoadTotalSize", custId);
        if (ValidateUtil.isNotEmpty(vs))
        {
            Object totalSize = vs.get(0);
            if (ValidateUtil.isNull(totalSize)|| ValidateUtil.isEmpty(totalSize))
            {
                return 0;
            }
            return Integer.valueOf(totalSize.toString());
        }
        else
        {
            return 0;
        }
    }
    
}
