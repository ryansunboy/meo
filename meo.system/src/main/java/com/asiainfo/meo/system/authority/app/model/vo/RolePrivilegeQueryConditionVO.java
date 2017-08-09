package com.asiainfo.meo.system.authority.app.model.vo;
/**
 * 
  * @Description: This class contains query's conditions for role privilege          
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author Thanapol                                                                                                                                                                                                                                                                           
  * @Date Aug 21, 2015 10:03:14 AM 
  * @version 1.0
 */
public class RolePrivilegeQueryConditionVO
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
