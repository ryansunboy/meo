package com.asiainfo.meo.system.common.app.model.vo;

public class ActionDefine
{
    public static final Integer USER_TYPE = 1;
    public static final Integer REWARD_TYPE = 2;
    private String  actionName;
    
    private Long atcionId;
    
    private String  actionDesc;
    
    public String getActionName()
    {
        return actionName;
    }
    
    public void setActionName(String actionName)
    {
        this.actionName = actionName;
    }
    
    public Long getAtcionId()
    {
        return atcionId;
    }

    public void setAtcionId(Long atcionId)
    {
        this.atcionId = atcionId;
    }

    public String getActionDesc()
    {
        return actionDesc;
    }
    
    public void setActionDesc(String actionDesc)
    {
        this.actionDesc = actionDesc;
    }
    
}
