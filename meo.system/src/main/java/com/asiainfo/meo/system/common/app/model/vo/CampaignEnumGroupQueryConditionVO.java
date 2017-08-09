package com.asiainfo.meo.system.common.app.model.vo;
/**
 * 
  * @Description: The class contains query's condition of campaign enum group                 
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author Thanapol                                                                                                                                                                                                                                                                           
  * @Date Sep 1, 2015 11:22:33 AM 
  * @version 1.0
 */
public class CampaignEnumGroupQueryConditionVO
{
    private Integer pageNo;
    private Integer pageSize;
    private String groupCode;
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
    public String getGroupCode()
    {
        return groupCode;
    }
    public void setGroupCode(String groupCode)
    {
        this.groupCode = groupCode;
    }
}
