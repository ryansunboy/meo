package com.asiainfo.meo.campaign.profile.app.model.entity;

// Generated 2015-4-27 11:29:06 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

/**
 * CmCampaignPriceRel generated by hbm2java
 */
public class CampaignPriceRel implements java.io.Serializable
{
    
    private long            id;
    
    private Long            campaignId;
    
    private long            priceId;
    
    private Long            contractId;
    
    private Timestamp       validDate;
    
    private Timestamp       expiredDate;
    
    private int             sts;
    
    private Timestamp       modifyDate;
    
    /** price status constant */
    public static final int STS_VALID   = 1;
    
    /** price status constant */
    public static final int STS_INVALID = 2;
    
    public long getId()
    {
        return this.id;
    }
    
    public void setId(long id)
    {
        this.id = id;
    }
    
    public Long getCampaignId()
    {
        return this.campaignId;
    }
    
    public void setCampaignId(Long campaignId)
    {
        this.campaignId = campaignId;
    }
    
    public long getPriceId()
    {
        return this.priceId;
    }
    
    public void setPriceId(long priceId)
    {
        this.priceId = priceId;
    }
    
    public Long getContractId()
    {
        return this.contractId;
    }
    
    public void setContractId(Long contractId)
    {
        this.contractId = contractId;
    }
    
    public Timestamp getValidDate()
    {
        return this.validDate;
    }
    
    public void setValidDate(Timestamp validDate)
    {
        this.validDate = validDate;
    }
    
    public Timestamp getExpiredDate()
    {
        return this.expiredDate;
    }
    
    public void setExpiredDate(Timestamp expiredDate)
    {
        this.expiredDate = expiredDate;
    }
    
    public int getSts()
    {
        return this.sts;
    }
    
    public void setSts(int sts)
    {
        this.sts = sts;
    }
    
    public Timestamp getModifyDate()
    {
        return this.modifyDate;
    }
    
    public void setModifyDate(Timestamp modifyDate)
    {
        this.modifyDate = modifyDate;
    }
    
}
