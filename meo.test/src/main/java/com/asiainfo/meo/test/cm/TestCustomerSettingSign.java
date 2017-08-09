package com.asiainfo.meo.test.cm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.validation.constraints.NotNull;

import org.junit.Test;

import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.common.core.utils.SignUtil;
import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;
import com.asiainfo.meo.customer.setting.app.contant.SettingErrorContant;
import com.mysql.fabric.xmlrpc.base.Array;

public class TestCustomerSettingSign
{
    private static final String url       = "http://localhost:8080/meo/rest/customers";
    
    private static final String secretKey = "4xhstsuz";
    
    class CustomerSettingVO
    {
        private Integer settingId;
        private Integer settingValue;
        
        public Integer getSettingId()
        {
            return settingId;
        }
        
        public void setSettingId(Integer settingId)
        {
            this.settingId = settingId;
        }
        
        public Integer getSettingValue()
        {
            return settingValue;
        }
        
        public void setSettingValue(Integer settingValue)
        {
            this.settingValue = settingValue;
        }
        
    }
    
    @Test
    public void testGet()
    {
        System.out.println(DateTimeUtil.getCurrentTimeMillis());
        
        
        String queryString = "method=getCustSetInfo&accessToken=670eefb8062141138de6ff4767dc9eab&timestamp=1427978919182&format=json&v=1&settingType=1";
        
        String signature = SignUtil.md5Signature(stringToMap(queryString), secretKey, null);
        
        System.out.println(signature);
        
        System.out.println(url+ "?"+ queryString+ "&signature="+ signature);
    }
    
    @Test
    public void testPost() throws IOException
    {
        String queryString = "method=modifyCustSet&accessToken=670eefb8062141138de6ff4767dc9eab&timestamp=1427978919182&format=json&v=1";
        // {"userName":"raymond","userId":1001,"address":[]}
        String bodyString = JsonUtil.writeObjectAsString(createCustomer());
        System.out.println(bodyString);
        String signature = SignUtil.md5Signature(stringToMap(queryString), secretKey, bodyString);
        System.out.println(signature);
        System.out.println(url+ "?"+ queryString+ "&signature="+ signature);
    }
    
    private TreeMap<String, String> stringToMap(String queryString)
    {
        TreeMap<String, String> params = new TreeMap<String, String>();
        String[] keyValues = queryString.split("&");
        for (String keyValue : keyValues)
        {
            String[] kv = keyValue.split("=");
            params.put(kv[0], kv[1]);
        }
        return params;
    }
    
    private List<CustomerSettingVO> createCustomer()
    {
        List<CustomerSettingVO> custSettingVOList=new ArrayList<CustomerSettingVO>();
       
        CustomerSettingVO custSettingVO1 = new CustomerSettingVO();
        CustomerSettingVO custSettingVO2 = new CustomerSettingVO();
        
        custSettingVO1.setSettingId(2);
        custSettingVO1.setSettingValue(2);
        
        custSettingVO2.setSettingId(4);
        custSettingVO2.setSettingValue(1);
        
        custSettingVOList.add(custSettingVO1);
        
        custSettingVOList.add(custSettingVO2);
        
        return custSettingVOList;
    }
}
