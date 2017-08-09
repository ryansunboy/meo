package com.asiainfo.meo.activity.manager.model.vo;


import java.util.ArrayList;
import java.util.List;

import com.asiainfo.meo.activity.business.bean.ActivityBean;
import com.asiainfo.meo.activity.manager.factory.ActivityManagerFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;

public class ActivityManagerConduit
{

    private Class<? extends ActivityBean> clazz;
    private ActivityManagerBundle bundle;
    private List<Object> beanParams;
    private boolean isActivity=true;
    public ActivityManagerConduit(Class<? extends ActivityBean> clazz, ActivityManagerBundle bundle)
    {
        this.clazz = clazz;
        this.bundle = bundle;
    }
    
    public ActivityManagerConduit(Class<? extends ActivityBean> clazz)
    {
        super();
        this.clazz = clazz;
    }
    
    public ActivityManagerConduit(Long custId,Long actionId,Long entityId,Integer entityType)
    {
        ActivityBean activityBean = ActivityManagerFactory.getActivityBean(actionId);
        if(ValidateUtil.isNull(activityBean)){
            throw new IllegalArgumentException("cant not find activity business bean exception ");
        }
       this.clazz=activityBean.getClass();
       this.bundle=new ActivityManagerBundle(custId, actionId,entityId,entityType);
    }

    public Class<? extends ActivityBean> getClazz()
    {
        return clazz;
    }
    public void setClazz(Class<ActivityBean> clazz)
    {
        this.clazz = clazz;
    }
    public ActivityManagerBundle getBundle()
    {
        return bundle;
    }
    public void setBundle(ActivityManagerBundle bundle)
    {
        this.bundle = bundle;
    }
    

    public boolean isActivity()
    {
        return isActivity;
    }

    public void setActivity(boolean isActivity)
    {
        this.isActivity = isActivity;
    }


    public void addParams(Object params)
    {
        if (ValidateUtil.isEmpty(beanParams))
        {
            beanParams=new ArrayList<Object>();
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
