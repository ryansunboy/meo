package com.asiainfo.meo.task.profile.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.task.profile.repository.TaskRepository;

public class TaskRepositoryImpl implements TaskRepository
{
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    public List<Object> getEntityPatcipant(Integer n)
    {
        return (List<Object>) hibernateRepository.findByNamedQuery("meo.asset.getEntityPatcipant", n);
    }
}
