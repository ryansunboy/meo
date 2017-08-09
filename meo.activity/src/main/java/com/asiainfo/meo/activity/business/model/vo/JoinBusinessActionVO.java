package com.asiainfo.meo.activity.business.model.vo;

public class JoinBusinessActionVO
{
    private Long entityId;
    
    private Integer entityType;
    
    private Long custId;
    
    private Long actionId;

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
