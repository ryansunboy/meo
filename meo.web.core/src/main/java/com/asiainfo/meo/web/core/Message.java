package com.asiainfo.meo.web.core;

import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;

public class Message<T>
{
    private String requestId;
    
    private long doneTime;
    
    private ResponseError error;
    
    private T body;
    
    public Message(String requestId)
    {
        this.doneTime = DateTimeUtil.getCurrentTimeMillis();
        this.requestId = requestId;
    }
    
    public Message()
    {
        this(BoContext.getBoContext().getGlobalTransactionId());
    }
    
    public Message(T body)
    {
        this();
        this.body = body;
    }
    
    public ResponseError getError()
    {
        return error;
    }


    public void setError(ResponseError error)
    {
        this.error = error;
    }
    
    public String getRequestId()
    {
        return requestId;
    }


    public void setRequestId(String requestId)
    {
        this.requestId = requestId;
    }


    public long getDoneTime()
    {
        return doneTime;
    }


    public void setDoneTime(long doneTime)
    {
        this.doneTime = doneTime;
    }


    public T getBody()
    {
        return body;
    }
    
    public void setBody(T body)
    {
        this.body = body;
    }
}
