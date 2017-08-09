package com.asiainfo.meo.common.core.sequence.uuid;

import java.io.Serializable;
import java.util.UUID;

import com.asiainfo.meo.common.core.sequence.SequenceProvider;

public class UUIDSequenceProviderImpl implements SequenceProvider
{
    public Serializable next()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
}
