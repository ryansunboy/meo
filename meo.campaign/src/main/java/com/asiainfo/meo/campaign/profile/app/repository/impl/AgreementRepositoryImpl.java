package com.asiainfo.meo.campaign.profile.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.campaign.profile.app.model.entity.Agreement;
import com.asiainfo.meo.campaign.profile.app.model.entity.CampaignPropertyDef;
import com.asiainfo.meo.campaign.profile.app.repository.AgreementRepository;
import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.utils.ValidateUtil;

public class AgreementRepositoryImpl implements AgreementRepository
{
    private final static String SEQ_AGREEMENT_ID = "SEQ_AGREEMENT_ID";
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    /**
     * 
     * @Description: 获得Agreement定义信息
     * @Description: get all agreement info if agreementId is not null   
     * @author zhoujj
     * @param agreementId
     * @return
     */
    public List<Agreement> getAgreementInfo(Long agreementId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Agreement.class);
        if (ValidateUtil.isNotNull(agreementId))
        {
            criteria.add(Restrictions.eq("agreementId", agreementId));
        }
        return (List<Agreement>)hibernateRepository.findByCriteria(criteria);
    }
    
}
