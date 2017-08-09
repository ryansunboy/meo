package com.asiainfo.meo.system.authority.app.model.vo;
/**
 * 
  * @Description: The class contains query conditions of user's role information         
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author Thanapol                                                                                                                                                                                                                                                                           
  * @Date Aug 21, 2015 12:49:18 PM 
  * @version 1.0
 */
public class UserRoleQueryConditionVO
{
    private Integer pageNo;
    private Integer pageSize;
    private Long   roleId;    
    private String roleName;
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
    public Long getRoleId()
    {
        return roleId;
    }
    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }
    public String getRoleName()
    {
        return roleName;
    }
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }
    
}
