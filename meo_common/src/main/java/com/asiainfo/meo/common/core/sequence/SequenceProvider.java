package com.asiainfo.meo.common.core.sequence;

import java.io.Serializable;

public interface SequenceProvider
{
    Serializable next();
}
