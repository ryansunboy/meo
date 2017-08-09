package com.asiainfo.meo.common.core.utils;

public class GridRow
{
    private String   id;
    
    private String[] cell;
    
    public GridRow(String id, String[] cell)
    {
        this.id = id;
        this.cell = cell;
    }
    
    public String getId()
    {
        return id;
    }
    
    public String[] getCell()
    {
        return cell;
    }
    
}
