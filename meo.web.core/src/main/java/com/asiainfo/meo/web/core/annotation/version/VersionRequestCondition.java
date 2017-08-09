package com.asiainfo.meo.web.core.annotation.version;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.condition.AbstractRequestCondition;

import com.asiainfo.meo.web.core.VersionInfo;

public class VersionRequestCondition extends AbstractRequestCondition<VersionRequestCondition>
{
    private static final String VERSION = "v";
    
    private final List<Integer> versionRange = new ArrayList<Integer>(2);
    
    private final int minorVersion;
    
    private final int majorVersion;
    
    public VersionRequestCondition(int minorVersion, int majorVersion)
    {
        this.minorVersion = minorVersion;
        this.majorVersion = majorVersion;
        
        this.versionRange.add(minorVersion);
        this.versionRange.add(majorVersion);
    }
    
    @Override
    public VersionRequestCondition combine(VersionRequestCondition other)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public VersionRequestCondition getMatchingCondition(HttpServletRequest request)
    {
        String version = request.getParameter(VERSION);
        int v = VersionInfo.getValidVersion(version);
        if (containVersion(v))
        {
            return this;
        }
        return null;
    }
    
    @Override
    public int compareTo(VersionRequestCondition other, HttpServletRequest request)
    {
        return (other.majorVersion == this.majorVersion) && (other.minorVersion == this.minorVersion) ? 0 : -1;
    }
    
    private boolean containVersion(int version)
    {
        return (version >= minorVersion) && (version <= majorVersion);
    }

    @Override
    protected Collection<Integer> getContent()
    {
        return this.versionRange;
    }

    @Override
    protected String getToStringInfix()
    {
        return ",";
    }
}
