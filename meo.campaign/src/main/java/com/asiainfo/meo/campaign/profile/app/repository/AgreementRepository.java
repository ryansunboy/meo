package com.asiainfo.meo.campaign.profile.app.repository;

import java.util.List;

import com.asiainfo.meo.campaign.profile.app.model.entity.Agreement;

public interface AgreementRepository
{
    
    /**
     * 
     * @Description: 获得Agreement定义信息
     * @Description: get all agreement info if agreementId is not null   
     * @author zhoujj
     * @param agreementId
     * @return
     */
    public List<Agreement> getAgreementInfo(Long agreementId);
}
