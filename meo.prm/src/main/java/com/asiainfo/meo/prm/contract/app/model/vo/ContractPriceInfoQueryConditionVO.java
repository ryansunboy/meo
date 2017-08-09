package com.asiainfo.meo.prm.contract.app.model.vo;
/**
 * 
  * @Description: The class contains query's condition of contract price info            
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author Thanapol                                                                                                                                                                                                                                                                           
  * @Date Aug 21, 2015 5:50:26 PM 
  * @version 1.0
 */
public class ContractPriceInfoQueryConditionVO
{
    private Integer pageNo;
    private Integer pageSize;
    private Long partnerId;
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
    public Long getPartnerId()
    {
        return partnerId;
    }
    public void setPartnerId(Long partnerId)
    {
        this.partnerId = partnerId;
    }
}
