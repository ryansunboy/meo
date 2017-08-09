package com.asiainfo.meo.customer.level.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.customer.level.app.model.entity.CustomerLevelChgHis;
import com.asiainfo.meo.customer.level.app.model.entity.LevelDef;
import com.asiainfo.meo.customer.level.app.model.entity.LevelRule;
import com.asiainfo.meo.customer.level.app.model.entity.LevelRuleMapping;
import com.asiainfo.meo.customer.level.app.repository.LevelRepository;

public class LevelRepositoryImpl implements LevelRepository
{
    //@Resource
    //private Repository hibernateRepository;
    @Resource
    private HibernateRepository hibernateRepository;
    
    @SuppressWarnings("unchecked")
    public List<LevelDef> getLevelDef(DetachedCriteria criteria)
    {
        
        return (List<LevelDef>)hibernateRepository.findByCriteria(criteria);
    }
    
    @SuppressWarnings("unchecked")
    public List<LevelRuleMapping> getLevelRuleMapping(DetachedCriteria criteria)
    {
        return (List<LevelRuleMapping>)hibernateRepository.findByCriteria(criteria);
    }
    
    @SuppressWarnings("unchecked")
    public List<LevelRule> getLevelRule(DetachedCriteria criteria)
    {
        return (List<LevelRule>)hibernateRepository.findByCriteria(criteria);
    }
    
    public void saveCustomerLevelChgHis(CustomerLevelChgHis customerLevelChgHis)
    {
        hibernateRepository.saveObject(customerLevelChgHis);
    }
    
    public LevelRule getLevelRule(int levelRuleId)
    {
        return hibernateRepository.get(LevelRule.class, levelRuleId);
    }
    
    public CustomerLevelChgHis getCustomerLevelChgHis(long custId)
    {
        return hibernateRepository.get(CustomerLevelChgHis.class, custId);
    }
    
    public void saveLevelDef(LevelDef levelDef)
    {
        hibernateRepository.saveObject(levelDef);
    }
    
}
