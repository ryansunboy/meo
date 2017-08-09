package com.asiainfo.meo.web.core.servlet;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;

public class ContentCacheRequestWrapper extends HttpServletRequestWrapper
{
    private byte[] rawData;
    
    private HttpServletRequest request;
    
    public ContentCacheRequestWrapper(HttpServletRequest request)
    {
        super(request);
        this.request = request;
    }
    
    @Override
    public ServletInputStream getInputStream() throws IOException
    {
        if (rawData== null)
        {
            rawData = IOUtils.toByteArray(this.request.getInputStream());
        }
        return new ContentCacheInputStream(new ByteArrayInputStream(rawData));
    }
    
    @Override
    public BufferedReader getReader() throws IOException
    {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
    
    public byte[] getContentAsByteArray()
    {
        return this.rawData;
    }
    
    private class ContentCacheInputStream extends ServletInputStream
    {
        private final InputStream is;
        
        public ContentCacheInputStream(InputStream is)
        {
            this.is = is;
        }
        
        @Override
        public int read() throws IOException
        {
            return is.read();
        }
        
    }
}
