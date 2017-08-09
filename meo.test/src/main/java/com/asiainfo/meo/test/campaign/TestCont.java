package com.asiainfo.meo.test.campaign;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.asiainfo.meo.common.core.utils.SignUtil;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.test.utils.TestUtil;

public class TestCont
{
    @Test
    public void test1()
    {
        List<Long> list1 = null;
        List<Long> list2 = new ArrayList<Long>();
        List<Long> list3 = new ArrayList<Long>();
        list3.add(1L);
        
        if (ValidateUtil.isNotEmpty(list1))
        {
            System.out.println("list1");
        }
        if (ValidateUtil.isNotEmpty(list2))
        {
            System.out.println("list2");
        }
        if (ValidateUtil.isNotEmpty(list3))
        {
            System.out.println("list3");
        }
    }
    
    
  //测试创建客户基本信息
    @Test
    public void testCreateCustBasicInfoPost() throws IOException
    {
        
       String URL = "http://localhost:8080/meo/rest/campaign/customer";
       String queryString = "method=meo.campaign.cust.hot.get&accessToken=e9573409afae427ab91f73aba76d6dca&timestamp=1430980771444&format=json&v=1&categoryId=1&pageSize=5&pageNo=1";
       String signature = SignUtil.md5Signature(TestUtil.stringToMap(queryString), "j98pjz71", null);
       System.out.println(URL+"?"+queryString+"&signature="+signature);
    }
    
  //测试创建客户基本信息
    @Test
    public void testCreateCustBasicInfoPost2() throws IOException
    {
        
       String URL = "http://localhost:8080/meo/rest/campaign/customer";
       String queryString = "method=meo.campaign.cust.new.get&accessToken=e9573409afae427ab91f73aba76d6dca&timestamp=1430982256382&format=json&v=1&categoryId=1&pageSize=5&pageNo=1";
       String signature = SignUtil.md5Signature(TestUtil.stringToMap(queryString), "j98pjz71", null);
       System.out.println(URL+"?"+queryString+"&signature="+signature);
    }
    
    
  //测试创建客户基本信息
    @Test
    public void testCreateCustBasicInfoPost3() throws IOException
    {
        
       String URL = "http://localhost:8080/meo/rest/campaign/customer";
       String queryString = "method=meo.activity.coin.earn.get&accessToken=8b32d4a23ec24a56a3d73bde981f89fd&timestamp=1431077759945&format=json&v=1&pageSize=10&pageNo=1";
       String signature = SignUtil.md5Signature(TestUtil.stringToMap(queryString), "a3mxnm1t", null);
       System.out.println(URL+"?"+queryString+"&signature="+signature);
    }
}
