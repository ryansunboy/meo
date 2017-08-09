package com.asiainfo.meo.prm.profile.app.model.vo;

import com.asiainfo.meo.prm.profile.app.model.entity.Partner;
import com.asiainfo.meo.prm.profile.app.model.entity.PartnerInfo;

/**
 * @Description: partner and partner信息
 * @Description: partner and partner information
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @author zhaozx
 * @Date 2015-3-13 上午11:53:31
 * @version 1.0
 */
public class PartnerProfileVO
{
    private Partner     partner;
    
    private PartnerInfo partnerInfo;
    
    private String      avatarUrl;
    
    private String      registerFileUrl;
    
    private Long        contractId;
    
    private String      contractNo;
    
    private String      partnerTypeDescription;
    
    public String getRegisterFileUrl()
    {
        return registerFileUrl;
    }

    public void setRegisterFileUrl(String registerFileUrl)
    {
        this.registerFileUrl = registerFileUrl;
    }

    public Long getContractId()
    {
        return contractId;
    }
    
    public void setContractId(Long contractId)
    {
        this.contractId = contractId;
    }
    
    public String getContractNo()
    {
        return contractNo;
    }
    
    public void setContractNo(String contractNo)
    {
        this.contractNo = contractNo;
    }
    
    public Partner getPartner()
    {
        return partner;
    }
    
    public void setPartner(Partner partner)
    {
        this.partner = partner;
    }
    
    public PartnerInfo getPartnerInfo()
    {
        return partnerInfo;
    }
    
    public void setPartnerInfo(PartnerInfo partnerInfo)
    {
        this.partnerInfo = partnerInfo;
    }
    
    public String getAvatarUrl()
    {
        return avatarUrl;
    }
    
    public void setAvatarUrl(String avatarUrl)
    {
        this.avatarUrl = avatarUrl;
    }

    public String getPartnerTypeDescription()
    {
        return partnerTypeDescription;
    }

    public void setPartnerTypeDescription(String partnerTypeDescription)
    {
        this.partnerTypeDescription = partnerTypeDescription;
    }
    
}
