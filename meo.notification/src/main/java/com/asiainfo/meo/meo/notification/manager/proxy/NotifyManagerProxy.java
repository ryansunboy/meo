package com.asiainfo.meo.meo.notification.manager.proxy;

import com.asiainfo.meo.meo.notification.manager.model.vo.NotifyManagerConduit;



public interface NotifyManagerProxy
{
    public Object startNotify(NotifyManagerConduit conduit);
}
