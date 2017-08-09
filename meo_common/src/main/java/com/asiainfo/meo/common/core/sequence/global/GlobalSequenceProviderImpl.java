package com.asiainfo.meo.common.core.sequence.global;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import com.asiainfo.meo.common.core.sequence.SequenceProvider;

public class GlobalSequenceProviderImpl implements SequenceProvider
{
    
    private String prefix = "meo-";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final String SOA_SUFFERIX_PATTERN = "%1$010d";
    private AtomicLong count = new AtomicLong(); //TODO ATOMIC RECURRING POSITIVE COUNTER
    
    public String next()
    {
        StringBuilder sb = new StringBuilder(getPrefix());
        sb.append(dateFormat.format(new Date()));
        long index = count.incrementAndGet();
        String indexValue = String.format(SOA_SUFFERIX_PATTERN, index);
        sb.append(indexValue);
        return sb.toString();
    }

    public String getPrefix()
    {
        return prefix;
    }

    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }
}
