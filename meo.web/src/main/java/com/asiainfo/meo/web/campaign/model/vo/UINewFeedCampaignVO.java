package com.asiainfo.meo.web.campaign.model.vo;

import java.util.List;

public class UINewFeedCampaignVO
{
    private UICampaignBasicVO        campaignBasic;
    
    private List<UICampaignDetailVO> campaignDetails;
    
    
    public UICampaignBasicVO getCampaignBasic()
    {
        return campaignBasic;
    }
    
    public void setCampaignBasic(UICampaignBasicVO campaignBasic)
    {
        this.campaignBasic = campaignBasic;
    }
    
    public List<UICampaignDetailVO> getCampaignDetails()
    {
        return campaignDetails;
    }
    
    public void setCampaignDetails(List<UICampaignDetailVO> campaignDetails)
    {
        this.campaignDetails = campaignDetails;
    }
}
