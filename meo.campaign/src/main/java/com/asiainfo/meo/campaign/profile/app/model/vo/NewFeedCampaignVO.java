package com.asiainfo.meo.campaign.profile.app.model.vo;

import java.util.List;

public class NewFeedCampaignVO
{
    private CampaignBasicVO  campaignBasic;
    
    private List<CampaignDetailVO> campaignDetails;
    
    public CampaignBasicVO getCampaignBasic()
    {
        return campaignBasic;
    }
    
    public void setCampaignBasic(CampaignBasicVO campaignBasic)
    {
        this.campaignBasic = campaignBasic;
    }

    public List<CampaignDetailVO> getCampaignDetails()
    {
        return campaignDetails;
    }

    public void setCampaignDetails(List<CampaignDetailVO> campaignDetails)
    {
        this.campaignDetails = campaignDetails;
    }
    
}
