package com.asiainfo.meo.system.authority.app.model.vo;
/**
 * 
  * @Description: Contain query conditions of Operator entity                 
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author Thanapol                                                                                                                                                                                                                                                                           
  * @Date Aug 18, 2015 5:32:17 PM 
  * @version 1.0
 */
public class OperatorQueryConditionVO
{
    private Integer pageNo;    
    private Integer pageSize;
    private Long operatorId;
    private String operatorName;
    public Integer getPageNo()
    {
        return pageNo;
    }
    public void setPageNo(Integer pageNo)
    {
        this.pageNo = pageNo;
    }
    public Integer getPageSize()
    {
        return pageSize;
    }
    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }
    public Long getOperatorId()
    {
        return operatorId;
    }
    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
    }
    public String getOperatorName()
    {
        return operatorName;
    }
    public void setOperatorName(String operatorName)
    {
        this.operatorName = operatorName;
    }
    
}
