package com.asiainfo.meo.web.notification.model.vo;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

public class UINotificationNumLimitVO implements Serializable
{

    /**                                                                           
      * @Fields serialVersionUID : (用一句话描述这个变量表示什么)                                                                                                                                                                                                                                  
      */
    private static final long serialVersionUID = -8694561637638956528L;
    
    @Valid
    @NotNull(groups={Update.class})
    private Long numLimitId;
    
    @Valid
    @NotNull(groups={Insert.class, Update.class})
    private Long actionId;
    
    private String actionName;
    
    @Valid
    @NotNull(groups={Insert.class, Update.class})
    private Integer regionCode;
    
    @Valid
    @NotNull(groups={Insert.class, Update.class})
    private Integer maxNum;
    
    @Valid
    @NotNull(groups={Insert.class, Update.class})
    private Integer cycleType;
    
    @Valid
    @NotNull(groups={Update.class})
    private Integer cycleUnit;

    public Long getActionId()
    {
        return actionId;
    }

    public void setActionId(Long actionId)
    {
        this.actionId = actionId;
    }

    public Integer getRegionCode()
    {
        return regionCode;
    }

    public void setRegionCode(Integer regionCode)
    {
        this.regionCode = regionCode;
    }

    public Integer getMaxNum()
    {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum)
    {
        this.maxNum = maxNum;
    }

    public Integer getCycleType()
    {
        return cycleType;
    }

    public void setCycleType(Integer cycleType)
    {
        this.cycleType = cycleType;
    }

    public Integer getCycleUnit()
    {
        return cycleUnit;
    }

    public void setCycleUnit(Integer cycleUnit)
    {
        this.cycleUnit = cycleUnit;
    }

    public Long getNumLimitId()
    {
        return numLimitId;
    }

    public void setNumLimitId(Long numLimitId)
    {
        this.numLimitId = numLimitId;
    }

    public String getActionName()
    {
        return actionName;
    }

    public void setActionName(String actionName)
    {
        this.actionName = actionName;
    }

}
