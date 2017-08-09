package com.asiainfo.meo.web.passport.privilege.model.vo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;


public class UIUserMenuVO
{
    @NotNull(groups={Update.class})
    private Long   menuId;
    
    @NotEmpty(groups={Insert.class, Update.class})
    private String menuName;
    
    private Long   parentMenuId;
    
    @Valid
    @NotEmpty(groups={Insert.class, Update.class})
    private String url;
    
    @Valid
    @NotNull(groups={Insert.class, Update.class})
    private Long   systemId;
    
    @Valid
    @NotNull(groups={Insert.class, Update.class})
    private Long   moduleId;
    
    @Valid
    @NotEmpty(groups={Insert.class})
    private String imageUrl;
    
    @Valid
    @NotNull(groups={Insert.class})
    private Integer menuType;
    
    public Integer getMenuType()
    {
        return menuType;
    }

    public void setMenuType(Integer menuType)
    {
        this.menuType = menuType;
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

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }
    
}
