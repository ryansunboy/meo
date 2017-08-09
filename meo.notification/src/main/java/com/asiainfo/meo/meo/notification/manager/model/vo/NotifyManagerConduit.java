package com.asiainfo.meo.meo.notification.manager.model.vo;

import java.util.ArrayList;
import java.util.List;

import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.meo.notification.business.NotifyCommonBean;
import com.asiainfo.meo.meo.notification.business.bean.NotifyBean;
import com.asiainfo.meo.meo.notification.manager.factory.NotifyManagerFactory;

public class NotifyManagerConduit
{
    private Class<? extends NotifyBean> clazz;
    
    private List<Object>                beanParams;
    
    private NotifyCommonBean            coomonBean;
    
    private boolean                     isTrigger = true;
    
    public NotifyManagerConduit(Class<? extends NotifyBean> clazz, NotifyCommonBean coomonBean)
    {
        this.clazz = clazz;
        this.coomonBean = coomonBean;
    }
    
    public NotifyManagerConduit(Class<? extends NotifyBean> clazz)
    {
        super();
        this.clazz = clazz;
    }
    
    public NotifyManagerConduit(Long userId, Long actionId, Long userType, Integer notifyType)
    {
        NotifyBean notifyBean = NotifyManagerFactory.getNotifyBean(notifyType);
        if (notifyBean== null)
        {
            throw new IllegalArgumentException("cant not find notify business bean exception ");
        }
        this.clazz = notifyBean.getClass();
        this.coomonBean = new NotifyCommonBean(userId, userType, actionId, notifyType);
    }
    
    public NotifyCommonBean getCoomonBean()
    {
        return coomonBean;
    }
    
    public void setCoomonBean(NotifyCommonBean coomonBean)
    {
        this.coomonBean = coomonBean;
    }
    
    public void setClazz(Class<? extends NotifyBean> clazz)
    {
        this.clazz = clazz;
    }
    
    public Class<? extends NotifyBean> getClazz()
    {
        return clazz;
    }

    public boolean isTrigger()
    {
        return isTrigger;
    }
    
    public void setTrigger(boolean isTrigger)
    {
        this.isTrigger = isTrigger;
    }
    
    public void addParams(Object params)
    {
        if (ValidateUtil.isEmpty(beanParams))
        {
            beanParams = new ArrayList<Object>();
        }
        beanParams.add(params);
        
    }
    
    public List<Object> getBeanParams()
    {
        return beanParams;
    }
    
    public void setBeanParams(List<Object> beanParams)
    {
        this.beanParams = beanParams;
    }
    
}
