package com.asiainfo.meo.web.campaign.model.vo;

import java.util.List;

public class UICampaignCategoryVO
{
    
    private Integer categoryId;
    
    private List<UICampaignVO> campaigns;

    public Integer getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId)
    {
        this.categoryId = categoryId;
    }

    public List<UICampaignVO> getCampaigns()
    {
        return campaigns;
    }

    public void setCampaigns(List<UICampaignVO> campaigns)
    {
        this.campaigns = campaigns;
    }
    
}
