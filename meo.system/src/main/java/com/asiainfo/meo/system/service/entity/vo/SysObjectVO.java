package com.asiainfo.meo.system.service.entity.vo;

public class SysObjectVO
{
    private Long objectId;
    private Integer objectType;
    private Long operatorId;
    public Long getObjectId()
    {
        return objectId;
    }
    public void setObjectId(Long objectId)
    {
        this.objectId = objectId;
    }
    
    public Integer getObjectType()
    {
        return objectType;
    }
    public void setObjectType(Integer objectType)
    {
        this.objectType = objectType;
    }
    public Long getOperatorId()
    {
        return operatorId;
    }
    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
    }
    
}
