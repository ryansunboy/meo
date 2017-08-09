package com.asiainfo.meo.web.notification.model.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

public class UIActionNotificationVO implements Serializable
{

    /**                                                                           
      * @Fields serialVersionUID : (用一句话描述这个变量表示什么)                                                                                                                                                                                                                                  
      */
    private static final long serialVersionUID = 368198890345460722L;
    
    @Valid
    @NotNull(groups={Insert.class, Update.class})
    private UINotifyActionRelVO notification;
    
    private List<UINotificationTimeLimitVO> timeLimittList;
    
    private List<UINotificationNumLimitVO> countLimitList;

    public UINotifyActionRelVO getNotification()
    {
        return notification;
    }

    public void setNotification(UINotifyActionRelVO notification)
    {
        this.notification = notification;
    }

    public List<UINotificationTimeLimitVO> getTimeLimittList()
    {
        return timeLimittList;
    }

    public void setTimeLimittList(List<UINotificationTimeLimitVO> timeLimittList)
    {
        this.timeLimittList = timeLimittList;
    }

    public List<UINotificationNumLimitVO> getCountLimitList()
    {
        return countLimitList;
    }

    public void setCountLimitList(List<UINotificationNumLimitVO> countLimitList)
    {
        this.countLimitList = countLimitList;
    }
}
