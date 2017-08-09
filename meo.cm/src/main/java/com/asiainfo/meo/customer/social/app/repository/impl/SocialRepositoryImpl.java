package com.asiainfo.meo.customer.social.app.repository.impl;

import javax.annotation.Resource;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.customer.social.app.model.entity.SocialInfo;
import com.asiainfo.meo.customer.social.app.repository.SocialRepository;


public class SocialRepositoryImpl implements SocialRepository
{
    @Resource
    private HibernateRepository hibernateRepository; 
    
    private static final String SEQ_SOCIAL_INFO_ID="SEQ_SOCIAL_INFO_ID";
    
    public void saveSocial(SocialInfo socialInfo)
    {
        Sequence sequence = ServiceLocatorFactory.getService(Sequence.class);
        socialInfo.setId(sequence.next(SEQ_SOCIAL_INFO_ID));
        socialInfo.setCreateDate(DateTimeUtil.getNow());
        hibernateRepository.saveObject(socialInfo);
        
    }
   
}
