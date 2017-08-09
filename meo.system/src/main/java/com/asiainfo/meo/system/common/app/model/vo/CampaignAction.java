package com.asiainfo.meo.system.common.app.model.vo;

import java.util.List;

public class CampaignAction
{
    private Long                 actionId;
    
    private String               actionName;
    
    private List<CampaignAction> triggerAction;
    
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
    
    public List<CampaignAction> getTriggerAction()
    {
        return triggerAction;
    }
    
    public void setTriggerAction(List<CampaignAction> triggerAction)
    {
        this.triggerAction = triggerAction;
    }
    
}
