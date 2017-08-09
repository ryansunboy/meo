package com.asiainfo.meo.web.passport.privilege.model.vo;

import java.util.List;

public class UISysMenuVO
{
    private Long   menuId;
    
    private String menuName;
    
    private Long   parentMenuId;
    
    private Long   systemId;
    
    private String system;
    
    private Long   moduleId;
    
    private String module;

    private String url;
    
    private List<UISysMenuVO> children;
    
    
    public String getSystem()
    {
        return system;
    }

    public void setSystem(String system)
    {
        this.system = system;
    }

    public String getModule()
    {
        return module;
    }

    public void setModule(String module)
    {
        this.module = module;
    }

    public List<UISysMenuVO> getChildren()
    {
        return children;
    }

    public void setChildren(List<UISysMenuVO> children)
    {
        this.children = children;
    }

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

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
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
