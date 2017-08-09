package com.asiainfo.meo.web.campaign.model.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;


public class UIReNewCampaignCommonVO
{
    @NotNull
    private Long                    campaignId;
    
    @NotNull
    private Long                    endDate;
    
    @NotNull
    private Long                    validDate;
     
    private Integer                 participant;
     
    private List<UICampaignPriceVO> campaignPrices = new ArrayList<UICampaignPriceVO>();

    public Long getCampaignId()
    {
        return campaignId;
    }

    public void setCampaignId(Long campaignId)
    {
        this.campaignId = campaignId;
    }

    public Long getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Long endDate)
    {
        this.endDate = endDate;
    }

    public Long getValidDate()
    {
        return validDate;
    }

    public void setValidDate(Long validDate)
    {
        this.validDate = validDate;
    }

    public Integer getParticipant()
    {
        return participant;
    }

    public void setParticipant(Integer participant)
    {
        this.participant = participant;
    }

    public List<UICampaignPriceVO> getCampaignPrices()
    {
        return campaignPrices;
    }

    public void setCampaignPrices(List<UICampaignPriceVO> campaignPrices)
    {
        this.campaignPrices = campaignPrices;
    }
}
