package com.asiainfo.meo.web.campaign.model.vo;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;


public class UICampaignGroupConditionsVO
{
    @NotEmpty
    private String groupCode;
    
    @Valid
    private List<Integer> categoryList;

    public String getGroupCode()
    {
        return groupCode;
    }

    public void setGroupCode(String groupCode)
    {
        this.groupCode = groupCode;
    }

    public List<Integer> getCategoryList()
    {
        return categoryList;
    }

    public void setCategoryList(List<Integer> categoryList)
    {
        this.categoryList = categoryList;
    }
}
