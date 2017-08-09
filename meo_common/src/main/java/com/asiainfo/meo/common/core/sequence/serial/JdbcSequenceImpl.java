package com.asiainfo.meo.common.core.sequence.serial;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.Resource;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.sequence.SequenceProvider;
import com.asiainfo.meo.common.core.sequence.serial.model.entity.SysSequence;
import com.asiainfo.meo.common.core.sequence.serial.provider.JdbcSequenceProviderImpl;
import com.asiainfo.meo.common.core.sequence.serial.repository.SequenceRepository;
import com.asiainfo.meo.common.core.utils.ValidateUtil;

public class JdbcSequenceImpl implements Sequence
{
    private ConcurrentMap<String,SequenceProvider> store  = new ConcurrentHashMap<String,SequenceProvider>(16);
    
    @Resource
    private SequenceRepository sequenceRepository;
    
    private Object LOCK = new Object();
    
    public JdbcSequenceImpl()
    {
//        loadAll();
    }
    
    private void loadAll()
    {
        List<SysSequence> tSequences = getSequenceRepository().loadAll();
        if(!ValidateUtil.isEmpty(tSequences))
        {
            for(SysSequence tSequence : tSequences)
            {
                store.put(tSequence.getSequenceName(), new JdbcSequenceProviderImpl(tSequence));
            }
        }
    }
    
    public  long next(String sequenceName)
    {
        SequenceProvider sequeceProvider = store.get(sequenceName);
        if(sequeceProvider == null)
        {
            synchronized (LOCK)
            {
                sequeceProvider = store.get(sequenceName);
                if(sequeceProvider == null)
                {
                    SysSequence tSequence = getSequenceRepository().getSequence(sequenceName);
                    if(tSequence == null)
                    {
                        throw new IllegalArgumentException("sequence not found [" + sequenceName + "]");//TODO
                    }
                    sequeceProvider =  new JdbcSequenceProviderImpl(tSequence);
                    SequenceProvider oldSequenceProvider =  store.putIfAbsent(sequenceName,  sequeceProvider);
                    if(oldSequenceProvider != null)
                    {
                        sequeceProvider = oldSequenceProvider;
                    }
                }
            }
        }
        
        Long nextVal = (Long)sequeceProvider.next();
        return nextVal.longValue();
    }

    public SequenceRepository getSequenceRepository()
    {
        return sequenceRepository;
    }

    public void setSequenceRepository(SequenceRepository sequenceRepository)
    {
        this.sequenceRepository = sequenceRepository;
    }
    
    
}
