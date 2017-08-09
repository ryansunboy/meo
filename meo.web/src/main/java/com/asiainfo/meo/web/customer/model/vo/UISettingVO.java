package com.asiainfo.meo.web.customer.model.vo;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

public class UISettingVO
{
    @NotEmpty
    @Valid
    List<UICustSettingInfoVO> settingInfos;
    
    public List<UICustSettingInfoVO> getSettingInfos()
    {
        return settingInfos;
    }
    
    public void setSettingInfos(List<UICustSettingInfoVO> settingInfos)
    {
        this.settingInfos = settingInfos;
    }
}
