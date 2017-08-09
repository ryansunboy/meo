package com.asiainfo.meo.web.campaign.model.vo;

import java.util.ArrayList;
import java.util.List;


public class UICampaignBasicInfoVO
{
    private String campaignNo; 
    
    private List<UICampaignBasicVO> campaignBasicInfo = new ArrayList<UICampaignBasicVO>();

    public String getCampaignNo()
    {
        return campaignNo;
    }

    public void setCampaignNo(String campaignNo)
    {
        this.campaignNo = campaignNo;
    }

    public List<UICampaignBasicVO> getCampaignBasicInfo()
    {
        return campaignBasicInfo;
    }

    public void setCampaignBasicInfo(List<UICampaignBasicVO> campaignBasicInfo)
    {
        this.campaignBasicInfo = campaignBasicInfo;
    }



                   
    
 
}
