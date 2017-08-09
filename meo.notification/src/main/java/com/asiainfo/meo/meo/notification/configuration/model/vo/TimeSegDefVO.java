package com.asiainfo.meo.meo.notification.configuration.model.vo;

import java.io.Serializable;
import java.util.List;
/**
 * 
  * @Description: The class contains all values of SysTimeSegDef entity               
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author Thanapol                                                                                                                                                                                                                                                                           
  * @Date Sep 8, 2015 1:42:18 PM 
  * @version 1.0
 */
public class TimeSegDefVO implements Serializable
{

    /**                                                                           
      * @Fields serialVersionUID : (用一句话描述这个变量表示什么)                                                                                                                                                                                                                                  
      */
    private static final long serialVersionUID = -7559875470962108051L;
    private Long segId;
    private Integer timeMode;
    private String timeModeName;
    private String segName;
    private Long operatorId;
    private String description;
    private List<TimeSegDtlVO> timeSegmentDtlList;
    
    public Long getSegId()
    {
        return segId;
    }
    public void setSegId(Long segId)
    {
        this.segId = segId;
    }
    public Integer getTimeMode()
    {
        return timeMode;
    }
    public void setTimeMode(Integer timeMode)
    {
        this.timeMode = timeMode;
    }
    public String getSegName()
    {
        return segName;
    }
    public void setSegName(String segName)
    {
        this.segName = segName;
    }
    public Long getOperatorId()
    {
        return operatorId;
    }
    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public List<TimeSegDtlVO> getTimeSegmentDtlList()
    {
        return timeSegmentDtlList;
    }
    public void setTimeSegmentDtlList(List<TimeSegDtlVO> timeSegmentDtlList)
    {
        this.timeSegmentDtlList = timeSegmentDtlList;
    }
    public String getTimeModeName()
    {
        return timeModeName;
    }
    public void setTimeModeName(String timeModeName)
    {
        this.timeModeName = timeModeName;
    }
    
    
}
