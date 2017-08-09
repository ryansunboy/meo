package com.asiainfo.meo.system.authority.app.model.vo;
/**
 * 
  * @Description: Contains condition to get menu list                
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author Thanapol                                                                                                                                                                                                                                                                           
  * @Date Aug 20, 2015 4:23:31 PM 
  * @version 1.0
 */
public class MenuQueryConditionVO
{
    private Integer pageNo;    
    private Integer pageSize;
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
}
