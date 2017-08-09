package com.asiainfo.meo.meo.notification.component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javapns.Push;
import javapns.devices.Device;
import javapns.devices.Devices;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PayloadPerDevice;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.PushedNotifications;
import javapns.notification.transmission.PushQueue;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.meo.notification.define.NotifyConstantDefine;
import com.asiainfo.meo.meo.notification.email.app.bo.EmailNotificationBO;
import com.asiainfo.meo.meo.notification.email.app.model.vo.NotifyEmailMessageVO;
import com.asiainfo.meo.meo.notification.manager.Datastore;
import com.asiainfo.meo.meo.notification.push.app.model.vo.NotifyAndroidMessageVO;
import com.asiainfo.meo.meo.notification.push.app.model.vo.NotifyIosMessageVO;
import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class NotificationComponent
{
    
    private static final Logger logger = Logger.getLogger(NotificationComponent.class);
    
    public List<String> syncPushIosNotification(NotifyIosMessageVO message)
    {
        PushNotificationPayload payLoad = new PushNotificationPayload();
        try
        {
            payLoad.addAlert(message.getContent()); // æ¶ˆæ�¯å†…å®¹
            payLoad.addBadge(message.getBadge()); // iphoneåº”ç”¨å›¾æ ‡ä¸Šå°�çº¢åœˆä¸Šçš„æ•°å€¼
            if (ValidateUtil.isEmpty(message.getSound()))
            {
                payLoad.addSound("default");// é“ƒéŸ³
            }
            PushNotificationManager pushManager = new PushNotificationManager();
            // trueï¼šè¡¨ç¤ºçš„æ˜¯äº§å“�å�‘å¸ƒæŽ¨é€�æœ�åŠ¡ falseï¼šè¡¨ç¤ºçš„æ˜¯äº§å“�æµ‹è¯•æŽ¨é€�æœ�åŠ¡
            pushManager.initializeConnection(new AppleNotificationServerBasicImpl(message.getCertificatePath(), message
                    .getCertificatePwd(), message.isTest()));
            List<PushedNotification> notifications = new ArrayList<PushedNotification>();
            // å�‘é€�pushæ¶ˆæ�¯
            if (!message.isGroup())
            {
                Device device = new BasicDevice();
                device.setToken(message.getDeviceTokens().get(0));
                PushedNotification notification = pushManager.sendNotification(device, payLoad, true);
                notifications.add(notification);
            }
            else
            {
                List<Device> device = new ArrayList<Device>();
                for (String token : message.getDeviceTokens())
                {
                    device.add(new BasicDevice(token));
                }
                notifications = pushManager.sendNotifications(payLoad, device);
            }
            List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
            // List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
            List<String> failedDevice = new ArrayList<String>();
            for (PushedNotification pushedNotification : failedNotifications)
            {
                failedDevice.add(pushedNotification.getDevice().getToken());
            }
            pushManager.stopConnection();
            return failedDevice;
        }
        catch(Exception e)
        {
            logger.error("push notification method error:", e);
        }
        return null;
        
    }
    
    public List<String> asyncPushAndroidNotification(NotifyAndroidMessageVO message)
    {
        if (message.getDevices().size()== 1)
        {
            return anycPushAndroidSimple(message);
            
        }
        else
        {
            return anycPushAndroidMulti(message);
        }
        
    }
    
    private List<String> anycPushAndroidMulti(NotifyAndroidMessageVO message)
    {
        Executor threadPool = Executors.newFixedThreadPool(5);
        final Sender sender = new Sender(message.getApiKey());
        List<String> devices = message.getDevices();
        final List<String> failedDevices = new ArrayList<String>();
        int total = devices.size();
        List<String> partialDevices = new ArrayList<String>(total);
        int counter = 0;
        int tasks = 0;
        for (String device : devices)
        {
            counter++ ;
            partialDevices.add(device);
            int partialSize = partialDevices.size();
            if (partialSize== NotifyConstantDefine.MULTICAST_SIZE|| counter== total)
            {
                
                final List<String> devicesPartial = new ArrayList<String>(partialDevices);
                threadPool.execute(new Runnable()
                {
                    
                    public void run()
                    {
                        Message message = new Message.Builder().build();
                        MulticastResult multicastResult;
                        try
                        {
                            multicastResult = sender.send(message, devicesPartial, 5);
                        }
                        catch(IOException e)
                        {
                            logger.error("", e);
                            throw new MeoException("");
                        }
                        List<Result> results = multicastResult.getResults();
                        // analyze the results
                        for (int i = 0; i< devicesPartial.size(); i++ )
                        {
                            String regId = devicesPartial.get(i);
                            Result result = results.get(i);
                            if (!checkPushAndroidResult(result, regId))
                            {
                                failedDevices.add(regId);
                            }
                        }
                    }
                });
                partialDevices.clear();
                tasks++ ;
                logger.info("Asynchronously sending "+ tasks+ " multicast messages to "+ total+ " devices");
            }
        }
        
        return null;
    }
    
    private List<String> anycPushAndroidSimple(NotifyAndroidMessageVO message)
    {
        Sender sender = new Sender(message.getApiKey());
        String regId = message.getDevices().get(0);
        String content = message.getContent();
        Message msg = new Message.Builder().addData("message", content).build();
        List<String> failedDevices = new ArrayList<String>();
        try
        {
            Result result = sender.send(msg, regId, 5);
            if (!checkPushAndroidResult(result, regId))
            {
                failedDevices.add(regId);
            }
            
        }
        catch(IOException e)
        {
        }
        return null;
        
    }
    
    private boolean checkPushAndroidResult(Result result, String regId)
    {
        String messageId = result.getMessageId();
        if (messageId!= null)
        {
            logger.info("Succesfully sent message to device: "+ regId+ "; messageId = "+ messageId);
            String canonicalRegId = result.getCanonicalRegistrationId();
            if (canonicalRegId!= null)
            {
                // same device has more than on registration id: update it
                logger.info("canonicalRegId "+ canonicalRegId);
                Datastore.updateRegistration(regId, canonicalRegId);
            }
        }
        else
        {
            String error = result.getErrorCodeName();
            if (error.equals(Constants.ERROR_NOT_REGISTERED))
            {
                // application has been removed from device - unregister it
                logger.info("Unregistered device: "+ regId);
                Datastore.unregister(regId);
            }
            else
            {
                logger.info("Error sending message to "+ regId+ ": "+ error);
                return false;
            }
        }
        return true;
    }
    
    public List<String> syncPushAndroidNotificaiton(NotifyAndroidMessageVO message)
    {
        if (message.getDevices().size()== 1)
        {
            return anycPushAndroidSimple(message);
            
        }
        else
        {
            return syncPushAndroidMulti(message);
        }
        
    }
    
    private List<String> syncPushAndroidMulti(NotifyAndroidMessageVO message)
    {
        Sender sender = new Sender(message.getApiKey());
        List<String> devices = message.getDevices();
        final List<String> failedDevices = new ArrayList<String>();
        int total = devices.size();
        List<String> partialDevices = new ArrayList<String>();
        int counter = 0;
        int tasks = 0;
        for (String device : devices)
        {
            counter++ ;
            partialDevices.add(device);
            int partialSize = partialDevices.size();
            if (partialSize== NotifyConstantDefine.MULTICAST_SIZE|| counter== total)
            {
                
                List<String> devicesPartial = new ArrayList<String>(partialDevices);
                Message msg = new Message.Builder().build();
                MulticastResult multicastResult;
                try
                {
                    multicastResult = sender.send(msg, devicesPartial, 5);
                }
                catch(IOException e)
                {
                    logger.error("", e);
                    throw new MeoException("");
                }
                List<Result> results = multicastResult.getResults();
                // analyze the results
                for (int i = 0; i< devicesPartial.size(); i++ )
                {
                    String regId = devicesPartial.get(i);
                    Result result = results.get(i);
                    if (!checkPushAndroidResult(result, regId))
                    {
                        failedDevices.add(regId);
                    }
                }
            }
        }
        partialDevices.clear();
        tasks++ ;
        logger.info("synchronously sending "+ tasks+ " multicast messages to "+ total+ " devices");
        return failedDevices;
        
    }
    
    public List<String> asyncPushIosNotification(NotifyIosMessageVO message)
    {
        try
        {
            List<Device> devices = Devices.asDevices(message.getDeviceTokens());
            PushQueue queue = Push.queue(message.getCertificatePath(), message.getCertificatePwd(), message.isTest(), 10);
            queue.start();
            PayloadPerDevice perDevice = null;
            for (int i = 0; i< devices.size(); i++ )
            {
                PushNotificationPayload payload = PushNotificationPayload.complex();
                payload.addBadge(message.getBadge());
                if (ValidateUtil.isEmpty(message.getSound()))
                {
                    payload.addSound("default");
                }
                else
                {
                    payload.addSound(message.getSound());
                }
                
                perDevice = new PayloadPerDevice(payload, devices.get(i));
                queue.add(perDevice);
            }
            PushedNotifications failedNotifications = queue.getPushedNotifications().getFailedNotifications();
            List<String> failedDevice = new ArrayList<String>();
            for (PushedNotification pushedNotification : failedNotifications)
            {
                failedDevice.add(pushedNotification.getDevice().getToken());
            }
            return failedDevice;
        }
        catch(Exception e)
        {
            logger.error("", e);
            throw new MeoException("");
        }
        
    }
    
    public Boolean sendEmailNotification(NotifyEmailMessageVO message) throws MeoException
    {
        if (validateEmailMessage(message))
        {
            try
            {
                /** define smtp properties */
                final Properties properties = System.getProperties();
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");
                final Session session = Session.getInstance(properties, new javax.mail.Authenticator()
                {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication("ai.th.exp.lab@gmail.com", "$asiainfo$");
                    }
                });
                
                final MimeMessage mimeMessage = new MimeMessage(session);
                mimeMessage.setFrom(new InternetAddress(message.getSenderAddress()));
                /** add recipient */
                for (final String recipient : message.getToAddress())
                {
                    mimeMessage.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(recipient));
                }
                /** add cc recipient */
                if (message.getCcAddress()!= null&& message.getCcAddress().size()> 0)
                {
                    for (final String cc : message.getCcAddress())
                    {
                        mimeMessage.addRecipient(javax.mail.Message.RecipientType.CC, new InternetAddress(cc));
                    }
                }
                /** add bcc recipient */
                if (message.getBccAddress()!= null&& message.getBccAddress().size()> 0)
                {
                    for (final String bcc : message.getBccAddress())
                    {
                        mimeMessage.addRecipient(javax.mail.Message.RecipientType.BCC, new InternetAddress(bcc));
                    }
                }
                /** add mail's subject */
                if (message.getSubject()!= null&& message.getSubject().trim().length()> 0)
                {
                    mimeMessage.setSubject(message.getSubject());
                }
                /** set mail's content */
                if (message.getMessages()!= null&& message.getMessages().trim().length()> 0)
                {
                    final Multipart multipart = new MimeMultipart();
                    final BodyPart messageBodyPart = new MimeBodyPart();
                    messageBodyPart.setText(message.getMessages());
                    multipart.addBodyPart(messageBodyPart);
                    mimeMessage.setContent(multipart);
                }
                Transport.send(mimeMessage);
                return Boolean.TRUE;
            }
            catch(MessagingException mex)
            {
                mex.printStackTrace();
                throw new MeoException("Email notificaiton", mex);
            }
        }
        return Boolean.FALSE;
    }
    
    private Boolean validateEmailMessage(NotifyEmailMessageVO message)
    {
        if (message== null)
        {
            return Boolean.FALSE;
        }
        if (message.getSenderAddress()== null|| message.getSenderAddress().trim().length()== 0)
        {
            return Boolean.FALSE;
        }
        if (message.getToAddress()== null|| message.getToAddress().size()== 0)
        {
            return Boolean.FALSE;
        }
        else
        {
            for (final String to : message.getToAddress())
            {
                if (to== null|| to.trim().length()== 0)
                {
                    return Boolean.FALSE;
                }
            }
        }
        if (message.getCcAddress()!= null&& message.getCcAddress().size()> 0)
        {
            for (final String cc : message.getCcAddress())
            {
                if (cc== null|| cc.trim().length()== 0)
                {
                    return Boolean.FALSE;
                }
            }
        }
        if (message.getBccAddress()!= null&& message.getBccAddress().size()> 0)
        {
            for (final String bcc : message.getBccAddress())
            {
                if (bcc== null|| bcc.trim().length()== 0)
                {
                    return Boolean.FALSE;
                }
            }
        }
        
        return Boolean.TRUE;
    }
    
    public static void main(String[] args)
    {
        // NotifyIosMessageVO message = new NotifyIosMessageVO();
        // message.setTest(false);
        // message.setBadge(5);
        // String currentUrl = System.getProperty("user.dir").replace("\\", "/");
        // String certificatePath = currentUrl+ "/APNS-MeO-Pilot-Sandbox.p12";
        // message.setCertificatePath(certificatePath);
        // message.setContent("hello world!!!大幅度幅度浮动幅度放");
        // List<String> deviceTokens = new ArrayList<String>();
        // deviceTokens.add("0644ee6ee9f9d1f021a8e8c4f8611afeaccff9d21253ba1bdcab3206f60ebd68");
        // message.setDeviceTokens(deviceTokens);
        // NotificationComponent bo = new NotificationComponent();
        // bo.syncPushIosNotification(message);
        
        // NotifyAndroidMessageVO message = new NotifyAndroidMessageVO();
        // message.setApiKey("AIzaSyAPLh-WhdFtEM1AtNeo4_H_OWQtzDgyMcM");
        // List<String> deviceTokens = new ArrayList<String>();
        // deviceTokens.add("ccW9sOc7bc8:APA91bHsNfNxEXH-3F_9HhU2M-0dH6ypgPJMtX4nQfPpVXmOCcW3-DYf-GERwWi0uJi8_e4yX82KiJdV0WdToeazNCcbXSCQatSmyDRTHRj2PmMCadsEMQXaFE00575CkQEYymE35OWq");
        // message.setContent("android push notification test");
        // message.setDevices(deviceTokens);
        // NotificationComponent bo = new NotificationComponent();
        // bo.asyncPushAndroidNotification(message);
        
         EmailNotificationBO email = ServiceLocatorFactory.getService(EmailNotificationBO.class);
         NotifyEmailMessageVO message = new NotifyEmailMessageVO();
         message.setSenderAddress("liuyang8@asiainfo.com");
         List<String> toAddress = new ArrayList<>();
         toAddress.add("zhengzy@asiainfo.com");
         message.setToAddress(toAddress);
         message.setMessages("push email notification test!");
         message.setSubject("email notification");
         email.sendEmailNotification(message);
    }
    
}
