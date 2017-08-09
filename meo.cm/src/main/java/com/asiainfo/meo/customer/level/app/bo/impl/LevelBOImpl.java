package com.asiainfo.meo.customer.level.app.bo.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.customer.level.app.bo.LevelBO;
import com.asiainfo.meo.customer.level.app.model.entity.CustomerLevelChgHis;
import com.asiainfo.meo.customer.level.app.model.entity.LevelDef;
import com.asiainfo.meo.customer.level.app.model.entity.LevelRule;
import com.asiainfo.meo.customer.level.app.model.entity.LevelRuleMapping;
import com.asiainfo.meo.customer.level.app.model.vo.Level;
import com.asiainfo.meo.customer.level.app.repository.LevelRepository;
import com.asiainfo.meo.customer.profile.app.model.entity.Customer;
import com.asiainfo.meo.customer.service.require.CustomerRserviceBO;

public class LevelBOImpl implements LevelBO
{
    private static final Log LOG = LogFactory.getLog(LevelBOImpl.class);
    
    @Resource
    private LevelRepository levelRepository;
    
    @Resource(name = "customerRserviceBO")
    private CustomerRserviceBO        custRserviceBO;
    
    private Customer getCustomer(Long custId)
    {
        return custRserviceBO.getCustomer(custId);
    }
    
    private void updateCustomer(Customer customer)
    {
        custRserviceBO.updateCustomer(customer);
    }
    
    public List<LevelDef> getLevelDef(int levelId, int levelType)
    {
        LOG.info("LevelBO getLevelDef begin");
        DetachedCriteria criteria = DetachedCriteria.forClass(LevelDef.class);  
        if(ValidateUtil.isEmpty(levelId))
            criteria.add(Restrictions.eq("levelId", levelId));

        if(ValidateUtil.isEmpty(levelType))
            criteria.add(Restrictions.eq("levelType", levelType));
        
        return levelRepository.getLevelDef(criteria);
    }
    
    public void saveCustomerLevelChgHis(CustomerLevelChgHis customerLevelChgHis)
    {
        Sequence sequence = ServiceLocatorFactory.getService(Sequence.class);
        customerLevelChgHis.setId(sequence.next("SEQ_CUSTOMER_LEVEL_CHG_HIS_ID"));
        levelRepository.saveCustomerLevelChgHis(customerLevelChgHis);
    }
    
    public CustomerLevelChgHis getCustomerLevelChgHis(long custId)
    {
        return levelRepository.getCustomerLevelChgHis(custId);
    }
    
    public List<LevelRuleMapping> getLevelRuleMapping(int levelId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(LevelRuleMapping.class);  
        if(ValidateUtil.isEmpty(levelId))
            criteria.add(Restrictions.eq("levelId", levelId));
    
        return levelRepository.getLevelRuleMapping(criteria);
    }
    
    public String updateUpLevel(long custId, int levelType)
    {
        Customer customer = getCustomer(custId);
        int levelId = customer.getLevelId();
        
        if (levelId== LevelDef.AKIRA)
            return "AKIRA aleady the hightest level";
        
        if (levelId== LevelDef.SPARK)
        {
            customer.setCustId(custId);
            customer.setLevelId(LevelDef.NOVA);
            customer.setLevelValidDate(DateTimeUtil.getNow());
            CustomerLevelChgHis customerLevelChgHis = new CustomerLevelChgHis();
            customerLevelChgHis.setCustId(custId);
            customerLevelChgHis.setOrgLevelId(LevelDef.SPARK);
            customerLevelChgHis.setCurLevelId(LevelDef.NOVA);
            customerLevelChgHis.setLevelValidDate(DateTimeUtil.getNow());
            customerLevelChgHis.setModifyDate(DateTimeUtil.getNow());
            
            List<LevelDef> levelDeflist = getLevelDef(LevelDef.NOVA, levelType);
            for (LevelDef levelDef : levelDeflist)
            {
                int period = levelDef.getPeriod();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(DateTimeUtil.getNow());
                calendar.add(Calendar.MONTH, period);
                customerLevelChgHis.setLevelExpiredDate(DateTimeUtil.getNow());
                customer.setLevelExpiredDate(new Timestamp(calendar.getTimeInMillis()));
            }
            customerLevelChgHis.setSts(CustomerLevelChgHis.STS_VALID);
            customerLevelChgHis.setCreateDate(DateTimeUtil.getNow());
            updateCustomer(customer);
            saveCustomerLevelChgHis(customerLevelChgHis);
            return "Congratulation, you upgrade from SPARK to NOVA";
            
        }
        else if (levelId== LevelDef.NOVA)
        {
            customer.setCustId(custId);
            customer.setLevelId(LevelDef.AKIRA);
            customer.setLevelValidDate(DateTimeUtil.getNow());
            
            CustomerLevelChgHis customerLevelChgHis = new CustomerLevelChgHis();
            customerLevelChgHis.setCustId(custId);
            customerLevelChgHis.setOrgLevelId(LevelDef.NOVA);
            customerLevelChgHis.setCurLevelId(LevelDef.AKIRA);
            customerLevelChgHis.setModifyDate(DateTimeUtil.getNow());
            customerLevelChgHis.setLevelValidDate(DateTimeUtil.getNow());
            List<LevelDef> levelDeflist = getLevelDef(LevelDef.AKIRA, levelType);
            for (LevelDef levelDef : levelDeflist)
            {
                int period = levelDef.getPeriod();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(DateTimeUtil.getNow());
                calendar.add(Calendar.MONTH, period);
                customerLevelChgHis.setLevelExpiredDate(new Timestamp(calendar.getTimeInMillis()));
                customer.setLevelExpiredDate(new Timestamp(calendar.getTimeInMillis()));
            }
            customerLevelChgHis.setSts(CustomerLevelChgHis.STS_VALID);
            customerLevelChgHis.setCreateDate(DateTimeUtil.getNow());
            updateCustomer(customer);
            saveCustomerLevelChgHis(customerLevelChgHis);
            return "Congratulation, you upgrade from NOVA to AKIRA";
        }
        return null;
    }
    
    public String updateDownLevel(long custId, int levelType)
    {
        Customer customer = getCustomer(custId);
        int levelId = customer.getLevelId();
        if (levelId== LevelDef.SPARK)
        {
            return "spark aleady the lowest level";
        }else if (levelId== LevelDef.NOVA)
        {
            customer.setCustId(custId);
            customer.setLevelId(LevelDef.SPARK);
            customer.setLevelValidDate(DateTimeUtil.getNow());
            
            CustomerLevelChgHis customerLevelChgHis = new CustomerLevelChgHis();
            customerLevelChgHis.setCustId(custId);
            customerLevelChgHis.setOrgLevelId(LevelDef.NOVA);
            customerLevelChgHis.setCurLevelId(LevelDef.SPARK);
            customerLevelChgHis.setModifyDate(DateTimeUtil.getNow());
            customerLevelChgHis.setLevelValidDate(DateTimeUtil.getNow());
            List<LevelDef> levelDeflist = getLevelDef(LevelDef.NOVA, levelType);
            for (LevelDef levelDef : levelDeflist)
            {
                int period = levelDef.getPeriod();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(DateTimeUtil.getNow());
                calendar.add(Calendar.MONTH, period);
                customerLevelChgHis.setLevelExpiredDate(new Timestamp(calendar.getTimeInMillis()));
            }
            customerLevelChgHis.setSts(CustomerLevelChgHis.STS_VALID);
            customerLevelChgHis.setCreateDate(DateTimeUtil.getNow());
            updateCustomer(customer);
            saveCustomerLevelChgHis(customerLevelChgHis);
            return "you downgrade from Nova to Spark";
            
        }
        else if (levelId== LevelDef.AKIRA)
        {
            customer.setCustId(custId);
            customer.setLevelId(LevelDef.NOVA);
            customer.setLevelValidDate(DateTimeUtil.getNow());
            
            CustomerLevelChgHis customerLevelChgHis = new CustomerLevelChgHis();
            customerLevelChgHis.setCustId(custId);
            customerLevelChgHis.setOrgLevelId(LevelDef.AKIRA);
            customerLevelChgHis.setCurLevelId(LevelDef.NOVA);
            customerLevelChgHis.setModifyDate(DateTimeUtil.getNow());
            customerLevelChgHis.setLevelValidDate(DateTimeUtil.getNow());
            List<LevelDef> levelDeflist = getLevelDef(LevelDef.NOVA, levelType);
            for (LevelDef levelDef : levelDeflist)
            {
                int period = levelDef.getPeriod();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(DateTimeUtil.getNow());
                calendar.add(Calendar.MONTH, period);
                customerLevelChgHis.setLevelExpiredDate(new Timestamp(calendar.getTimeInMillis()));
                customer.setLevelExpiredDate(new Timestamp(calendar.getTimeInMillis()));
            }
            customerLevelChgHis.setSts(CustomerLevelChgHis.STS_VALID);
            customerLevelChgHis.setCreateDate(DateTimeUtil.getNow());
            updateCustomer(customer);
            saveCustomerLevelChgHis(customerLevelChgHis);
            return "you downgrade from Alira to Nova";
        }
        return null;
    }
    
    public List<Level> getLevel(long custId, int levelType)
    {
        List<Level> levellist = new ArrayList<Level>();
        Level level = new Level();
        Customer customer = getCustomer(custId);
        int levelId = customer.getLevelId();
        List<LevelDef> levelDeflist = getLevelDef(levelId, levelType);
        if (ValidateUtil.isNotEmpty(levelDeflist))
        {
            for (LevelDef levelDef : levelDeflist)
                level.setLevelDef(levelDef);
        }
        List<LevelRuleMapping> levelRuleMappingList = getLevelRuleMapping(levelId);
        for (LevelRuleMapping levelRuleMapping : levelRuleMappingList)
        {
            int levelRuleId = levelRuleMapping.getLevelRuleId();
            List<LevelRule> levelRulelist = getLevelRule(levelRuleId);
            for (LevelRule levelRule : levelRulelist)
                level.getLevelRule().add(levelRule);
            levellist.add(level);
        }
        return levellist;
    }
    
    public void updateCustomerLevel(Customer customer)
    {
        
    }
    
    public List<LevelRule> getLevelRule(int levelRuleId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(LevelRule.class);  
        if(ValidateUtil.isEmpty(levelRuleId))
            criteria.add(Restrictions.eq("levelRuleId", levelRuleId));
        return levelRepository.getLevelRule(criteria);
    }
    
    public int getCustomerLevel(long custId)
    {
        return getCustomer(custId).getLevelId();
    }
    
    public void saveLevelDef(LevelDef levelDef)
    {
        levelRepository.saveLevelDef(levelDef);
    }

}
