package com.asiainfo.meo.campaign.profile.app.model.entity;

import java.sql.Timestamp;

public class CampaignTaskDetail implements java.io.Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long              detailId;
    
    private Long              currentTaskId;
    
    private Timestamp         createDate;
    
    private Timestamp         modifyDate;
    
    private String            campaignNo;
    
    private Integer           sts;
    
    private Long              nextTaskId;
    
    private Long              custId;
    
    public static final int   STS_VALID        = 1;
    
    public static final int   STS_INVALID      = 2;
    
    public Long getDetailId()
    {
        return detailId;
    }
    
    public void setDetailId(Long detailId)
    {
        this.detailId = detailId;
    }
    
    public Long getCurrentTaskId()
    {
        return currentTaskId;
    }
    
    public void setCurrentTaskId(Long currentTaskId)
    {
        this.currentTaskId = currentTaskId;
    }
    
    public Timestamp getCreateDate()
    {
        return createDate;
    }
    
    public void setCreateDate(Timestamp createDate)
    {
        this.createDate = createDate;
    }
    
    public Timestamp getModifyDate()
    {
        return modifyDate;
    }
    
    public void setModifyDate(Timestamp modifyDate)
    {
        this.modifyDate = modifyDate;
    }
    
    public String getCampaignNo()
    {
        return campaignNo;
    }
    
    public void setCampaignNo(String campaignNo)
    {
        this.campaignNo = campaignNo;
    }
    
    public Integer getSts()
    {
        return sts;
    }
    
    public void setSts(Integer sts)
    {
        this.sts = sts;
    }
    
    public Long getNextTaskId()
    {
        return nextTaskId;
    }
    
    public void setNextTaskId(Long nextTaskId)
    {
        this.nextTaskId = nextTaskId;
    }
    
    public Long getCustId()
    {
        return custId;
    }
    
    public void setCustId(Long custId)
    {
        this.custId = custId;
    }
    
}