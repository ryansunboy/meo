package com.asiainfo.meo.common.core.sequence.serial.repository.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional.TxType;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.serial.model.entity.SysSequence;
import com.asiainfo.meo.common.core.sequence.serial.repository.SequenceRepository;

public class SequenceRepositoryImpl implements SequenceRepository
{
//    private static final String P_SEQ_NEXT = "call SEQ_NEXTVAL{?,?}";   
    
    private static final String SEQ_NEXT = "SELECT START_BY,INCREMENT_BY,CACHE,LAST_VAL FROM sys_sequences WHERE SEQUENCE_NAME = ?";
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    public List<SysSequence> loadAll()
    {
        return getHibernateRepository().loadAll(SysSequence.class);
    }
    
    public SysSequence getSequence(String sequenceName)
    {
        Map<String,Object> sysSequence = getHibernateRepository().findUniqueMapByNativeSql(SEQ_NEXT, sequenceName);
        SysSequence s = new SysSequence();
        s.setSequenceName(sequenceName);
        s.setStartAt(((BigInteger)sysSequence.get("START_BY")).longValue());
        s.setIncrementBy(((BigInteger)sysSequence.get("INCREMENT_BY")).intValue());
        s.setCache(((BigInteger)sysSequence.get("CACHE")).intValue());
        s.setLastVal(((BigInteger)sysSequence.get("LAST_VAL")).longValue());
        return s;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public long updateSequence(String sequenceName)
    {
        Map<String,Object> result = getHibernateRepository().findUniqueMapByNativeSql("SELECT SEQ_NEXTVAL(?) as seq", sequenceName);
        return ((BigInteger)result.get("seq")).longValue();
    }

    public HibernateRepository getHibernateRepository()
    {
        return hibernateRepository;
    }

    public void setHibernateRepository(HibernateRepository hibernateRepository)
    {
        this.hibernateRepository = hibernateRepository;
    }
    
}
