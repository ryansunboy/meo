package com.asiainfo.meo.product.profile.app.model.vo;
/**
 * 
  * @Description: The class contains query condition of product basic info               
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author Thanapol                                                                                                                                                                                                                                                                           
  * @Date Aug 21, 2015 5:18:34 PM 
  * @version 1.0
 */
public class ProductBasicInfoQueryConditionVO
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
