package com.asiainfo.meo.web.core;

public final class VersionInfo
{
    public static final int majorVersion = 10;
    
    public static final int minorVersion = 1;
    
    private VersionInfo()
    {
    }
    
    public static int getValidVersion(String version)
    {
        return toValidVersion(version);
    }
    
    public static int getMajorVersion()
    {
        return majorVersion;
    }
    
    public static int getMinorVersion()
    {
        return minorVersion;
    }
    
    private static boolean isValidVersion(int version)
    {
        return (version >= minorVersion) && (version <= majorVersion);
    }
    
    private static int toValidVersion(String s)
    {
        try
        {
            int v = Integer.parseInt(s);
            
            if (isValidVersion(v))
            {
                return v;
            }
            return majorVersion;
        }
        catch(Exception e)
        {
            return majorVersion;
        }
    }
}
