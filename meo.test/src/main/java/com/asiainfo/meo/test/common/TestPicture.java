package com.asiainfo.meo.test.common;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.user.app.model.entity.User;
//import org.apache.http.Header;
// import org.apache.http.HttpResponse;
// import org.apache.http.client.HttpClient;
// import org.apache.http.client.methods.HttpPost;
// import org.apache.http.entity.StringEntity;
// import org.apache.http.impl.client.DefaultHttpClient;
// import org.apache.http.params.BasicHttpParams;
// import org.apache.http.params.HttpParams;

public class TestPicture
{
    @Test
    public void testPicture() throws Exception
    {
        // File f = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg");
        //
        //
        // String value = Base64.getEncoder().encodeToString(FileCopyUtils.copyToByteArray(f));
        // File f2 = new File("1.jpg");
        // FileCopyUtils.copy(Base64.getDecoder().decode(value), f2);
        System.out.println(Long.MAX_VALUE);
        long start = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMDDmmHHss");
        Date d = new Date();
        for (int i = 0; i < 1000000; i++ )
        {
            String s = simpleDateFormat.format(d);
        }
        System.out.println(System.currentTimeMillis() - start);
        
        AtomicInteger i = new AtomicInteger(Integer.MAX_VALUE);
        System.out.println(i.incrementAndGet());
        System.out.println(Integer.MAX_VALUE);
    }
    
    @Test
    public void testUpload()
    {
        String url = "http://localhost:8080/imint/fileUpload";
        String filePath = "1.jpg";
        
        RestTemplate rest = new RestTemplate();
        // Set the request factory.
        // IMPORTANT: This section I had to add for POST request. Not needed for GET
        FileSystemResource resource = new FileSystemResource(new File(filePath));
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        
        param.add("file", resource);
        param.add("name", "Desert.jpg");
        
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(param);
        ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.POST, httpEntity, String.class);
        System.out.println(responseEntity.getBody());
        
        String string = rest.postForObject(url, param, String.class);
        System.out.println(string);
    }
    
    @Test
    public void testPath()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMDD");
        String value = simpleDateFormat.format(new Date());
        System.out.println(value);
        int i = Integer.valueOf(value);
        long l = i << 8;
        System.out.println(l);
        System.out.println((char) 61);
        System.out.println((char) 38);
        System.out.println((char) 37);
        System.out.println((char) 43);
        
        System.out.println((char) 13);
        System.out.println((char) 10);
        System.out.println((char) 32);
        System.out.println((char) 9);
        System.out.println((char) 58);
        System.out.println((char) 59);
        System.out.println((char) 63);
    }
    
    @Test
    public void testJson() throws Exception, IOException
    {
        String url = "http://localhost:8080/meo/rest/users/user/4?method=addFriend&accesstoken=c6d69644-7461-4332-9615-9c69e02cb94b&timestamp=1421316752364&format=json&v=1&mobileNo=13100010&sign=3743a65bd805877e023afee3e50ec5de";
        // RestTemplate rest = new RestTemplate();
        // HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.APPLICATION_JSON);
        // List<HttpMessageConverter<?>> list = new ArrayList<HttpMessageConverter<?>>();
        // list.add(new MappingJacksonHttpMessageConverter());
        // rest.setMessageConverters(list);
        //
        //
        // String JSONInput = ("{" +
        // "  \"firstName\": \"Anouska\"," +
        // "  \"lastName\": \"Williams\"," +
        // "  \"email\": \"Anouska@place.com\"," +
        // "  \"cardBin1\": 123456768," +
        // "  \"cardBin2\": 123456789," +
        // "  \"language\": \"Spanish\"," +
        // "  \"country\": \"Mexico\"," +
        // "  \"product\": \"shit\"," +
        // "  \"telephoneNumber\": 447869995262," +
        // "  \"termsAndConditions\": \"true\"" +
        // "}");
         User u = new User();
         u.setUserId(1001);
         u.setUserName("raymond");
        // HttpEntity request= new HttpEntity(u, headers);
        // ResponseEntity<String> result = rest.postForEntity(url, request, String.class);
        // System.out.println(result);
        
         HttpPost post = new HttpPost(url);
        // HttpParams params1 = new BasicHttpParams();
        // User1 u = new User1();
        // u.setUserId(1001);
        // // params1.setParameter("user", u);
         HttpClient client = new DefaultHttpClient();
        // // post.setParams(params1);
        //
        // // List<NameValuePair> params = new ArrayList<NameValuePair>();
        // // params.add(new BasicNameValuePair("name", JsonUtil.writeObjectAsString(u)));
        // // params.add(new BasicNameValuePair("user", JsonUtil.writeObjectAsString(u)));
        // //// // 编码格式转换
        // // UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
        // Map<String,Object> m = new HashMap<String,Object>();
        // m.put("user", u);
        // // Address a = new Address();
        // // m.put("address", a);
         String value = JsonUtil.writeObjectAsString(u);
        // System.out.println(value);
         StringEntity entity = new StringEntity(value);
        // // StringEntity entity = new StringEntity("firstname=abcde&lastname=fghi");
        // // 传入请求体
         post.setEntity(entity);
        // Header[] headers = post.getAllHeaders();
        // for(Header header : headers)
        // {
        // System.out.println(header.getName() + " : " + header.getValue());
        // }
        // post.setHeader("content-type", "application/json");
         HttpResponse response = client.execute(post);
         System.out.println(response.getStatusLine().getStatusCode());
        
        // HttpGet get = new HttpGet(url);
    }
    
    @Test
    public void testReference()
    {
        User u = new User();
        u.setUserId(1001);
        createUser(u);
        System.out.println(u.getUserId());
        
    }
    
    @Test
    public void testInteger()
    {
        String s = "1";
        System.out.println(Integer.parseInt(s));
        System.out.println(Timestamp.valueOf("1990-12-12 11:11:11"));
        System.out.println(Long.toHexString(Long.MAX_VALUE));
    }
    
    private void createUser(User u)
    {
        u = null;
    }
}
