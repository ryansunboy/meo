package com.asiainfo.meo.activity.business.model.entity;

import java.sql.Timestamp;

public class CmCampaignActivity implements java.io.Serializable
{

    /**                                                                           
      * @Fields serialVersionUID                                                                                                                                                                                                                                 
      */
    private static final long serialVersionUID = -2632604155768059766L;
    public static final String SEQ_CAMPAIGN_ACTIVITY_ID = "SEQ_CAMPAIGN_ACTIVITY_ID";
    private long campaignActivityId;
    private Long activityId;
    private String campaignNo;
    private Timestamp doneTime;
    private Long custId;
    private String actionId;
    
    public CmCampaignActivity() {        
    }
    
    public CmCampaignActivity(final long campaignActivityId) {
        this.campaignActivityId = campaignActivityId;
    }
    
    public CmCampaignActivity(final long campaignActivityId,
                              final Long activityId,
                              final String campaignNo,
                              final Timestamp doneTime,
                              final Long custId,
                              final String actionId) {
        this.campaignActivityId = campaignActivityId;
        this.activityId = activityId;
        this.campaignNo = campaignNo;
        this.doneTime = doneTime;
        this.custId = custId;
        this.actionId = actionId;
    }

    public long getCampaignActivityId()
    {
        return campaignActivityId;
    }

    public void setCampaignActivityId(long campaignActivityId)
    {
        this.campaignActivityId = campaignActivityId;
    }

    public Long getActivityId()
    {
        return activityId;
    }

    public void setActivityId(Long activityId)
    {
        this.activityId = activityId;
    }

    public String getCampaignNo()
    {
        return campaignNo;
    }

    public void setCampaignNo(String campaignNo)
    {
        this.campaignNo = campaignNo;
    }

    public Timestamp getDoneTime()
    {
        return doneTime;
    }

    public void setDoneTime(Timestamp doneTime)
    {
        this.doneTime = doneTime;
    }

    public Long getCustId()
    {
        return custId;
    }

    public void setCustId(Long custId)
    {
        this.custId = custId;
    }

    public String getActionId()
    {
        return actionId;
    }

    public void setActionId(String actionId)
    {
        this.actionId = actionId;
    }   

}
