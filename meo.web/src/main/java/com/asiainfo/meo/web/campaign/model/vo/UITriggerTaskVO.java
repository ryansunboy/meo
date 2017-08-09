package com.asiainfo.meo.web.campaign.model.vo;

import java.util.List;

import javax.validation.constraints.NotNull;

public class UITriggerTaskVO
{
    private Long                taskId;
    
    @NotNull
    private Long                actionId;
    
    private String              actionName;
    
    @NotNull
    private List<UITaskParamVO> taskParamList;
    
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
}
