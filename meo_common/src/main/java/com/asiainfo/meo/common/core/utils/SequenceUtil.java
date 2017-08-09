package com.asiainfo.meo.common.core.utils;

import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.sequence.SequenceProvider;

public final class SequenceUtil
{
    private SequenceUtil()
    {}
    
    public static long getSequence(String sequenceName)
    {
        return ServiceLocatorFactory.getService(Sequence.class).next(sequenceName);
    }
    
    public static String getUUIDSequence()
    {
        SequenceProvider uuidSequenceProvider =  ServiceLocatorFactory.getService("uuidSequenceProvider",SequenceProvider.class);
        return (String)uuidSequenceProvider.next();
    }
    
    
    public static String getGlobalSequence()
    {
        SequenceProvider globalSequenceProvider = ServiceLocatorFactory.getService("globalSequenceProvider",SequenceProvider.class);
        return (String)globalSequenceProvider.next();
    }
}
