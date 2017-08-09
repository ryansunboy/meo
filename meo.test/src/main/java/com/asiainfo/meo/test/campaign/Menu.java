package com.asiainfo.meo.test.campaign;

import java.util.List;

public class Menu
{
    private Integer id;
    
    private Integer parentId;
    
    private String  name;
    
    private List<Menu> children;
    
    public List<Menu> getChildren()
    {
        return children;
    }

    public void setChildren(List<Menu> children)
    {
        this.children = children;
    }

    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public Integer getParentId()
    {
        return parentId;
    }
    
    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
}
