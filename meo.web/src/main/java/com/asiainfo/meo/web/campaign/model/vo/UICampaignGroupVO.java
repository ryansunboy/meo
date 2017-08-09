package com.asiainfo.meo.web.campaign.model.vo;

import java.util.List;

public class UICampaignGroupVO
{
    private String groupCode;
    
    private List<UICampaignCategoryVO> campaignCategories;

    public String getGroupCode()
    {
        return groupCode;
    }

    public void setGroupCode(String groupCode)
    {
        this.groupCode = groupCode;
    }

    public List<UICampaignCategoryVO> getCampaignCategories()
    {
        return campaignCategories;
    }

    public void setCampaignCategories(List<UICampaignCategoryVO> campaignCategories)
    {
        this.campaignCategories = campaignCategories;
    }
}
