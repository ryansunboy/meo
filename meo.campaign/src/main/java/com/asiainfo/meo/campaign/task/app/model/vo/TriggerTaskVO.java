package com.asiainfo.meo.campaign.task.app.model.vo;

import java.util.List;

public class TriggerTaskVO
{
    private Long              taskId;
    
    private Long              actionId;
    
    private String            actionName;
    
    private List<TaskParamVO> taskParamList;
    
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
    
}
