package com.asiainfo.meo.web.activity.model.vo;

import javax.validation.constraints.NotNull;

public class UIJoinCampaignVO
{
    @NotNull
    private Long campaignId;
    @NotNull
    private Long actionId;
    public Long getCampaignId() 
    {
        return campaignId;
    }
    public void setCampaignId(Long campaignId)
    {
        this.campaignId = campaignId;
    }
    public Long getActionId()
    {
        return actionId;
    }
    public void setActionId(Long actionId)
    {
        this.actionId = actionId;
    }
    
    
    
    
}
