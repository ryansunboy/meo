package com.asiainfo.meo.web.common.define.model.vo;

import java.util.List;

public class UICampaignActionVO
{
    private Long                 actionId;
    
    private String               actionName;
    
    private List<UICampaignActionVO> triggerAction;
    
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
    
    public List<UICampaignActionVO> getTriggerAction()
    {
        return triggerAction;
    }
    
    public void setTriggerAction(List<UICampaignActionVO> triggerAction)
    {
        this.triggerAction = triggerAction;
    }
    
}
