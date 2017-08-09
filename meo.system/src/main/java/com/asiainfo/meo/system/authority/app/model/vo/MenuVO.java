package com.asiainfo.meo.system.authority.app.model.vo;

public class MenuVO
{
    private Long   menuId;
    
    private String menuName;
    
    private Long   parentMenuId;
    
    private Long   systemId;
    
    private Long   moduleId;
    
    public Long getMenuId()
    {
        return menuId;
    }
    
    public void setMenuId(Long menuId)
    {
        this.menuId = menuId;
    }
    
    public String getMenuName()
    {
        return menuName;
    }
    
    public void setMenuName(String menuName)
    {
        this.menuName = menuName;
    }
    
    public Long getParentMenuId()
    {
        return parentMenuId;
    }
    
    public void setParentMenuId(Long parentMenuId)
    {
        this.parentMenuId = parentMenuId;
    }
    
    public Long getSystemId()
    {
        return systemId;
    }
    
    public void setSystemId(Long systemId)
    {
        this.systemId = systemId;
    }
    
    public Long getModuleId()
    {
        return moduleId;
    }
    
    public void setModuleId(Long moduleId)
    {
        this.moduleId = moduleId;
    }
    
}
