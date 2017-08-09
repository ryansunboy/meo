package com.asiainfo.meo.test.cm;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.customer.setting.app.bo.CustomerSettingBO;
import com.asiainfo.meo.customer.setting.app.model.entity.CustomerSetting;
import com.asiainfo.meo.customer.setting.app.model.vo.SettingVO;

public class TestCustomerSettingDB
{
    Logger log = Logger.getLogger(this.getClass());
    
    @Test
    public void testSaveCustomerSetting()
    {
        CustomerSetting customerSetting = new CustomerSetting();
        // customerSetting.setCustId(100000026000L);
        customerSetting.setSettingId(2);
        customerSetting.setSettingValue(1);
        CustomerSettingBO customerSettingBO = ServiceLocatorFactory.getService(CustomerSettingBO.class);
        customerSettingBO.saveCustomerSetting(customerSetting);
    }
    
    // @Test
    // public void testSaveSettingDef(){
    // SettingDef settingDef = new SettingDef();
    // settingDef.setSettingName("off");
    // settingDef.setSettingDesc("off");
    // settingDef.setSts(0);
    // settingDef.setSettingType(3);
    // settingDef.setDefaultValue(2);
    // CustomerSettingBO customerSettingBO =
    // ServiceLocatorFactory.getService(CustomerSettingBO.class);
    // customerSettingBO.saveSettingDef(settingDef);
    // }
    
    /*
     * @Test public void testGetCustomerSetting() { // CustomerSetting customerSetting = new CustomerSetting(); Map<String,
     * Object> map = new HashMap<String, Object>(); map.put("custId", 100000024000L); // map.put("settingId", 100000004000L);
     * CustomerSettingBO customerSettingBO = ServiceLocatorFactory.getService(CustomerSettingBO.class); List<CustomerSetting>
     * customerSettingList = customerSettingBO.getCustomerSetting(map); // Map<String, Object> map1 = new HashMap<String,
     * Object>(); for (CustomerSetting customerSetting : customerSettingList) { // map1.put("settingId",
     * customerSetting.getSettingId()); // List<SettingDef> list = customerSettingBO.getSettingDef(map1); // for(SettingDef
     * settingDef : list){ // SettingDef settingDef1 = new SettingDef(); //
     * settingDef1.setSettingName(settingDef.getSettingName()); // settingDef1.setSettingType(settingDef.getSettingType()); //
     * System.out.println(settingDef1.getSettingName()+", "+settingDef1.getSettingType()); // }
     * System.out.println(customerSetting.getCustId()+ ","+ customerSetting.getDefId()); } }
     */
    /*
     * @Test public void testGetSettingDef(){ Map<String, Object> map = new HashMap<String, Object>(); // map.put("custId",
     * 100000024000L); // map.put("settingId", 100000004000L); CustomerSettingBO customerSettingBO =
     * ServiceLocatorFactory.getService(CustomerSettingBO.class); List<SettingDef> defList = customerSettingBO.getSettingDef(map);
     * // List<SettingDef> list = new ArrayList<SettingDef>(); // for(SettingDef settingDefList : defList){ // settingDefList.g //
     * } CustomerSetting customerSetting=new CustomerSetting(); Setting setting = new Setting();
     * setting.setCustomerSetting(customerSetting); setting.setSettingDef(defList); // for(SettingDef settingDefList : defList ){
     * // list.add(settingDefList); // setting.setSettingDef(list); for (SettingDef settingDef :defList) {
     * System.out.println(setting.getCustomerSetting().getSettingId()+","+settingDef.getSettingId()); } // SettingDef settingDef =
     * new SettingDef(); // settingDef.setSettingId(settingDefList.getSettingId()); //
     * settingDef.setSettingName(settingDefList.getSettingName()); // settingDef.setSettingType(settingDefList.getSettingType());
     * // settingDef.setSettingDesc(settingDefList.getSettingDesc()); //
     * System.out.println(settingDef.getSettingId()+","+settingDefList.getSettingId()); // Setting setting = new Setting(); //
     * setting.setSettingDef(settingDef); // System.out.println(setting.getSettingDef()+","+setting.getCustomerSetting()); //
     * settingDef.setSettingId(settingDefList.getSettingId()); // System.out.println(settingDef.getSettingId());; // } }
     */
    
    @Test
    public void getSetting() throws Exception
    {
        // Map<String, Object> map = new HashMap<String, Object>();
        // map.put("custId", 100000025000L);
        CustomerSettingBO customerSettingBO = ServiceLocatorFactory.getService(CustomerSettingBO.class);
        
        List<SettingVO> settingVOlist = customerSettingBO.getSetting(100000026001L, 2);
        
        // List<SettingVO> settingVOlist = customerSettingBO.getSetting(100000026001L,null);
        
        for (SettingVO settingVO : settingVOlist)
        {
            log.info(JsonUtil.writeObjectAsString(settingVO));
        }
        // List<SettingAndSettingDef> settingAndSettingDef = customerSettingBO.getSetting(100000026000L);
        // for(int i = 0; i< settingAndSettingDef.size(); i++ ){
        // if( settingAndSettingDef.get(i).getCustomerSettingList().size() > 0){
        // List<CustomerSetting> customerSettingList = settingAndSettingDef.get(i).getCustomerSettingList();
        // for(CustomerSetting customerSetting : customerSettingList){
        // log.info(JsonUtil.writeObjectAsString(customerSetting));
        // }
        // }if(settingAndSettingDef.get(i).getSettingDefList().size() > 0){
        // List<SettingDef> settingDefList = settingAndSettingDef.get(i).getSettingDefList();
        // for(SettingDef settingDef : settingDefList){
        // log.info(JsonUtil.writeObjectAsString(settingDef));
        // }
        // }
        // }
    }
    
    @Test
    public void updateSetting() throws IOException
    {
        CustomerSettingBO customerSettingBO = ServiceLocatorFactory.getService(CustomerSettingBO.class);
        long custId = 100000012000L;
        // int settingId = 1;
        // int settingValue = 2;
        int settingId = 2;
        int settingValue = 2;
        customerSettingBO.updateCustomerSetting(custId, settingId, settingValue);
    }
    
    @Test
    public void initCustomerSeeting()
    {
        CustomerSettingBO customerSettingBO = ServiceLocatorFactory.getService(CustomerSettingBO.class);
        long custId = 100000015000L;
        customerSettingBO.createCustomerSetting(custId);
    }
    
    // // @Test
    // public void getCustomerSetting()
    // {
    // Map<String, Object> map = new HashMap<String, Object>();
    // map.put("custId", 100000024000L);
    // CustomerSettingBO customerSettingBO = ServiceLocatorFactory.getService(CustomerSettingBO.class);
    // System.out.println(customerSettingBO.getCustomerSetting(map));
    // ;
    //
    // }
    
    // @Test
    // public void saveCustIdSetting(){
    // List<CustSetting> list = new ArrayList<CustSetting>();
    // CustSetting custSetting = new CustSetting();
    // List<CustomerSetting> custSettingList = new ArrayList<CustomerSetting>();
    //
    // custSetting.setCustId(100000026000L);
    //
    // CustomerSetting customerSetting = new CustomerSetting();
    // customerSetting.setSettingId(1L);
    // customerSetting.setSettingValue(2);
    // custSettingList.add(customerSetting);
    // // CustomerSetting customerSetting1 = new CustomerSetting();
    // // customerSetting1.setSettingId(2L);
    // // customerSetting1.setSettingValue(2);
    // // custSettingList.add(customerSetting1);
    //
    // custSetting.setCustomerSettingList(custSettingList);
    //
    // list.add(custSetting);
    // CustomerSettingBO customerSettingBO =
    // ServiceLocatorFactory.getService(CustomerSettingBO.class);
    // customerSettingBO.saveCustIdSetting(list);
    // }
    
}
