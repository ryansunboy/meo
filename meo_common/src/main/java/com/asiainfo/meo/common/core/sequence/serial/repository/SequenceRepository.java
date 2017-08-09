package com.asiainfo.meo.common.core.sequence.serial.repository;

import java.util.List;

import com.asiainfo.meo.common.core.sequence.serial.model.entity.SysSequence;

public interface SequenceRepository
{
    List<SysSequence> loadAll();
    
    SysSequence getSequence(String sequenceName);
    
    long updateSequence(String sequenceName);
}
