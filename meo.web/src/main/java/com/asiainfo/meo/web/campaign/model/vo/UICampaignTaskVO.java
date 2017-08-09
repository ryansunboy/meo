package com.asiainfo.meo.web.campaign.model.vo;

import java.util.List;

import javax.validation.constraints.NotNull;

public class UICampaignTaskVO
{
    private Long                  taskId;
    
    @NotNull
    private Long                  actionId;
    
    private String                actionName;
    
    private List<UITaskParamVO>   taskParamList;
    
    private List<UITriggerTaskVO> triggerTaskList;
    
    private Long                  parentTaskId;
    
    private Long                  parentActionId;
    
    //1 means joined the campaignTask ,0 means have not joined the campaignTask
    private Long                  joinStatus;
    
    public static final Long JOINED_STS = 1L;
    
    public static final Long NOT_JOINED_STS = 0L;
    
    public Long getTaskId()
    {
        return taskId;
    }
    
    public void setTaskId(Long taskId)
    {
        this.taskId = taskId;
    }
    
    public Long getActionId()
    {
        return actionId;
    }
    
    public void setActionId(Long actionId)
    {
        this.actionId = actionId;
    }
    
    public String getActionName()
    {
        return actionName;
    }
    
    public void setActionName(String actionName)
    {
        this.actionName = actionName;
    }
    
    public List<UITaskParamVO> getTaskParamList()
    {
        return taskParamList;
    }
    
    public void setTaskParamList(List<UITaskParamVO> taskParamList)
    {
        this.taskParamList = taskParamList;
    }
    
    public List<UITriggerTaskVO> getTriggerTaskList()
    {
        return triggerTaskList;
    }
    
    public void setTriggerTaskList(List<UITriggerTaskVO> triggerTaskList)
    {
        this.triggerTaskList = triggerTaskList;
    }
    
    public Long getParentTaskId()
    {
        return parentTaskId;
    }
    
    public void setParentTaskId(Long parentTaskId)
    {
        this.parentTaskId = parentTaskId;
    }

    public Long getParentActionId()
    {
        return parentActionId;
    }

    public void setParentActionId(Long parentActionId)
    {
        this.parentActionId = parentActionId;
    }

    public Long getJoinStatus()
    {
        return joinStatus;
    }

    public void setJoinStatus(Long joinStatus)
    {
        this.joinStatus = joinStatus;
    }
    
}
