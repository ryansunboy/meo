/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */                                                                              
 package com.asiainfo.meo.test.common;                                                                                                                                                                                                                                                                       

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.common.core.utils.SignUtil;
import com.asiainfo.meo.user.app.model.entity.User;
                                                                                                                                                                                                                                                                                              
 /**@Description: TODO(这里用一句话描述这个类的作用)                   
 * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
 * @Author AI                                                                                                                                                                                                                                                                           
 * @Date 2015-3-10                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
 */
public class TestSign
{
    private static final String url = "http://localhost:8080/meo/rest/users/user/4";
    
    private static final String secretKey = "20150310";
    
    @Test
    public void testGet()
    {
        String queryString = "method=getFriends&accesstoken=c6d69644-7461-4332-9615-9c69e02cb94b&timestamp=1421316752364&format=json&v=1&mobileNo=13100010&pagesize=1&limit=10";
        String signature = SignUtil.md5Signature(stringToMap(queryString), secretKey, null);
        System.out.println(signature);
    }
    
    @Test
    public void testPost() throws IOException
    {
        String queryString = "method=addFriend&accesstoken=c6d69644-7461-4332-9615-9c69e02cb94b&timestamp=1421316752364&format=json&v=1&mobileNo=13100010";
        //{"userName":"raymond","userId":1001,"address":[]}
        String bodyString = JsonUtil.writeObjectAsString(createUser());
        System.out.println(bodyString);
        String signature = SignUtil.md5Signature(stringToMap(queryString), secretKey, bodyString);
        System.out.println(signature);
    }
    
    private TreeMap<String,String> stringToMap(String queryString)
    {
        TreeMap<String,String> params = new TreeMap<String,String>();
        String[] keyValues = queryString.split("&");
        for(String keyValue : keyValues)
        {
            String[] kv = keyValue.split("=");
            params.put(kv[0], kv[1]);
        }
        return params;
    }
    
    private User createUser()
    {
        User u = new User();
        u.setUserId(1001);
        u.setUserName("raymond");
        return u;
    }
}
