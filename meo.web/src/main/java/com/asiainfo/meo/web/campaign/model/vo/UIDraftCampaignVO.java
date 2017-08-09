package com.asiainfo.meo.web.campaign.model.vo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class UIDraftCampaignVO
{
    @Valid
    @NotNull
    private UICampaignCommonVO       campaignCommon;
    
    @Valid
    @NotNull
    private List<UICampaignDetailVO> campaignDetails;
    
    @Valid
    @NotNull
    private List<UICampaignTaskVO>   campaignTasks;
    
    public UICampaignCommonVO getCampaignCommon()
    {
        return campaignCommon;
    }
    
    public void setCampaignCommon(UICampaignCommonVO campaignCommon)
    {
        this.campaignCommon = campaignCommon;
    }
    
    public List<UICampaignDetailVO> getCampaignDetails()
    {
        return campaignDetails;
    }
    
    public void setCampaignDetails(List<UICampaignDetailVO> campaignDetails)
    {
        this.campaignDetails = campaignDetails;
    }
    
    public List<UICampaignTaskVO> getCampaignTasks()
    {
        return campaignTasks;
    }
    
    public void setCampaignTasks(List<UICampaignTaskVO> campaignTasks)
    {
        this.campaignTasks = campaignTasks;
    }
    
}
