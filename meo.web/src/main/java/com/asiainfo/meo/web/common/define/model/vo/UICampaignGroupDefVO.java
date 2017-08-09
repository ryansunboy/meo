package com.asiainfo.meo.web.common.define.model.vo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

public class UICampaignGroupDefVO
{
    @Valid
    @NotNull(groups = {Insert.class })
    private String     groupCode;
    
    @Valid
    @NotEmpty(groups = {Insert.class })
    private String     groupName;
    
    private Integer    groupType;
    
    @Valid
    @NotEmpty(groups = {Insert.class, Update.class})
    private List<Long> campaignTypeIds;
    
    @Valid
    @NotNull(groups={Update.class})
    private Long       groupId;
    
    private Long       operatorId;
    
    public Integer getGroupType()
    {
        return groupType;
    }
    
    public void setGroupType(Integer groupType)
    {
        this.groupType = groupType;
    }
    
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