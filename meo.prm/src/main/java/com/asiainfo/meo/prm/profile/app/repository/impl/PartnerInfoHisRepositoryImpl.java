package com.asiainfo.meo.prm.profile.app.repository.impl;

import javax.annotation.Resource;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.prm.profile.app.model.entity.PartnerInfoHis;
import com.asiainfo.meo.prm.profile.app.repository.PartnerInfoHisRepository;

public class PartnerInfoHisRepositoryImpl implements PartnerInfoHisRepository
{
    @Resource
    private HibernateRepository hibernateRepository;
    private static final String SEQ_PARTNER_INFO_HIS_ID = "SEQ_PARTNER_INFO_HIS_ID";
    
    /**
     * @Description: 保存partner历史信息
     * @Description: save partner history
     * @author zhaozx
     * @param partnerInfoHis partnerInfoHis entity
     */
    @Override
    public void savePartnerInfoHis(PartnerInfoHis partnerInfoHis)
    {
        partnerInfoHis.setId(ServiceLocatorFactory.getService(Sequence.class).next(SEQ_PARTNER_INFO_HIS_ID));
        hibernateRepository.saveObject(partnerInfoHis);
    }
    
}
