package com.asiainfo.meo.campaign.task.app.model.entity;

// Generated 2015-4-20 14:45:47 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

/**
 * CmCampaignQuiz generated by hbm2java
 */
public class CampaignQuiz implements java.io.Serializable
{
    private static final long serialVersionUID = -2058083440083357572L;
    
    private long              quizId;
    
    private String            quizName;
    
    private String            description;
    
    private long              campaignId;
    
    private Timestamp         createDate;
    
    private Timestamp         modifyDate;
    
    private String            quizUrl;
    
    private String            findAnswerUrl;
    
    private int               quesCount;
    
    public long getQuizId()
    {
        return this.quizId;
    }
    
    public void setQuizId(long quizId)
    {
        this.quizId = quizId;
    }
    
    public String getQuizName()
    {
        return this.quizName;
    }
    
    public void setQuizName(String quizName)
    {
        this.quizName = quizName;
    }
    
    public String getDescription()
    {
        return this.description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public long getCampaignId()
    {
        return this.campaignId;
    }
    
    public void setCampaignId(long campaignId)
    {
        this.campaignId = campaignId;
    }
    
    public Timestamp getCreateDate()
    {
        return this.createDate;
    }
    
    public void setCreateDate(Timestamp createDate)
    {
        this.createDate = createDate;
    }
    
    public Timestamp getModifyDate()
    {
        return this.modifyDate;
    }
    
    public void setModifyDate(Timestamp modifyDate)
    {
        this.modifyDate = modifyDate;
    }
    
    public String getQuizUrl()
    {
        return this.quizUrl;
    }
    
    public void setQuizUrl(String quizUrl)
    {
        this.quizUrl = quizUrl;
    }
    
    public String getFindAnswerUrl()
    {
        return this.findAnswerUrl;
    }
    
    public void setFindAnswerUrl(String findAnswerUrl)
    {
        this.findAnswerUrl = findAnswerUrl;
    }
    
    public int getQuesCount()
    {
        return this.quesCount;
    }
    
    public void setQuesCount(int quesCount)
    {
        this.quesCount = quesCount;
    }
    
}
