package com.asiainfo.meo.meo.notification.manager.listenter;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.asiainfo.meo.common.core.cache.Cache;
import com.asiainfo.meo.common.core.cache.distributed.DistributedCache;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.event.Event;
import com.asiainfo.meo.common.event.EventListener;
import com.asiainfo.meo.customer.device.app.model.vo.DeviceInfoVO;
import com.asiainfo.meo.meo.notification.business.NotifyCommonBean;
import com.asiainfo.meo.meo.notification.component.NotificationComponent;
import com.asiainfo.meo.meo.notification.define.NotifyCationErrorCodeDefine;
import com.asiainfo.meo.meo.notification.email.app.bo.EmailNotificationBO;
import com.asiainfo.meo.meo.notification.email.app.model.vo.NotifyEmailMessageVO;
import com.asiainfo.meo.meo.notification.push.app.model.vo.NotifyAndroidMessageVO;
import com.asiainfo.meo.meo.notification.push.app.model.vo.NotifyIosMessageVO;
import com.asiainfo.meo.meo.notification.sms.app.bo.SmsNotificationBO;
import com.asiainfo.meo.system.define.SysConstantDefine;
import com.asiainfo.meo.system.service.provide.SystemPserviceBO;
import com.asiainfo.meo.system.user.app.model.entity.SysCustomerLogin;

public class NotificationEventListener implements EventListener
{
    private static final Logger LOG = Logger.getLogger(NotificationEventListener.class);
    
    @SuppressWarnings("null")
    @Override
    public void onEvent(Event e)
    {
        if (e== null)
        {
            LOG.info("empty event or not message event,ignore "+ e.getClass());
            return;
        }
        
        NotifyCommonBean commonBean = e.getPayload(NotifyCommonBean.class);
        if (null== commonBean)
        {
            return;
        }
        
        Long actionId = commonBean.getActionId();
        // according to the actionId query the corresponding notification configuration
//        ConfigurationBO configurationBO = ServiceLocatorFactory.getService(ConfigurationBO.class);
//        List<ActionNotificationVO> actionNotificationList = configurationBO.getActionNotificationByActionIdAndNotifyType(
//                actionId, SysNotificationDef.USER_NOTIFICATION);
//        if (ValidateUtil.isEmpty(actionNotificationList))
//        {
//            LOG.debug("actionId :"+ actionId+ " not configuration the actionNotificationDef");
//            return;
//        }
//        ActionNotificationVO actionNotificationDef = actionNotificationList.get(0);
//        NotifyActionRelVO notifyActionRelVO = actionNotificationDef.getNotifyActionRelVO();
//        Long templateId = notifyActionRelVO.getTemplateId();
        
        // Long channelType = commonBean.getChannelType();
        // String deveceToken = commonBean.getDeviceToken();
        
//        DeviceInfoVO divo = new DeviceInfoVO();
//        divo.setDeviceToken("0644ee6ee9f9d1f021a8e8c4f8611afeaccff9d21253ba1bdcab3206f60ebd68");
//        divo.setDeviceType(1);
//        divo.setDeviceId(100000112142L);
//        divo.setOsVersion("8.4.1");
//        divo.setUserId(100000108109L);
//        Cache devecieCache = ServiceLocatorFactory.getService(DistributedCache.class);
//        String deviceCachekey =  SysConstantDefine.CUSTOMER_DEVICE_CACHE_KEY+"zzy.521vip@gmail.com";
//        devecieCache.put(SysConstantDefine.DEVICE_CACHE_NAME, deviceCachekey, divo);
        
        Integer notifyType = commonBean.getNotifyType();
        Long userId = commonBean.getUserId();
//        SystemPserviceBO systemPServiceBO = ServiceLocatorFactory.getService(SystemPserviceBO.class);
//        SysCustomerLogin customerLogin = systemPServiceBO.querySysCustLoginByCustId(userId);
//        Cache deviceCache = ServiceLocatorFactory.getService(DistributedCache.class);
//        DeviceInfoVO deviceInfo = deviceCache.get(SysConstantDefine.DEVICE_CACHE_NAME,
//                SysConstantDefine.CUSTOMER_DEVICE_CACHE_KEY+ customerLogin.getLoginAcct(), DeviceInfoVO.class);
        DeviceInfoVO deviceInfo = new DeviceInfoVO();
        deviceInfo.setDeviceType(notifyType);
        deviceInfo.setDeviceToken("0644ee6ee9f9d1f021a8e8c4f8611afeaccff9d21253ba1bdcab3206f60ebd68");
        List<String> iosDevices = new ArrayList<String>();
        List<String> androidDevices = new ArrayList<String>();
        if(NotifyCommonBean.STS_ANDROID == deviceInfo.getDeviceType())
        {
            androidDevices.add(deviceInfo.getDeviceToken());
        }
        else if(NotifyCommonBean.STS_IOS == deviceInfo.getDeviceType())
        {
            iosDevices.add(deviceInfo.getDeviceToken());
        }
        NotificationComponent bo = ServiceLocatorFactory.getService(NotificationComponent.class);
        
        // ==========================================================//
        if (NotifyCommonBean.STS_ANDROID== notifyType)
        {
            NotifyAndroidMessageVO message = new NotifyAndroidMessageVO();
            message.setDevices(androidDevices);
            String apiKey = "AIzaSyAPLh-WhdFtEM1AtNeo4_H_OWQtzDgyMcM";
            message.setApiKey(apiKey);
            message.setContent("android push notification test");
            bo.asyncPushAndroidNotification(message);
        }
        else if (NotifyCommonBean.STS_EMAIL== notifyType)
        {
            NotifyEmailMessageVO message = new NotifyEmailMessageVO();
            String senderAddress = "ai.th.exp.lab@gmail.com";
            String receiverAddress = /*customerLogin.getLoginAcct()*/"zzy.521vip@gmail.com";
            String messageContent = "push email notification test!";
            String subject = "email notification test";
            message.setSenderAddress(senderAddress);
            List<String> toAddress = new ArrayList<>();
            toAddress.add(receiverAddress);
            message.setToAddress(toAddress);
            message.setMessages(messageContent);
            message.setSubject(subject);
            EmailNotificationBO emailBO = ServiceLocatorFactory.getService(EmailNotificationBO.class);
            emailBO.sendEmailNotification(message);
        }
        else if (NotifyCommonBean.STS_IOS== notifyType)
        {
            NotifyIosMessageVO message = new NotifyIosMessageVO();
            message.setTest(false);
            message.setBadge(1);
            // according to the current url get the certificatePath
            String certificatePath = NotificationEventListener.class.getResource("/").getPath()
                    + "config/apns/APNS-MeO-Pilot-Sandbox.p12";
            LOG.info("========================");
            LOG.info(NotificationEventListener.class.getResource("/").getPath());
            LOG.info(certificatePath);
            LOG.info("========================");
            message.setCertificatePath(certificatePath);
            // Maybe the content is composed of a rule,the rule define in the db
            String content = "push notification message";
            message.setContent(content);
            message.setDeviceTokens(iosDevices);
            bo.syncPushIosNotification(message);
        }
        else if (NotifyCommonBean.STS_SMS== notifyType)
        {
            SmsNotificationBO smsBO = ServiceLocatorFactory.getService(SmsNotificationBO.class);
        }
    }
    
    @Override
    public void onException(Throwable arg0)
    {
        throw new MeoException(NotifyCationErrorCodeDefine.NOTIFY_MANAGER_ERROR, arg0);
    }
}
