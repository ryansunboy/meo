package com.asiainfo.meo.meo.notification.configuration.model.vo;

import java.io.Serializable;
/**
 * 
  * @Description: The class contains all values of SysNotificationNumLimit entity            
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author Thanapol                                                                                                                                                                                                                                                                           
  * @Date Sep 8, 2015 1:55:32 PM 
  * @version 1.0
 */
public class NotificationNumLimitVO implements Serializable
{

    /**                                                                           
      * @Fields serialVersionUID : (用一句话描述这个变量表示什么)                                                                                                                                                                                                                                  
      */
    private static final long serialVersionUID = -264498917739330321L;
    private Long numLimitId;
    private Long actionId;
    private String actionName;
    private Integer regionCode;
    private Integer maxNum;
    private Integer cycleType;
    private Integer cycleUnit;
    private Long operatorId;
    
    public Long getNumLimitId()
    {
        return numLimitId;
    }
    public void setNumLimitId(Long numLimitId)
    {
        this.numLimitId = numLimitId;
    }
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
    public Long getOperatorId()
    {
        return operatorId;
    }
    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
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
