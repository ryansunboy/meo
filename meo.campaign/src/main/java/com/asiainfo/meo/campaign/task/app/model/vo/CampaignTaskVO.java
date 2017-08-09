package com.asiainfo.meo.campaign.task.app.model.vo;

import java.util.List;

public class CampaignTaskVO
{
    private Long                taskId;
    
    private Long                actionId;
    
    private String              actionName;
    
    private List<TaskParamVO>   taskParamList;
    
    private List<TriggerTaskVO> triggerTaskList;
    
    private Long                parentTaskId;
    
    private Long                parentActionId;
    
    // 1 means joined the campaignTask ,0 means have not joined the campaignTask
    private Long                joinStatus;
    
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
    
    public List<TaskParamVO> getTaskParamList()
    {
        return taskParamList;
    }
    
    public void setTaskParamList(List<TaskParamVO> taskParamList)
    {
        this.taskParamList = taskParamList;
    }
    
    public List<TriggerTaskVO> getTriggerTaskList()
    {
        return triggerTaskList;
    }
    
    public void setTriggerTaskList(List<TriggerTaskVO> triggerTaskList)
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
