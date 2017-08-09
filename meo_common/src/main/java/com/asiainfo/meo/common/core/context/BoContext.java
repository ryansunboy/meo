package com.asiainfo.meo.common.core.context;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.asiainfo.meo.common.core.utils.SequenceUtil;

public class BoContext
{
    private static final ThreadLocal<BoContext> CONTEXT = new ThreadLocal<BoContext>()
    {
        protected BoContext initialValue()
        {
            return new BoContext();
        }
    };
    
    public static BoContext getBoContext()
    {
        return CONTEXT.get();
    }
    
    public static void remove()
    {
        CONTEXT.remove();
    }
    
    private String globalTransactionId;
    
    private String url;
    
    private String queryString;
    
    private Map<String, Object> attachments = new HashMap<String, Object>();;
    
    private Map<Class<?>, Object> contents = new HashMap<Class<?>, Object>();
    
    private String messageBody;
    
    private Object responseParams;
    
    private String clientIp;
    
    private Locale locale;
    
    private String schema;
    
    private String httpMethod;
    
    private long userId;
    
    public BoContext()
    {
        this.globalTransactionId = SequenceUtil.getGlobalSequence(); // TODO
    }
    
    public String getGlobalTransactionId()
    {
        return globalTransactionId;
    }
    
    public void setGlobalTransactionId(String globalTransactionId)
    {
        this.globalTransactionId = globalTransactionId;
    }
    
    public String getUrl()
    {
        return url;
    }
    
    public void setUrl(String url)
    {
        this.url = url;
    }
    
    public String getQueryString()
    {
        return queryString;
    }
    
    public void setQueryString(String queryString)
    {
        this.queryString = queryString;
    }
    
    public Map<String, Object> getAttachments()
    {
        return attachments;
    }
    
    public void setAttachments(Map<String, Object> attachments)
    {
        this.attachments = attachments;
    }
    
    public String getMessageBody()
    {
        return messageBody;
    }
    
    public void setMessageBody(String messageBody)
    {
        this.messageBody = messageBody;
    }
    
    public Object getResponseParams()
    {
        return responseParams;
    }
    
    public void setResponseParams(Object responseParams)
    {
        this.responseParams = responseParams;
    }
    
    public String getClientIp()
    {
        return clientIp;
    }
    
    public void setClientIp(String clientIp)
    {
        this.clientIp = clientIp;
    }
    
    public Locale getLocale()
    {
        return locale;
    }
    
    public void setLocale(Locale locale)
    {
        this.locale = locale;
    }
    
    public String getSchema()
    {
        return schema;
    }
    
    public void setSchema(String schema)
    {
        this.schema = schema;
    }
    
    public long getUserId()
    {
        return userId;
    }
    
    public void setUserId(long userId)
    {
        this.userId = userId;
    }
    
    public Map<Class<?>, Object> getContents()
    {
        return contents;
    }
    
    public void setContents(Map<Class<?>, Object> contents)
    {
        this.contents = contents;
    }
    
    public <T> T  getContent(Class<T> type)
    {
        return (T)this.contents.get(type);
    }
    
    public  void  setContent(Class<?> type,Object v)
    {
         this.contents.put(type, v);
    }
    
    public String getHttpMethod()
    {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod)
    {
        this.httpMethod = httpMethod;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("request id [").append(this.globalTransactionId).append("], ")
          .append("httpMethod").append(this.httpMethod).append("], ")
          .append("schema[").append(this.schema).append("], ")
          .append("url [").append(this.url).append("], ")
          .append("query string[").append(this.queryString).append("], ")
          .append("message body").append(this.messageBody).append("], ")
          .append("userId").append(this.userId).append("], ");
          
        return sb.toString();
    }
}
