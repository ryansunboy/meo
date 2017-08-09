package com.asiainfo.meo.system.common.app.model.vo;

import java.util.List;

public class EnumGroupDefVO
{
    private Long       groupId;
    
    private String     groupCode;
    
    private String     groupName;
    
    private Integer    groupType;
    
    private List<Long> campaignTypeIds;
    
    private Long       operatorId;
    
    public String getGroupCode()
    {
        return groupCode;
    }
    
    public void setGroupCode(String groupCode)
    {
        this.groupCode = groupCode;
    }
    
    public String getGroupName()
    {
        return groupName;
    }
    
    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }
    
    public Integer getGroupType()
    {
        return groupType;
    }
    
    public void setGroupType(Integer groupType)
    {
        this.groupType = groupType;
    }
    
    public List<Long> getCampaignTypeIds()
    {
        return campaignTypeIds;
    }
    
    public void setCampaignTypeIds(List<Long> campaignTypeIds)
    {
        this.campaignTypeIds = campaignTypeIds;
    }
    
    public Long getOperatorId()
    {
        return operatorId;
    }
    
    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
    }

    public Long getGroupId()
    {
        return groupId;
    }

    public void setGroupId(Long groupId)
    {
        this.groupId = groupId;
    }
    
}