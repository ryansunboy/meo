package com.asiainfo.meo.web.notification.model.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

public class UIActionNotificationListVO implements Serializable
{

    /**                                                                           
      * @Fields serialVersionUID : (用一句话描述这个变量表示什么)                                                                                                                                                                                                                                  
      */
    private static final long serialVersionUID = -1398579383435214906L;
    
    @Valid
    @NotNull(groups={Insert.class, Update.class})
    private List<UIActionNotificationVO> actionNotificationList;

    public List<UIActionNotificationVO> getActionNotificationList()
    {
        return actionNotificationList;
    }

    public void setActionNotificationList(List<UIActionNotificationVO> actionNotificationList)
    {
        this.actionNotificationList = actionNotificationList;
    }
}
