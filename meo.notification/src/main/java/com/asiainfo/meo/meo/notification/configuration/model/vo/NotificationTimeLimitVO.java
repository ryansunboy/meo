package com.asiainfo.meo.meo.notification.configuration.model.vo;

import java.io.Serializable;
import java.util.List;
/**
 * 
  * @Description: The class contains all values of SysNotificationTimeLimit entity                
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author Thanapol                                                                                                                                                                                                                                                                           
  * @Date Sep 8, 2015 1:25:05 PM 
  * @version 1.0
 */
public class NotificationTimeLimitVO implements Serializable
{

    /**                                                                           
      * @Fields serialVersionUID : (用一句话描述这个变量表示什么)                                                                                                                                                                                                                                  
      */
    private static final long serialVersionUID = -1040073819447826330L;
    private Long timeLimitId;
    private Long actionId;
    private String actionName;
    private Long segId;
    private Integer regionCode;
    private Long operatorId;
    private List<TimeSegDefVO> timeSegmentList;
    
    public Long getTimeLimitId()
    {
        return timeLimitId;
    }
    public void setTimeLimitId(Long timeLimitId)
    {
        this.timeLimitId = timeLimitId;
    }
    public Long getActionId()
    {
        return actionId;
    }
    public void setActionId(Long actionId)
    {
        this.actionId = actionId;
    }
    public Long getSegId()
    {
        return segId;
    }
    public void setSegId(Long segId)
    {
        this.segId = segId;
    }
    public Integer getRegionCode()
    {
        return regionCode;
    }
    public void setRegionCode(Integer regionCode)
    {
        this.regionCode = regionCode;
    }
    public Long getOperatorId()
    {
        return operatorId;
    }
    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
    }
    public List<TimeSegDefVO> getTimeSegmentList()
    {
        return timeSegmentList;
    }
    public void setTimeSegmentList(List<TimeSegDefVO> timeSegmentList)
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

}
