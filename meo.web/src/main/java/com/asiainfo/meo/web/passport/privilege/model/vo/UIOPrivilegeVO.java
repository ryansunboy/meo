package com.asiainfo.meo.web.passport.privilege.model.vo;

public class UIOPrivilegeVO
{
    private Long   privilegeId;
    
    private Long   operId;
    
    private String operName;
    
    public Long getPrivilegeId()
    {
        return privilegeId;
    }
    
    public void setPrivilegeId(Long privilegeId)
    {
        this.privilegeId = privilegeId;
    }
    
    public Long getOperId()
    {
        return operId;
    }
    
    public void setOperId(Long operId)
    {
        this.operId = operId;
    }
    
    public String getOperName()
    {
        return operName;
    }
    
    public void setOperName(String operName)
    {
        this.operName = operName;
    }
}
