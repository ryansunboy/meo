package com.asiainfo.meo.meo.notification.configuration.model.vo;

import java.io.Serializable;
/**
 * 
  * @Description: The class contains all values of SysTimeSegDtl entity             
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author Thanapol                                                                                                                                                                                                                                                                           
  * @Date Sep 8, 2015 1:47:09 PM 
  * @version 1.0
 */
public class TimeSegDtlVO implements Serializable
{

    /**                                                                           
      * @Fields serialVersionUID : (用一句话描述这个变量表示什么)                                                                                                                                                                                                                                  
      */
    private static final long serialVersionUID = -4629643046944729687L;
    private Long dtlId;
    private Long segId;
    private Long startVal;
    private Long endVal;
    private Long operatorId;
    
    public Long getDtlId()
    {
        return dtlId;
    }
    public void setDtlId(Long dtlId)
    {
        this.dtlId = dtlId;
    }
    public Long getSegId()
    {
        return segId;
    }
    public void setSegId(Long segId)
    {
        this.segId = segId;
    }
    public Long getStartVal()
    {
        return startVal;
    }
    public void setStartVal(Long startVal)
    {
        this.startVal = startVal;
    }
    public Long getEndVal()
    {
        return endVal;
    }
    public void setEndVal(Long endVal)
    {
        this.endVal = endVal;
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
