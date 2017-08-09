package com.asiainfo.meo.web.core.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.asiainfo.meo.common.core.context.BoContext;

public class InitMeoFilter implements Filter
{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException
    {
        try
        {
            BoContext.getBoContext();
            ContentCacheRequestWrapper wrapRequest = new ContentCacheRequestWrapper((HttpServletRequest)request);
            chain.doFilter(wrapRequest, response);
        }
        finally
        {
            BoContext.remove();
        }
    }

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub
        
    }
}
