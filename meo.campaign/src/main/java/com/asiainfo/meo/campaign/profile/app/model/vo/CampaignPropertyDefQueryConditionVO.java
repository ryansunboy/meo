package com.asiainfo.meo.campaign.profile.app.model.vo;
/**
 * 
  * @Description: The class contain query's condition of CampaignPropertyDef                
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author Thanapol                                                                                                                                                                                                                                                                           
  * @Date Aug 21, 2015 3:10:21 PM 
  * @version 1.0
 */
public class CampaignPropertyDefQueryConditionVO
{
    private Integer pageNo;
    private Integer pageSize;
    private Integer campaignType;
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
    public Integer getCampaignType()
    {
        return campaignType;
    }
    public void setCampaignType(Integer campaignType)
    {
        this.campaignType = campaignType;
    }
}
