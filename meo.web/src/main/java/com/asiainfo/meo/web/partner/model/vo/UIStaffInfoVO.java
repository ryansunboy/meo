package com.asiainfo.meo.web.partner.model.vo;

// Generated 2015-5-11 19:31:42 by Hibernate Tools 3.4.0.CR1


/**
 * CmStaffInfo generated by hbm2java
 */
public class UIStaffInfoVO implements java.io.Serializable
{
    
    /**                                                                           
      * @Fields serialVersionUID : (用一句话描述这个变量表示什么)                                                                                                                                                                                                                                  
      */
    private static final long serialVersionUID = 1L;

    private Long      staffId;
    
    private Long      staffNo;  
    
    private String    staffName;
    
    private String    email;
    
    private Integer   sts;
    
    private Integer   department;
    
    public Long getStaffId()
    {
        return staffId;
    }
    
    public void setStaffId(Long staffId)
    {
        this.staffId = staffId;
    }
    
    public Long getStaffNo()
    {
        return staffNo;
    }
    
    public void setStaffNo(Long staffNo)
    {
        this.staffNo = staffNo;
    }
    
    public String getStaffName()
    {
        return staffName;
    }
    
    public void setStaffName(String staffName)
    {
        this.staffName = staffName;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public Integer getSts()
    {
        return sts;
    }
    
    public void setSts(Integer sts)
    {
        this.sts = sts;
    }
    
    public Integer getDepartment()
    {
        return department;
    }
    
    public void setDepartment(Integer department)
    {
        this.department = department;
    }
    
}