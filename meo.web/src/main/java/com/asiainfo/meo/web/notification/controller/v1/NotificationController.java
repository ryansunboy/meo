package com.asiainfo.meo.web.notification.controller.v1;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.meo.common.core.context.BoContext;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;
import com.asiainfo.meo.common.event.bus.EventBus;
import com.asiainfo.meo.common.event.message.impl.SimpleMessageEvent;
import com.asiainfo.meo.meo.notification.business.NotifyCommonBean;
import com.asiainfo.meo.meo.notification.configuration.model.vo.ActionNotificationQueryConditionVO;
import com.asiainfo.meo.meo.notification.configuration.model.vo.ActionNotificationVO;
import com.asiainfo.meo.meo.notification.configuration.model.vo.NotificationDefVO;
import com.asiainfo.meo.meo.notification.define.NotifyConstantDefine;
import com.asiainfo.meo.meo.notification.service.provide.NotifyPserviceBO;
import com.asiainfo.meo.web.component.NotificationsComponent;
import com.asiainfo.meo.web.notification.model.vo.UIActionNotificationListVO;
import com.asiainfo.meo.web.notification.model.vo.UIActionNotificationVO;
import com.asiainfo.meo.web.notification.model.vo.UINotificationDefVO;

/**
 * @author liuyang
 */
@RestController
@RequestMapping("/notification")
public class NotificationController
{
    private static final Log   LOG = LogFactory.getLog(NotificationController.class);
    
    @Resource
    private NotifyPserviceBO notifyPserviceBO;
    
    @RequestMapping(params = "method=meo.notification.create", method = {RequestMethod.POST })
    public Map<String, Long> createNotificationParam(@RequestBody @Validated(value = Insert.class) UINotificationDefVO uiNotificationDefVO)
    {
        LOG.debug("meo.notification.create begin!");
        Map<String, Long> map = new HashMap<String, Long>();
        Long userId = BoContext.getBoContext().getUserId();
        uiNotificationDefVO.setOperatorId(userId);
        NotificationDefVO notificationDefVO = NotificationsComponent.transformUINotificationDefVOToNotificationDefVO(uiNotificationDefVO);
        Long notifyId = notifyPserviceBO.createSysNotificationDef(notificationDefVO);
        map.put("notifyId", notifyId);
        LOG.debug("meo.notification.create end!");
        return map;
    }
    
    @RequestMapping(params = "method=meo.notification.delete")
    public void deleteNotificationParam(@RequestParam Long notifyId)
    {
        LOG.debug("meo.notification.delete begin!");
        notifyPserviceBO.deleteSysNotificationDef(notifyId);
        LOG.debug("meo.notification.delete end!");
    }
    
    @RequestMapping(params = "method=meo.notification.modify", method = {RequestMethod.POST })
    public void modifyNotificationParam(@RequestBody @Validated(value = Update.class) UINotificationDefVO uiNotificationDefVO)
    {
        LOG.debug("meo.notification.modify begin!");
        NotificationDefVO notificationDefVO = NotificationsComponent.transformUINotificationDefVOToNotificationDefVO(uiNotificationDefVO);
        notifyPserviceBO.modifySysNotificationDef(notificationDefVO);
        LOG.debug("meo.notification.modify end!");
    }
    
    @RequestMapping(params = "method=meo.notification.list")
    public PageInfo<UINotificationDefVO> ListNotificationParam(
            @RequestParam(required = false) Integer pageNo, 
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Long notifyId,
            @RequestParam(required = false) String notificationName)
    {
        LOG.debug("meo.notification.list begin!");
        pageNo = ValidateUtil.checkPageNo(pageNo);
        pageSize = ValidateUtil.checkPageSize(pageSize);
        PageInfo<NotificationDefVO> pageInfo = notifyPserviceBO.getSysNotificationDefList(pageNo, pageSize, notifyId, notificationName);
        PageInfo<UINotificationDefVO> page = NotificationsComponent.transformPageNotificationDefVOToPageUINotificationDefVO(pageInfo);
        LOG.debug("meo.notification.list end!");
        return page;
    }
    
    @RequestMapping(params = "method=meo.notification.get")
    public UINotificationDefVO getNotification(@RequestParam Long notifyId)
    {
        LOG.debug("meo.notification.get begin!");
        NotificationDefVO notificationDefVO = notifyPserviceBO.getSysNotificationDefVOByNotifId(notifyId);
        UINotificationDefVO uivo = NotificationsComponent.transformNotificationDefVOToUINotificationDefVO(notificationDefVO);
        LOG.debug("meo.notification.get end!");
        return uivo;
    }
    
    @RequestMapping(params="method=meo.notification.action.create", method = {RequestMethod.POST})
    public void createNotificationAction(@RequestBody @Validated(value = Insert.class) UIActionNotificationListVO uiActionNotificationListVO)
    {
        LOG.debug("meo.notification.action.create begin!");
        Long userId = BoContext.getBoContext().getUserId();
        for (final UIActionNotificationVO uiActionNotification : uiActionNotificationListVO.getActionNotificationList()) {
            final ActionNotificationVO actionNotificationVO = NotificationsComponent.
                    transformUIActionNotificationVOToActionNotificationVO(uiActionNotification, userId); 
            notifyPserviceBO.createActionNotification(actionNotificationVO);
        }
        
        LOG.debug("meo.notification.action.create end!");
    }
    
    @RequestMapping(params="method=meo.notification.action.modify", method={RequestMethod.POST})
    public void modifyNotificationAction(@RequestBody @Validated(value = Update.class) UIActionNotificationListVO uiActionNotificationListVO)
    {
        LOG.debug("meo.notification.action.modify begin!");
        
        LOG.debug("meo.notification.action.modify end!");
    }
    
    @RequestMapping(params="method=meo.notification.action.get", method={RequestMethod.GET})
    public PageInfo<UIActionNotificationVO> getActionNotificationByNotifyAndActionId(
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = true) Long notifyId,
            @RequestParam(required = false) Long actionId) {
        LOG.debug("meo.notification.action.get begin!");
        final ActionNotificationQueryConditionVO conditionVO = NotificationsComponent.
                transformActionNotificationQueryConditionVO(pageNo, pageSize, notifyId, actionId);
        PageInfo<ActionNotificationVO> pageInfo = notifyPserviceBO.getActionNotificationByNotifyAndActionId(conditionVO);
        final PageInfo<UIActionNotificationVO> uiPageInfo = NotificationsComponent.transformToUIActionNotificationVOPageInfo(pageInfo);
        LOG.debug("meo.notification.action.get end!");
        return uiPageInfo;
    }
    
    @RequestMapping(params="method=meo.notification.action.list", method={RequestMethod.GET, RequestMethod.POST})
    public PageInfo<UIActionNotificationVO> getAllActionNotification(
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize) {
        LOG.debug("meo.notification.action.list begin!");
        final ActionNotificationQueryConditionVO conditionVO = NotificationsComponent.
                transformActionNotificationQueryConditionVO(pageNo, pageSize, null, null);
        PageInfo<ActionNotificationVO> pageInfo = notifyPserviceBO.getActionNotificationByNotifyAndActionId(conditionVO);
        final PageInfo<UIActionNotificationVO> uiPageInfo = NotificationsComponent.transformToUIActionNotificationVOPageInfo(pageInfo);
        LOG.debug("meo.notification.action.list end!");
        return uiPageInfo;
    }
    
    @RequestMapping(params="method=meo.notification.action.delete", method={RequestMethod.GET})
    public void deleteNotificationAction(@RequestParam(required = true) Long notifyId,
            @RequestParam(required = false) Long actionId) {
        LOG.debug("meo.notification.action.delete begin!");
        notifyPserviceBO.deleteActionNotification(notifyId, actionId);
        LOG.debug("meo.notification.action.delete end!");
    }
    
    /**
     *  
      * @Description: 这个方法目前是用来做测试的
      * @Description: this method just for testing push notification,it will be removed after test
      * @modifyReason: 
      * @author zhengzy
     */
    @RequestMapping(params="method=meo.notification.push.test", method={RequestMethod.GET})
    public void pushNotificationTestMethod(@RequestParam Integer notifyType)
    {
        LOG.debug("===========================================meo.notification.push.test begin!=======================");
        LOG.debug("===========the input parameter notifyType is "+notifyType);
        Long userId = 100000108109L;
        Long userType = 1L;
        Long actionId = 1L;
        NotifyCommonBean obj = new NotifyCommonBean();
        obj.setActionId(actionId);
        obj.setUserId(userId);
        obj.setUserType(userType);
        obj.setNotifyType(notifyType);
        EventBus eventBus = ServiceLocatorFactory.getService(EventBus.class);
        SimpleMessageEvent event = new SimpleMessageEvent(NotifyConstantDefine.NOTIFY_EVENT_ID,NotifyConstantDefine.NOTIFY_EVENT_TOPIC);
        event.setPayload(obj);
        eventBus.publish(event);
        LOG.debug("===========================================meo.notification.push.test end!=======================");
        return;
    }
    
}
