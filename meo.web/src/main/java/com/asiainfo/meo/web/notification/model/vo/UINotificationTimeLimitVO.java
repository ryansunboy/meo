package com.asiainfo.meo.web.notification.model.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

public class UINotificationTimeLimitVO implements Serializable
{

    /**                                                                           
      * @Fields serialVersionUID : (用一句话描述这个变量表示什么)                                                                                                                                                                                                                                  
      */
    private static final long serialVersionUID = -8518957734673572747L;
    
    @Valid
    @NotNull(groups={Update.class})
    private Long timeLimitId;
    
    @Valid
    @NotNull(groups={Insert.class, Update.class})
    private Long actionId;
    
    @Valid
    @NotNull(groups={Insert.class, Update.class})
    private Integer regionCode;
    
    private String actionName;
    
    private List<UITimeSegDefVO> timeSegmentList;
    
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
    public List<UITimeSegDefVO> getTimeSegmentList()
    {
        return timeSegmentList;
    }
    public void setTimeSegmentList(List<UITimeSegDefVO> timeSegmentList)
    {
        this.timeSegmentList = timeSegmentList;
    }
    public String getActionName()
    {
        return actionName;
    }
    public void setActionName(String actionName)
    {
        this.actionName = actionName;
    }
    public Long getTimeLimitId()
    {
        return timeLimitId;
    }
    public void setTimeLimitId(Long timeLimitId)
    {
        this.timeLimitId = timeLimitId;
    }
}
