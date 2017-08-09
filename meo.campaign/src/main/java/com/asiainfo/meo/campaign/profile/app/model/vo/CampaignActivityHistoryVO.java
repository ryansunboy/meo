package com.asiainfo.meo.campaign.profile.app.model.vo;

public class CampaignActivityHistoryVO
{
    private Long rewardId;
    private Long actionId;
    private Long doneTime;
    private Integer rewardType;
    private Integer rewardValue;
    private Integer rewardUnitId;
    private String rewardUnit;
    private String actionName;
    private Long campaignId;
    private String campaignName;
    private String campaignNo;
    private Long activityId;

    public Long getActivityId()
    {
        return activityId;
    }
    public void setActivityId(Long activityId)
    {
        this.activityId = activityId;
    }
    public Long getRewardId()
    {
        return rewardId;
    }
    public void setRewardId(Long rewardId)
    {
        this.rewardId = rewardId;
    }
    public Long getActionId()
    {
        return actionId;
    }
    public void setActionId(Long actionId)
    {
        this.actionId = actionId;
    }
    public Long getDoneTime()
    {
        return doneTime;
    }
    public void setDoneTime(Long doneTime)
    {
        this.doneTime = doneTime;
    }
    public Integer getRewardType()
    {
        return rewardType;
    }
    public void setRewardType(Integer rewardType)
    {
        this.rewardType = rewardType;
    }
    public Integer getRewardValue()
    {
        return rewardValue;
    }
    public void setRewardValue(Integer rewardValue)
    {
        this.rewardValue = rewardValue;
    }
    public Integer getRewardUnitId()
    {
        return rewardUnitId;
    }
    public void setRewardUnitId(Integer rewardUnitId)
    {
        this.rewardUnitId = rewardUnitId;
    }
    public String getRewardUnit()
    {
        return rewardUnit;
    }
    public void setRewardUnit(String rewardUnit)
    {
        this.rewardUnit = rewardUnit;
    }
    public String getActionName()
    {
        return actionName;
    }
    public void setActionName(String actionName)
    {
        this.actionName = actionName;
    }
    public Long getCampaignId()
    {
        return campaignId;
    }
    public void setCampaignId(Long campaignId)
    {
        this.campaignId = campaignId;
    }
    public String getCampaignName()
    {
        return campaignName;
    }
    public void setCampaignName(String campaignName)
    {
        this.campaignName = campaignName;
    }
    public String getCampaignNo()
    {
        return campaignNo;
    }
    public void setCampaignNo(String campaignNo)
    {
        this.campaignNo = campaignNo;
    }
}
