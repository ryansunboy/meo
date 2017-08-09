package com.asiainfo.meo.common.core.sequence.serial.provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asiainfo.meo.common.core.sequence.SequenceProvider;
import com.asiainfo.meo.common.core.sequence.serial.model.entity.SysSequence;
import com.asiainfo.meo.common.core.sequence.serial.repository.SequenceRepository;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;

public class JdbcSequenceProviderImpl implements SequenceProvider
{
    private static final Log LOG = LogFactory.getLog(JdbcSequenceProviderImpl.class);
    
    private String sequeceName;
    
    private long currentVal;
    
    private int cache;
    
    private int step;
    
    private int offset;
    
    private long nextVal;
    
    private SequenceRepository sequenceRepository;
    
    public JdbcSequenceProviderImpl()
    {
        
    }
    
    public JdbcSequenceProviderImpl(SysSequence sysSequence)
    {
        this.sequeceName = sysSequence.getSequenceName();
        this.currentVal = sysSequence.getLastVal();
        this.cache = sysSequence.getCache();
        this.step = sysSequence.getIncrementBy();
        this.offset = this.cache * this.step;
        
        refreshSequence();
    }
    
    public synchronized Long next()
    {
        currentVal = calNextVal();
        if (currentVal <= nextVal)
        {
            return currentVal;
        }
        else
        {
            refreshSequence(); 
            return calNextVal();
        }
    }
    
    private long calNextVal()
    {
        currentVal = currentVal + step;
        return currentVal;
    }
    
    private void refreshSequence()
    {
        long lastVal = getSequenceRepository().updateSequence(this.sequeceName);
        LOG.info("refresh sequence ,sequence name [" + this.sequeceName + "], lastVal = " + lastVal);
        currentVal = lastVal;
        nextVal = currentVal + offset;
    }
    
    public SequenceRepository getSequenceRepository()
    {
        if (sequenceRepository == null)
        {
            this.sequenceRepository = ServiceLocatorFactory.getService("sequenceRepository", SequenceRepository.class);
        }
        return sequenceRepository;
    }
    
    public void setSequenceRepository(SequenceRepository sequenceRepository)
    {
        this.sequenceRepository = sequenceRepository;
    }
    
}
