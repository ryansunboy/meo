package com.asiainfo.meo.customer.level.app.repository;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.asiainfo.meo.customer.level.app.model.entity.CustomerLevelChgHis;
import com.asiainfo.meo.customer.level.app.model.entity.LevelDef;
import com.asiainfo.meo.customer.level.app.model.entity.LevelRule;
import com.asiainfo.meo.customer.level.app.model.entity.LevelRuleMapping;

public interface LevelRepository
{
    List<LevelDef> getLevelDef(DetachedCriteria criteria);
    
    
    List<LevelRule> getLevelRule(DetachedCriteria criteria  );
    
    void saveCustomerLevelChgHis(CustomerLevelChgHis customerLevelChgHis);
    
    CustomerLevelChgHis getCustomerLevelChgHis(long custId);
    
    List<LevelRuleMapping> getLevelRuleMapping(DetachedCriteria criteria);
    
    void saveLevelDef(LevelDef levelDef);
    
    
}
