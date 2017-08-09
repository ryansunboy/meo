package com.asiainfo.meo.web.component;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.meo.notification.configuration.model.vo.ActionNotificationQueryConditionVO;
import com.asiainfo.meo.meo.notification.configuration.model.vo.ActionNotificationVO;
import com.asiainfo.meo.meo.notification.configuration.model.vo.NotificationDefVO;
import com.asiainfo.meo.meo.notification.configuration.model.vo.NotificationNumLimitVO;
import com.asiainfo.meo.meo.notification.configuration.model.vo.NotificationTimeLimitVO;
import com.asiainfo.meo.meo.notification.configuration.model.vo.NotifyActionRelVO;
import com.asiainfo.meo.meo.notification.configuration.model.vo.TimeSegDefVO;
import com.asiainfo.meo.meo.notification.configuration.model.vo.TimeSegDtlVO;
import com.asiainfo.meo.meo.notification.utils.TimeModeUtils;
import com.asiainfo.meo.system.template.app.model.vo.MessageTemplateDefVO;
import com.asiainfo.meo.system.template.app.model.vo.TemplateParamDefVO;
import com.asiainfo.meo.web.common.define.model.vo.UIMessageTemplateDefVO;
import com.asiainfo.meo.web.common.define.model.vo.UITemplateParamDefVO;
import com.asiainfo.meo.web.notification.model.vo.UIActionNotificationVO;
import com.asiainfo.meo.web.notification.model.vo.UINotificationDefVO;
import com.asiainfo.meo.web.notification.model.vo.UINotificationNumLimitVO;
import com.asiainfo.meo.web.notification.model.vo.UINotificationTimeLimitVO;
import com.asiainfo.meo.web.notification.model.vo.UINotifyActionRelVO;
import com.asiainfo.meo.web.notification.model.vo.UITimeSegDefVO;
import com.asiainfo.meo.web.notification.model.vo.UITimeSegDtlVO;
import com.asiainfo.meo.web.notification.model.vo.UITimeVO;

public class NotificationsComponent
{
    private static final Log logger = LogFactory.getLog(NotificationsComponent.class);

    public static MessageTemplateDefVO transformUIMessageTemplateDefVOToMessageTemplateDefVO(
            UIMessageTemplateDefVO uiMessageTemplateDefVO)
    {
        MessageTemplateDefVO messageTemplateDefVO = new MessageTemplateDefVO();
        messageTemplateDefVO.setOperatorId(uiMessageTemplateDefVO.getOperatorId());
        messageTemplateDefVO.setTemplateName(uiMessageTemplateDefVO.getTemplateName());
        messageTemplateDefVO.setTemplateContent(uiMessageTemplateDefVO.getTemplateContent());
        messageTemplateDefVO.setTemplateDesc(uiMessageTemplateDefVO.getTemplateDesc());
        messageTemplateDefVO.setTemplateId(uiMessageTemplateDefVO.getTemplateId());
        return messageTemplateDefVO;
    }

    public static PageInfo<UIMessageTemplateDefVO> transformPageMessageTemplateDefVOToPageUIMessageTemplateDefVO(
            PageInfo<MessageTemplateDefVO> pageInfo)
    {
        PageInfo<UIMessageTemplateDefVO> page = new PageInfo<UIMessageTemplateDefVO>();
        List<UIMessageTemplateDefVO> listUI = new ArrayList<UIMessageTemplateDefVO>();
        List<MessageTemplateDefVO> list = pageInfo.getResult();
        Integer pageNo = pageInfo.getCurrentPage();
        Integer pageSize = pageInfo.getPageSize();
        Integer totalSize = pageInfo.getTotalSize();
        for (MessageTemplateDefVO messageTemplateVo : list)
        {
            UIMessageTemplateDefVO uiMessageTemplateDefVO = new UIMessageTemplateDefVO();
            uiMessageTemplateDefVO.setTemplateId(messageTemplateVo.getTemplateId());
            uiMessageTemplateDefVO.setTemplateContent(messageTemplateVo.getTemplateContent());
            uiMessageTemplateDefVO.setTemplateName(messageTemplateVo.getTemplateName());
            uiMessageTemplateDefVO.setTemplateDesc(messageTemplateVo.getTemplateDesc());
            listUI.add(uiMessageTemplateDefVO);
        }
        return page.createPageInfo(pageNo, pageSize, totalSize, listUI);
    }

    public static UIMessageTemplateDefVO transformMessageTemplateDefVOToUIMessageTemplateDefVO(
            MessageTemplateDefVO messageTemplateDefVO)
    {
        UIMessageTemplateDefVO uiMessageTemplateDefVO = new UIMessageTemplateDefVO();
        uiMessageTemplateDefVO.setTemplateId(messageTemplateDefVO.getTemplateId());
        uiMessageTemplateDefVO.setTemplateContent(messageTemplateDefVO.getTemplateContent());
        uiMessageTemplateDefVO.setTemplateName(messageTemplateDefVO.getTemplateName());
        uiMessageTemplateDefVO.setTemplateDesc(messageTemplateDefVO.getTemplateDesc());
        return uiMessageTemplateDefVO;
    }

    public static TemplateParamDefVO transformUITemplateParamDefVOToTemplateParamDefVO(UITemplateParamDefVO uiTemplateParamDefVO)
    {
        TemplateParamDefVO templateParamDefVO = new TemplateParamDefVO();
        templateParamDefVO.setOperatorId(uiTemplateParamDefVO.getOperatorId());
        templateParamDefVO.setParamName(uiTemplateParamDefVO.getParamName());
        templateParamDefVO.setParamId(uiTemplateParamDefVO.getParamId());
        templateParamDefVO.setParamDesc(uiTemplateParamDefVO.getParamDesc());
        return templateParamDefVO;
    }
    
    public static PageInfo<UITemplateParamDefVO> transformPageTemplateParamDefVOToPageUITemplateParamDefVO(
            PageInfo<TemplateParamDefVO> pageInfo)
    {
        PageInfo<UITemplateParamDefVO> page = new PageInfo<UITemplateParamDefVO>();
        List<UITemplateParamDefVO> listUI = new ArrayList<UITemplateParamDefVO>();
        List<TemplateParamDefVO> list = pageInfo.getResult();
        Integer pageNo = pageInfo.getCurrentPage();
        Integer pageSize = pageInfo.getPageSize();
        Integer totalSize = pageInfo.getTotalSize();
        for (TemplateParamDefVO templateParamDefVO : list)
        {
            UITemplateParamDefVO uiTemplateParamDefVO = new UITemplateParamDefVO();
            uiTemplateParamDefVO.setParamId(templateParamDefVO.getParamId());
            uiTemplateParamDefVO.setParamName(templateParamDefVO.getParamName());
            uiTemplateParamDefVO.setParamDesc(templateParamDefVO.getParamDesc());
            listUI.add(uiTemplateParamDefVO);
        }
        return page.createPageInfo(pageNo, pageSize, totalSize, listUI);
    }

    public static NotificationDefVO transformUINotificationDefVOToNotificationDefVO(UINotificationDefVO uiNotificationDefVO)
    {
        
        NotificationDefVO notificationDefVO = new NotificationDefVO();
        notificationDefVO.setNotifId(uiNotificationDefVO.getNotifyId());
        notificationDefVO.setNotifName(uiNotificationDefVO.getNotificationName());
        notificationDefVO.setTemplateId(uiNotificationDefVO.getTemplateId());
        notificationDefVO.setNotifDesc(uiNotificationDefVO.getNotificationDesc());
        notificationDefVO.setNotifTypeId(uiNotificationDefVO.getNotifTypeId());
        notificationDefVO.setOperatorId(uiNotificationDefVO.getOperatorId());
        return notificationDefVO;
    }

    public static PageInfo<UINotificationDefVO> transformPageNotificationDefVOToPageUINotificationDefVO(
            PageInfo<NotificationDefVO> pageInfo)
    {
        PageInfo<UINotificationDefVO> page = new PageInfo<UINotificationDefVO>();
        List<UINotificationDefVO> listUI = new ArrayList<UINotificationDefVO>();
        List<NotificationDefVO> list = pageInfo.getResult();
        Integer pageNo = pageInfo.getCurrentPage();
        Integer pageSize = pageInfo.getPageSize();
        Integer totalSize = pageInfo.getTotalSize();
        for (NotificationDefVO notificationDefVO : list)
        {
            UINotificationDefVO uiNotificationDefVO = new UINotificationDefVO();
            uiNotificationDefVO.setNotifyId(notificationDefVO.getNotifId());
            uiNotificationDefVO.setNotificationName(notificationDefVO.getNotifName());
            uiNotificationDefVO.setTemplateId(notificationDefVO.getTemplateId());
            uiNotificationDefVO.setTemplateName(notificationDefVO.getTemplateName());
            uiNotificationDefVO.setNotificationDesc(notificationDefVO.getNotifDesc());
            uiNotificationDefVO.setNotifTypeId(notificationDefVO.getNotifTypeId());
            uiNotificationDefVO.setNotifType(notificationDefVO.getNotifType());
            listUI.add(uiNotificationDefVO);
        }
        return page.createPageInfo(pageNo, pageSize, totalSize, listUI);
    }

    public static UINotificationDefVO transformNotificationDefVOToUINotificationDefVO(NotificationDefVO notificationDefVO)
    {
        UINotificationDefVO uiNotificationDefVO = new UINotificationDefVO();
        if (ValidateUtil.isEmpty(notificationDefVO))
        {
            return uiNotificationDefVO;
        }
        uiNotificationDefVO.setNotifyId(notificationDefVO.getNotifId());
        uiNotificationDefVO.setNotificationName(notificationDefVO.getNotifName());
        uiNotificationDefVO.setTemplateId(notificationDefVO.getTemplateId());
        uiNotificationDefVO.setTemplateName(notificationDefVO.getTemplateName());
        uiNotificationDefVO.setNotificationDesc(notificationDefVO.getNotifDesc());
        uiNotificationDefVO.setNotifTypeId(notificationDefVO.getNotifTypeId());
        uiNotificationDefVO.setNotifType(notificationDefVO.getNotifType());
        return uiNotificationDefVO;
    }
    
    public static ActionNotificationVO transformUIActionNotificationVOToActionNotificationVO(
            final UIActionNotificationVO uiActionNotification,
            final Long operatorId) {
        final ActionNotificationVO actionNotifyVO = new ActionNotificationVO();
        final UINotifyActionRelVO uiNotifyActionRelVO = uiActionNotification.getNotification();
        actionNotifyVO.setNotifyActionRelVO(new NotifyActionRelVO());
        /** NotifyActionRelVO */
        BeanUtils.copyProperties(uiNotifyActionRelVO, actionNotifyVO.getNotifyActionRelVO());
        /** NotificationTimeLimitVO List */
        if (ValidateUtil.isNotEmpty(uiActionNotification.getTimeLimittList())) {
            actionNotifyVO.setNotifyTimeLimitList(new ArrayList<NotificationTimeLimitVO>());
            for (final UINotificationTimeLimitVO uiTimeLimitVO : uiActionNotification.getTimeLimittList()) {
                final NotificationTimeLimitVO timeLimitVO = new NotificationTimeLimitVO();
                timeLimitVO.setActionId(uiTimeLimitVO.getActionId());
                timeLimitVO.setRegionCode(uiTimeLimitVO.getRegionCode());
                timeLimitVO.setOperatorId(operatorId);
                /** time segment */
                if (ValidateUtil.isNotEmpty(uiTimeLimitVO.getTimeSegmentList())) {
                    timeLimitVO.setTimeSegmentList(new ArrayList<TimeSegDefVO>());
                    for (UITimeSegDefVO uiTimeSeg :  uiTimeLimitVO.getTimeSegmentList()) {
                        final TimeSegDefVO timeSeg = new TimeSegDefVO();
                        timeSeg.setOperatorId(operatorId);
                        BeanUtils.copyProperties(uiTimeSeg, timeSeg);
                        if (ValidateUtil.isNotEmpty(uiTimeSeg.getSegmentDtlList())) {
                            timeSeg.setTimeSegmentDtlList(new ArrayList<TimeSegDtlVO>());
                            for (UITimeSegDtlVO uiDtlVO : uiTimeSeg.getSegmentDtlList()) {
                                final TimeSegDtlVO dtlVO = new TimeSegDtlVO();
                                dtlVO.setStartVal(convertUITimeVOToLong(uiDtlVO.getStartVal()));
                                dtlVO.setEndVal(convertUITimeVOToLong(uiDtlVO.getEndVal()));
                                dtlVO.setOperatorId(operatorId);
                                BeanUtils.copyProperties(uiDtlVO, dtlVO);
                                timeSeg.getTimeSegmentDtlList().add(dtlVO);
                            }
                        }
                        timeLimitVO.getTimeSegmentList().add(timeSeg);
                    }
                }
                actionNotifyVO.getNotifyTimeLimitList().add(timeLimitVO);
            }
        }
        /** NotificationNumLimitVO List */
        if (ValidateUtil.isNotEmpty(uiActionNotification.getCountLimitList())) {
            actionNotifyVO.setNotifyNumLimitList(new ArrayList<NotificationNumLimitVO>());
            for (UINotificationNumLimitVO uiNumLimitVO : uiActionNotification.getCountLimitList()) {
                final NotificationNumLimitVO numLimitVO = new NotificationNumLimitVO();
                numLimitVO.setOperatorId(operatorId);
                BeanUtils.copyProperties(uiNumLimitVO, numLimitVO);
                actionNotifyVO.getNotifyNumLimitList().add(numLimitVO);
            }
        }
        
        return actionNotifyVO;
    }
    
    private static Long convertUITimeVOToLong(final UITimeVO uiTimeVO) {
        if (ValidateUtil.isNotEmpty(uiTimeVO)) {
            StringBuffer sb = new StringBuffer()
             .append(ValidateUtil.isNotNull(uiTimeVO.getYear()) ? 
                     (uiTimeVO.getYear().toString().trim().length() == 1) 
                         ? "0".concat(uiTimeVO.getYear().toString()) : uiTimeVO.getYear().toString()
                     : "")
             .append(ValidateUtil.isNotNull(uiTimeVO.getMonth()) ? 
                     (uiTimeVO.getMonth().toString().trim().length() == 1)
                         ? "0".concat(uiTimeVO.getMonth().toString()) : uiTimeVO.getMonth().toString()
                     : "")
             .append(ValidateUtil.isNotNull(uiTimeVO.getDay()) ?
                     (uiTimeVO.getDay().toString().trim().length() == 1)
                         ? "0".concat(uiTimeVO.getDay().toString()) : uiTimeVO.getDay().toString()
                     : "")
             .append(ValidateUtil.isNotNull(uiTimeVO.getWeek()) ?
                     (uiTimeVO.getWeek().toString().trim().length() == 1)
                         ? "0".concat(uiTimeVO.getWeek().toString()) : uiTimeVO.getWeek().toString()
                     : "")
             .append(ValidateUtil.isNotNull(uiTimeVO.getHour()) ?
                     (uiTimeVO.getHour().toString().trim().length() == 1)
                         ? "0".concat(uiTimeVO.getHour().toString()) : uiTimeVO.getHour().toString()
                     : "")
             .append(ValidateUtil.isNotNull(uiTimeVO.getMinute()) ?
                     (uiTimeVO.getMinute().toString().trim().length() == 1)
                         ? "0".concat(uiTimeVO.getMinute().toString()) : uiTimeVO.getMinute().toString()
                     : "")
             .append(ValidateUtil.isNotNull(uiTimeVO.getSecond()) ?
                     (uiTimeVO.getSecond().toString().trim().length() == 1)
                         ? "0".concat(uiTimeVO.getSecond().toString()) : uiTimeVO.getSecond().toString()
                     : "");
            try {
                return Long.parseLong(sb.toString());
            } catch (NumberFormatException nfe) {
                return null;
            }
        }
        return null;
    }
    
    private static UITimeVO convertLongToUITimeVO(final Long timeVal, final Integer timeMode) {
        UITimeVO uiTimeVO = new UITimeVO();     
        switch (timeMode) {
            case 1:
            case 5:
                /** DHHMMSS */
                if (TimeModeUtils.validateTimeMode(timeMode, timeVal)) {
                    final String timeValStr = timeVal.toString().trim();
                    uiTimeVO.setWeek(new Integer(timeValStr.substring(0, 1)));
                    uiTimeVO.setHour(new Integer(timeValStr.substring(1, 3)));
                    uiTimeVO.setMinute(new Integer(timeValStr.substring(3, 5)));
                    uiTimeVO.setSecond(new Integer(timeValStr.substring(5)));
                } else {
                    uiTimeVO = null;
                }
                break;
            case 2:
            case 6:
                /** YYYYMMDDHHMMSS */
                if (TimeModeUtils.validateTimeMode(timeMode, timeVal)) {
                    final String timeValStr = timeVal.toString().trim();
                    uiTimeVO.setYear(new Integer(timeValStr.substring(0, 4)));
                    uiTimeVO.setMonth(new Integer(timeValStr.substring(4, 6)));
                    uiTimeVO.setDay(new Integer(timeValStr.substring(6, 8)));
                    uiTimeVO.setHour(new Integer(timeValStr.substring(8, 10)));
                    uiTimeVO.setMinute(new Integer(timeValStr.substring(10, 12)));
                    uiTimeVO.setSecond(new Integer(timeValStr.substring(12)));
                } else {
                    uiTimeVO = null;
                }
                break;
            case 3:
            case 7:
                /** MMDDHHMMSS */
                if (TimeModeUtils.validateTimeMode(timeMode, timeVal)) {
                    final String timeValStr = timeVal.toString().trim();
                    uiTimeVO.setMonth(new Integer(timeValStr.substring(0, 2)));
                    uiTimeVO.setDay(new Integer(timeValStr.substring(2, 4)));
                    uiTimeVO.setHour(new Integer(timeValStr.substring(4, 6)));
                    uiTimeVO.setMinute(new Integer(timeValStr.substring(6, 8)));
                    uiTimeVO.setSecond(new Integer(timeValStr.substring(8)));
                } else {
                    uiTimeVO = null;
                }
                break;
            case 4:
            case 8:
                /** ddHHMMSS */
                if (TimeModeUtils.validateTimeMode(timeMode, timeVal)) {
                    final String timeValStr = timeVal.toString().trim();                    
                    uiTimeVO.setDay(new Integer(timeValStr.substring(0, 2)));
                    uiTimeVO.setHour(new Integer(timeValStr.substring(2, 4)));
                    uiTimeVO.setMinute(new Integer(timeValStr.substring(4, 6)));
                    uiTimeVO.setSecond(new Integer(timeValStr.substring(6)));
                } else {
                    uiTimeVO = null;
                }
                break;
           default:
               uiTimeVO = null;
        }
        return uiTimeVO;
    }
    
    public static ActionNotificationQueryConditionVO transformActionNotificationQueryConditionVO(final Integer pageNo,
            final Integer pageSize, final Long notifyId, final Long actionId) {
        final ActionNotificationQueryConditionVO conditionVO = new ActionNotificationQueryConditionVO();
        conditionVO.setPageNo(pageNo);
        conditionVO.setPageSize(pageSize);
        conditionVO.setActionId(actionId);
        conditionVO.setNotifyId(notifyId);
        return conditionVO;
    }
    
    public static PageInfo<UIActionNotificationVO> transformToUIActionNotificationVOPageInfo(final PageInfo<ActionNotificationVO> pageInfo) {
        final PageInfo<UIActionNotificationVO> uiPageInfo = new PageInfo<UIActionNotificationVO>();
        if (pageInfo == null || ValidateUtil.isEmpty(pageInfo.getResult()))
        {
            return uiPageInfo.emptyPageInfo();
        }
        List<UIActionNotificationVO> uiList = new ArrayList<UIActionNotificationVO>();
        for (final ActionNotificationVO vo : pageInfo.getResult()) {
            final UIActionNotificationVO uiVO = new UIActionNotificationVO();
            final UINotifyActionRelVO uiNotifyActionRelVO = new UINotifyActionRelVO();
            BeanUtils.copyProperties(vo.getNotifyActionRelVO(), uiNotifyActionRelVO);
            uiVO.setNotification(uiNotifyActionRelVO);
            /** UINotificationTimeLimit */
            transformToUINotificationTimeLimitVO(uiVO, vo.getNotifyTimeLimitList());
            /** UINotificationNumLimit */
            transformToUINotificationNumLimitVO(uiVO, vo.getNotifyNumLimitList());            
            uiList.add(uiVO);
        }
        return uiPageInfo.createPageInfo(pageInfo.getTotalSize(), uiList);
    }
    
    private static void transformToUINotificationTimeLimitVO(
            final UIActionNotificationVO uiVO,
            final List<NotificationTimeLimitVO> timeLimitList) {
        if (ValidateUtil.isNotEmpty(timeLimitList)) {
            uiVO.setTimeLimittList(new ArrayList<UINotificationTimeLimitVO>());
            for (final NotificationTimeLimitVO timeLimitVO : timeLimitList) {
                final UINotificationTimeLimitVO uiTimeLimitVO = new UINotificationTimeLimitVO();
                BeanUtils.copyProperties(timeLimitVO, uiTimeLimitVO);                    
                /** UITimeSegDefVO */
                transformToUITimeSegDefVO(uiTimeLimitVO, timeLimitVO.getTimeSegmentList());
                uiVO.getTimeLimittList().add(uiTimeLimitVO);
            }
        }
    }
    
    private static void transformToUITimeSegDefVO(
            final UINotificationTimeLimitVO uiTimeLimitVO,
            final List<TimeSegDefVO> timeSegDefList) {
        if (ValidateUtil.isNotEmpty(timeSegDefList)) {
            uiTimeLimitVO.setTimeSegmentList(new ArrayList<UITimeSegDefVO>());
            for (final TimeSegDefVO timeSegDef : timeSegDefList) {
                final UITimeSegDefVO uiTimeSegDef = new UITimeSegDefVO();
                BeanUtils.copyProperties(timeSegDef, uiTimeSegDef);
                /** UITimeSegDtlVO */
                transformToUITimeSegDtlVO(uiTimeSegDef, timeSegDef.getTimeSegmentDtlList());
                uiTimeLimitVO.getTimeSegmentList().add(uiTimeSegDef);
            }
        }
    }
    
    private static void transformToUITimeSegDtlVO(
            final UITimeSegDefVO uiTimeSegDef,
            final List<TimeSegDtlVO> timeSegDtlList) {
        if (ValidateUtil.isNotEmpty(timeSegDtlList)) {
            uiTimeSegDef.setSegmentDtlList(new ArrayList<UITimeSegDtlVO>());
            for (final TimeSegDtlVO dtlVO : timeSegDtlList) {
                final UITimeSegDtlVO uiDtlVO = new UITimeSegDtlVO();
                BeanUtils.copyProperties(dtlVO, uiDtlVO, "startVal", "endVal");
                /** transform to UITimeVO */
                uiDtlVO.setStartVal(convertLongToUITimeVO(dtlVO.getStartVal(), uiTimeSegDef.getTimeMode()));
                uiDtlVO.setEndVal(convertLongToUITimeVO(dtlVO.getEndVal(), uiTimeSegDef.getTimeMode())); 
                uiTimeSegDef.getSegmentDtlList().add(uiDtlVO);
            }
        }
    }
    
    private static void transformToUINotificationNumLimitVO(
            final UIActionNotificationVO uiVO,
            final List<NotificationNumLimitVO> numLimitList) {
        if (ValidateUtil.isNotEmpty(numLimitList)) {
            uiVO.setCountLimitList(new ArrayList<UINotificationNumLimitVO>());
            for (final NotificationNumLimitVO numLimit : numLimitList) {
                final UINotificationNumLimitVO uiNumLimit = new UINotificationNumLimitVO();
                BeanUtils.copyProperties(numLimit, uiNumLimit);
                uiVO.getCountLimitList().add(uiNumLimit);
            }
        }
    }

    public static UITemplateParamDefVO transformTemplateParamDefVOVOToUITemplateParamDefVO(TemplateParamDefVO templateParamDefVO)
    {
        UITemplateParamDefVO uiTemplateParamDefVO = new UITemplateParamDefVO();
        if (ValidateUtil.isEmpty(templateParamDefVO))
        {
            return uiTemplateParamDefVO;
        }
        uiTemplateParamDefVO.setParamId(templateParamDefVO.getParamId());
        uiTemplateParamDefVO.setParamName(templateParamDefVO.getParamName());
        uiTemplateParamDefVO.setParamDesc(templateParamDefVO.getParamDesc());
        return uiTemplateParamDefVO;
    }
    
}
