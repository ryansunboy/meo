package com.asiainfo.meo.customer.level.app.bo;

import java.util.List;

import com.asiainfo.meo.customer.level.app.model.entity.CustomerLevelChgHis;
import com.asiainfo.meo.customer.level.app.model.entity.LevelDef;
import com.asiainfo.meo.customer.level.app.model.entity.LevelRule;
import com.asiainfo.meo.customer.level.app.model.entity.LevelRuleMapping;
import com.asiainfo.meo.customer.level.app.model.vo.Level;
import com.asiainfo.meo.customer.profile.app.model.entity.Customer;

public interface LevelBO
{
    /**
     * @Description: (根据levelId和level类型查找等级定义)
     * @Description: (get level def from levelId and level)
     * @modifyReason:
     * @author lill
     * @param levelId
     * @return
     */
    List<LevelDef> getLevelDef(int levelId, int levelType);
    
    /**
     * @Description: (根据levelRuleId获取level权限)
     * @Description: (get level root from levelRuleId)
     * @modifyReason:
     * @author lill
     * @param levelRuleId
     * @return
     */
    List<LevelRule> getLevelRule(int levelRuleId);
    
    /**
     * @Description: (升级)
     * @Description: (up level)
     * @modifyReason:
     * @author lill
     * @param custId
     */
    String updateUpLevel(long custId, int levelType);
    
    /**
     * @Description: (降级)
     * @Description: (down level)
     * @modifyReason:
     * @author lill
     * @param custId
     */
    String updateDownLevel(long custId, int levelType);
    
    /**
     * @Description: (根据levelId取得所有levelRule表的数组)
     * @Description: (get levelRule list from levelId)
     * @modifyReason:
     * @author lill
     * @param levelId
     * @return
     */
    List<LevelRuleMapping> getLevelRuleMapping(int levelId);
    
    /**
     * @Description: (查询单个客户的等级及权限)
     * @Description: (get the level and root of personal )
     * @modifyReason:
     * @author lill
     * @param levelId
     * @return
     */
    List<Level> getLevel(long custId, int levelType);
    
    /**
     * @Description: (更新客户等级)
     * @Description: (update level of customer)
     * @modifyReason:
     * @author lill
     * @param customer
     */
    void updateCustomerLevel(Customer customer);
    
    /**
     * @Description: (取得用户等级)
     * @Description: (get level of customer)
     * @modifyReason:
     * @author lill
     * @param custId
     */
    int getCustomerLevel(long custId);
    
    CustomerLevelChgHis getCustomerLevelChgHis(long custId);
    
    void saveCustomerLevelChgHis(CustomerLevelChgHis customerLevelChgHis);
    
    void saveLevelDef(LevelDef levelDef);
}
