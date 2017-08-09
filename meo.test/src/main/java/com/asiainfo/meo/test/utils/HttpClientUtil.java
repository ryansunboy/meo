package com.asiainfo.meo.test.utils;

import java.io.File;
import java.io.IOException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import com.asiainfo.meo.common.core.utils.JsonUtil;

public class HttpClientUtil
{
    static CloseableHttpClient httpclient = null;  
    public static String post(String url, Object object) throws Exception  {   
       
        HttpPost method = new HttpPost(url);
        System.out.println(url);
        method.setHeader("Content-Type", "application/json;charset=utf-8");
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(new File(".keystore"), "meouser".toCharArray(), new TrustSelfSignedStrategy()).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslContext, 
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();  


        String value = JsonUtil.writeObjectAsString(object);
         StringEntity entity = new StringEntity(value);
        // // 传入请求体
         method.setEntity(entity);
        
         CloseableHttpResponse response = httpclient.execute(method);
          HttpEntity body = response.getEntity();
          response.getStatusLine().getStatusCode();
          System.out.println(response.getStatusLine());
          String message = EntityUtils.toString(body);
          response.close();
          httpclient.close();
          
        return message;  
    }  
    
    public static String get(String url) throws IOException{
       HttpGet method = new HttpGet(url);
       httpclient=HttpClients.createDefault();  
       CloseableHttpResponse response = httpclient.execute(method);
       HttpEntity body = response.getEntity();
       String message = EntityUtils.toString(body);
         return message;  
    }
   

}
