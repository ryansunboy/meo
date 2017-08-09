package com.asiainfo.meo.system.common.app.model.vo;
/**
 * 
  * @Description: The class contains query's condition of SysActionParamDef   
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author Thanapol                                                                                                                                                                                                                                                                           
  * @Date Aug 21, 2015 4:34:47 PM 
  * @version 1.0
 */
public class SysActionParamDefQueryConditionVO
{
    private Integer pageNo;
    private Integer pageSize;
    private String actionId;
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
    public String getActionId()
    {
        return actionId;
    }
    public void setActionId(String actionId)
    {
        this.actionId = actionId;
    }
    
}
