package com.asiainfo.meo.common.core.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
public class GridPage<T> implements Serializable
{
    @SuppressWarnings("unused")
    private static final Log LOG     = LogFactory.getLog(GridPage.class);
    
    private int              page    = 1;
    
    private int              total   = 1;
    
    private int              records = 1;
    
    private List<T>          rows    = new ArrayList<T>();
    
    public int getPage()
    {
        return page;
    }
    
    public void setPage(int page)
    {
        this.page = page;
    }
    
    public int getTotal()
    {
        return total;
    }
    
    public void setTotal(int total)
    {
        this.total = total;
    }
    
    public int getRecords()
    {
        return records;
    }
    
    public void setRecords(int records)
    {
        this.records = records;
    }
    
    public List<T> getRows()
    {
        return rows;
    }
    
    public void setRows(List<T> rows)
    {
        this.rows = rows;
    }
    
    public GridPage(PageInfo<T> pageInfo)
    {
        setPage(pageInfo.getCurrentPage());
        setRecords(pageInfo.getTotalSize());
        setTotal(pageInfo.getPageNum());
        List<T> list = pageInfo.getResult();
        setRows(list);
    }
    
    public GridPage(List<T> list)
    {
        setRecords(list.size());
        setRows(list);
    }
    
    public GridPage()
    {
        super();
    }
    
}
