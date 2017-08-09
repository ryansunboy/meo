package com.asiainfo.meo.test.cm;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.customer.level.app.bo.LevelBO;
import com.asiainfo.meo.customer.level.app.model.entity.CustomerLevelChgHis;
import com.asiainfo.meo.customer.level.app.model.entity.LevelDef;
import com.asiainfo.meo.customer.level.app.model.entity.LevelRule;
import com.asiainfo.meo.customer.level.app.model.entity.LevelRuleMapping;
import com.asiainfo.meo.customer.level.app.model.vo.Level;


public class TestLevelDB
{
    Logger log = Logger.getLogger(this.getClass());
//    @Test
    public void testLevelRuleMapping(){
        LevelRuleMapping levelRuleMapping = new LevelRuleMapping();
        LevelBO levelBO = ServiceLocatorFactory.getService(LevelBO.class);
      
        System.out.println(  levelBO.getLevelRuleMapping(1));
    }
    
    
//   @Test
   public void testGetLevelDef(){
      
       LevelBO levelBO = ServiceLocatorFactory.getService(LevelBO.class);
       List<LevelDef> list = levelBO.getLevelDef(1, 1);
       for(LevelDef levelDef : list){
          System.out.println( levelDef.getMaxValue());
       }
//       DetachedCriteria criteria = DetachedCriteria.forClass(LevelDef.class).add(Restrictions.eq("",))
//       DetachedCriteria.forClass(LevelDef.class).
//       levelBO.getLevelDef(levelId);
   }
   
   @Test
   public void testGetLevel() throws IOException{
       
       LevelBO levelBO = ServiceLocatorFactory.getService(LevelBO.class);
       List<Level> levellist = levelBO.getLevel(100000012000L, 1);
    
       for(Level level : levellist){
           LevelDef levelDef = level.getLevelDef();
           log.info(JsonUtil.writeObjectAsString(levelDef));
           List<LevelRule> levelRulelist = level.getLevelRule();
           for(LevelRule levelRule : levelRulelist){
             log.info(JsonUtil.writeObjectAsString(levelRule));
           }
       }
   }
//   @Test
   public void getLevelRule(){
       
       Map<String, Object> map = new HashMap<String, Object>();
       map.put("levelRuleId", 1);
       map.put("levelCode", "2");
       LevelBO levelBO = ServiceLocatorFactory.getService(LevelBO.class);
       List<LevelRule> levelRulelist = levelBO.getLevelRule(map);
       for(LevelRule levelRule : levelRulelist){
          System.out.println( levelRule.getLevelRuleId() +","+levelRule.getLevelValue());
       }
   }
   
//   @Test
   public void testSaveCustomerLevelChgHis(){
       CustomerLevelChgHis customerLevelChgHis = new CustomerLevelChgHis();
       customerLevelChgHis.setCustId(100000028000L);
       customerLevelChgHis.setCurLevelId(2);
       customerLevelChgHis.setOrgLevelId(1);
       customerLevelChgHis.setLevelExpiredDate(DateTimeUtil.getNow());
       customerLevelChgHis.setModifyDate(DateTimeUtil.getNow());
       customerLevelChgHis.setCreateDate(DateTimeUtil.getNow());
       customerLevelChgHis.setLevelValidDate(DateTimeUtil.getNow());
       customerLevelChgHis.setSts(0);
       LevelBO levelBO = ServiceLocatorFactory.getService(LevelBO.class);
       levelBO.saveCustomerLevelChgHis(customerLevelChgHis);
   }
   
   @Test
   public void testUpLevel(){
       LevelBO levelBO = ServiceLocatorFactory.getService(LevelBO.class);
       String str = levelBO.updateUpLevel(100000012000L, 1);
       System.out.println(str);
   }
   
   @Test
   public void testDownLevel(){
       LevelBO levelBO = ServiceLocatorFactory.getService(LevelBO.class);
       String str = levelBO.updateDownLevel(100000012000L, 1);
       System.out.println(str);
   }
   
   
//   @Test
   public void testTestLevelDB(){
       LevelBO levelBO = ServiceLocatorFactory.getService(LevelBO.class);
       LevelDef levelDef = new LevelDef();
       levelDef.setLevelId(4);
       levelDef.setLevelName("1");
       levelDef.setMinValue(1L);
       levelDef.setMaxValue(2L);
       levelDef.setOperatorId(0);
       levelDef.setModifyDate(DateTimeUtil.getNow());
       
       levelBO.saveLevelDef(levelDef);
   }
}
