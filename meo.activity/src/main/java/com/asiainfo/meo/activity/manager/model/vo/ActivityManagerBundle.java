package com.asiainfo.meo.activity.manager.model.vo;

import javax.validation.constraints.NotNull;

public class ActivityManagerBundle
{
    @NotNull
    private Long custId;
    @NotNull
    private Long actionId;
    private Long activityId;
    private Long taskId;
    private Long entityId;
    private Integer entityType;
    public static final Integer ENTITY_TYPE_CAMPAIGN = 1;
    
    public ActivityManagerBundle(Long custId, Long actionId,Long entityId,Integer entityType)
    {
       this.custId=custId;
       this.actionId=actionId;
       this.entityId=entityId;
    }
    public ActivityManagerBundle()
    {
        // TODO Auto-generated constructor stub
    }
    public Long getCustId()
    {
        return custId;
    }
    public void setCustId(Long custId)
    {
        this.custId = custId;
    }
    public Long getActionId()
    {
        return actionId;
    }
    public void setActionId(Long actionId)
    {
        this.actionId = actionId;
    }
    public Long getActivityId()
    {
        return activityId;
    }
    public void setActivityId(Long activityId)
    {
        this.activityId = activityId;
    }
    public Long getTaskId()
    {
        return taskId;
    }
    public void setTaskId(Long taskId)
    {
        this.taskId = taskId;
    }
    public Long getEntityId()
    {
        return entityId;
    }
    public void setEntityId(Long entityId)
    {
        this.entityId = entityId;
    }
    public Integer getEntityType()
    {
        return entityType;
    }
    public void setEntityType(Integer entityType)
    {
        this.entityType = entityType;
    }
    
  
   
    
}
