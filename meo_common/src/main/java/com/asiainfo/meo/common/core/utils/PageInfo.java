package com.asiainfo.meo.common.core.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PageInfo<T> implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @SuppressWarnings("unused")
    private static final Log  LOG              = LogFactory.getLog(PageInfo.class);
    
    public final static int DEFAULT_PAGE_NO= 1;
    
    public final static int DEFAULT_PAGE_SIZE = 10 ;
    
    private int               pageSize      = DEFAULT_PAGE_SIZE;
    
    private int               currentPage      = DEFAULT_PAGE_NO;
    
    private List<T>           result;
    
    private int               totalSize     = 0;
    
    public PageInfo<T> emptyPageInfo(int pageSize)
    {
        PageInfo<T> pageInfo = new PageInfo<T>();
        pageInfo.setTotalSize(0);
        pageInfo.setCurrentPage(0);
        pageInfo.setPageSize(pageSize);
        pageInfo.setResult(new ArrayList<T>());
        return pageInfo;
    }
    
    public PageInfo<T> emptyPageInfo()
    {
        PageInfo<T> pageInfo = new PageInfo<T>();
        pageInfo.setTotalSize(0);
        pageInfo.setCurrentPage(0);
        pageInfo.setPageSize(pageSize);
        pageInfo.setResult(new ArrayList<T>());
        return pageInfo;
    }
    
    public PageInfo<T> createPageInfo(int currentPage, int pageSize, int totalSize, List<T> list)
    {
        PageInfo<T> pageInfo = new PageInfo<T>();
        pageInfo.setResult(list);
        pageInfo.setTotalSize(totalSize);
        pageInfo.setCurrentPage(currentPage);
        pageInfo.setPageSize(pageSize);
        return pageInfo;
    }
    
    public PageInfo<T> createPageInfo(int totalRowSize, List<T> list)
    {
        PageInfo<T> pageInfo = new PageInfo<T>();
        pageInfo.setResult(list);
        pageInfo.setTotalSize(totalRowSize);
        return pageInfo;
    }
    
    public int getCurrentPage()
    {
        return currentPage;
    }
    
    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }
    
    public int getPageNum()
    {
        return totalSize% pageSize> 0 ? (totalSize/ pageSize)+ 1 : (totalSize/ pageSize);
    }
    
    public List<T> getResult()
    {
        return result;
    }
    
    public void setResult(List<T> result)
    {
        this.result = result;
    }
    
    public boolean haveNextPage()
    {
        if (result== null)
        {
            return false;
        }
        return (currentPage- 1)* pageSize+ result.size()< totalSize;
    }
    
    public boolean havePreviousPage()
    {
        return currentPage> 1;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getTotalSize()
    {
        return totalSize;
    }

    public void setTotalSize(int totalSize)
    {
        this.totalSize = totalSize;
    }
}
