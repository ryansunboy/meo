package com.asiainfo.meo.meo.notification.email.app.model.vo;

import java.util.List;

public class NotifyEmailMessageVO
{
    private String senderAddress;
    private List<String> toAddress;
    private List<String> ccAddress;
    private List<String> bccAddress;
    private String subject;
    private String messages;
    
    public String getSenderAddress()
    {
        return senderAddress;
    }
    public void setSenderAddress(String senderAddress)
    {
        this.senderAddress = senderAddress;
    }
    public List<String> getToAddress()
    {
        return toAddress;
    }
    public void setToAddress(List<String> toAddress)
    {
        this.toAddress = toAddress;
    }
    public List<String> getCcAddress()
    {
        return ccAddress;
    }
    public void setCcAddress(List<String> ccAddress)
    {
        this.ccAddress = ccAddress;
    }
    public List<String> getBccAddress()
    {
        return bccAddress;
    }
    public void setBccAddress(List<String> bccAddress)
    {
        this.bccAddress = bccAddress;
    }
    public String getSubject()
    {
        return subject;
    }
    public void setSubject(String subject)
    {
        this.subject = subject;
    }
    public String getMessages()
    {
        return messages;
    }
    public void setMessages(String messages)
    {
        this.messages = messages;
    }
}
