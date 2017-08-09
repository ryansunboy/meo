package com.asiainfo.meo.meo.notification.configuration.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.meo.notification.configuration.bo.ConfigurationBO;
import com.asiainfo.meo.meo.notification.configuration.model.entity.SysNotificationDef;
import com.asiainfo.meo.meo.notification.configuration.model.entity.SysNotificationNumLimit;
import com.asiainfo.meo.meo.notification.configuration.model.entity.SysNotificationTimeLimit;
import com.asiainfo.meo.meo.notification.configuration.model.entity.SysNotifyActionRel;
import com.asiainfo.meo.meo.notification.configuration.model.entity.SysTimeSegDef;
import com.asiainfo.meo.meo.notification.configuration.model.entity.SysTimeSegDtl;
import com.asiainfo.meo.meo.notification.configuration.model.vo.ActionNotificationQueryConditionVO;
import com.asiainfo.meo.meo.notification.configuration.model.vo.ActionNotificationVO;
import com.asiainfo.meo.meo.notification.configuration.model.vo.NotificationDefVO;
import com.asiainfo.meo.meo.notification.configuration.model.vo.NotificationNumLimitVO;
import com.asiainfo.meo.meo.notification.configuration.model.vo.NotificationTimeLimitVO;
import com.asiainfo.meo.meo.notification.configuration.model.vo.NotifyActionRelVO;
import com.asiainfo.meo.meo.notification.configuration.model.vo.TimeSegDefVO;
import com.asiainfo.meo.meo.notification.configuration.model.vo.TimeSegDtlVO;
import com.asiainfo.meo.meo.notification.configuration.respository.ConfigurationRepository;
import com.asiainfo.meo.meo.notification.define.NotifyEnumDefine;
import com.asiainfo.meo.meo.notification.define.NotifyErrorCodeDefine;
import com.asiainfo.meo.meo.notification.service.require.NotifyRserviceBO;
import com.asiainfo.meo.meo.notification.utils.TimeModeUtils;
import com.asiainfo.meo.system.common.app.model.vo.ActionDefine;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;
import com.asiainfo.meo.system.define.SysErrorCodeDefine;
import com.asiainfo.meo.system.template.app.model.vo.MessageTemplateDefVO;

public class ConfigurationBOImpl implements ConfigurationBO
{
    @Resource
    ConfigurationRepository configurationRepository;
    
    @Resource
    NotifyRserviceBO notifyRserviceBO;
    
    @Override
    public Long createSysNotificationDef(NotificationDefVO notification)
    {
        SysNotificationDef sysNotificationDef = new SysNotificationDef();
        sysNotificationDef.setNotifName(notification.getNotifName());
        MessageTemplateDefVO messageTemplateDefVO = notifyRserviceBO.getSysMessageTemplateDefByTemplateId(notification.getTemplateId());
        if (ValidateUtil.isEmpty(messageTemplateDefVO))
        {
            throw new MeoException(SysErrorCodeDefine.MESSAGE_TEMPLATE_DOES_NOT_EXIST, new Object[]{notification.getTemplateId()});
        }
        sysNotificationDef.setTemplateId(notification.getTemplateId());
        sysNotificationDef.setNotifDesc(notification.getNotifDesc());
        sysNotificationDef.setOperator(notification.getOperatorId());
        sysNotificationDef.setNotifType(notification.getNotifTypeId());
        configurationRepository.createSysNotificationDef(sysNotificationDef);
        return sysNotificationDef.getNotifyId();
    }

    @Override
    public void deleteSysNotificationDef(Long notifId)
    {
        SysNotificationDef  sysNotificationDef = configurationRepository.getSysNotificationDefByNotifId(notifId);
        if (ValidateUtil.isEmpty(sysNotificationDef))
        {
            throw new MeoException(NotifyErrorCodeDefine.NOTIFICATION_DEFINE_DOES_NOT_EXIST, new Object[]{notifId});
        }
        sysNotificationDef.setSts(SysNotificationDef.STS_INVALID);
        configurationRepository.modifySysNotificationDef(sysNotificationDef);
    }

    @Override
    public void modifySysNotificationDef(NotificationDefVO notification)
    {
        SysNotificationDef  sysNotificationDef = configurationRepository.getSysNotificationDefByNotifId(notification.getNotifId());
        if (ValidateUtil.isEmpty(sysNotificationDef))
        {
            throw new MeoException(NotifyErrorCodeDefine.NOTIFICATION_DEFINE_DOES_NOT_EXIST, new Object[]{notification.getNotifId()});
        }
        sysNotificationDef.setNotifName(notification.getNotifName());
        sysNotificationDef.setNotifType(notification.getNotifTypeId());
        sysNotificationDef.setTemplateId(notification.getTemplateId());
        if (notification.getNotifDesc() != null)
        {
            sysNotificationDef.setNotifDesc(notification.getNotifDesc());
        }
        configurationRepository.modifySysNotificationDef(sysNotificationDef);
        
    }

    @Override
    public PageInfo<NotificationDefVO> getSysNotificationDefList(Integer pageNo, Integer pageSize, Long notifId, String notifName)
    {
        PageInfo<NotificationDefVO> page = new PageInfo<NotificationDefVO>();
        List<SysNotificationDef> sysNotificationDefList = configurationRepository.getSysNotificationDefList(pageNo, pageSize, notifId, notifName);
        Integer totalSize = configurationRepository.getSysNotificationDefTotalSize();
        List<NotificationDefVO> list = new ArrayList<NotificationDefVO>();
        for (SysNotificationDef sysNotificationDef : sysNotificationDefList)
        {
            NotificationDefVO notificationDefVO = new NotificationDefVO();
            notificationDefVO.setNotifId(sysNotificationDef.getNotifyId());
            notificationDefVO.setNotifName(sysNotificationDef.getNotifName());
            notificationDefVO.setTemplateId(sysNotificationDef.getTemplateId());
            if (ValidateUtil.isNotEmpty(sysNotificationDef.getTemplateId()))
            {
                MessageTemplateDefVO  messageTemplateDefVO = notifyRserviceBO.getSysMessageTemplateDefByTemplateId(sysNotificationDef.getTemplateId());
                if (ValidateUtil.isNotEmpty(messageTemplateDefVO))
                {
                    notificationDefVO.setTemplateName(messageTemplateDefVO.getTemplateName());
                }
            }
            notificationDefVO.setNotifDesc(sysNotificationDef.getNotifDesc());
            notificationDefVO.setNotifTypeId(sysNotificationDef.getNotifType());
            List<EnumDefine> enumDefineList = notifyRserviceBO.getEnumByCode(String.valueOf(sysNotificationDef.getNotifType()), EnumDefine.NOTIFACTION_TYPE);
            for (EnumDefine enumDefine : enumDefineList)
            {
                notificationDefVO.setNotifType(enumDefine.getEnumName());
            }
            list.add(notificationDefVO);
        }
        return page.createPageInfo(pageNo, pageSize, totalSize, list);
    }

    @Override
    public NotificationDefVO getSysNotificationDefVOByNotifId(Long notifId)
    {
        SysNotificationDef sysNotificationDef = configurationRepository.getSysNotificationDefByNotifId(notifId);
        NotificationDefVO notificationDefVO = new NotificationDefVO();
        if (ValidateUtil.isEmpty(sysNotificationDef))
        {
            return null;
        }
        notificationDefVO.setNotifId(sysNotificationDef.getNotifyId());
        notificationDefVO.setNotifName(sysNotificationDef.getNotifName());
        notificationDefVO.setTemplateId(sysNotificationDef.getTemplateId());
        if (ValidateUtil.isNotEmpty(sysNotificationDef.getTemplateId()))
        {
            MessageTemplateDefVO  messageTemplateDefVO = notifyRserviceBO.getSysMessageTemplateDefByTemplateId(sysNotificationDef.getTemplateId());
            if (ValidateUtil.isNotEmpty(messageTemplateDefVO))
            {
                notificationDefVO.setTemplateName(messageTemplateDefVO.getTemplateName());
            }
        }
        notificationDefVO.setNotifDesc(sysNotificationDef.getNotifDesc());
        notificationDefVO.setNotifTypeId(sysNotificationDef.getNotifType());
        List<EnumDefine> enumDefineList = notifyRserviceBO.getEnumByCode(String.valueOf(sysNotificationDef.getNotifType()), EnumDefine.NOTIFACTION_TYPE);
        for (EnumDefine enumDefine : enumDefineList)
        {
            notificationDefVO.setNotifType(enumDefine.getEnumName());
        }
        return notificationDefVO;
    }

    private void validateAndExecuteSysNotifyActionRel(final NotifyActionRelVO notifyActionRelVO) throws MeoException {
        /** Check null for NotifyActionRelVO */
        if (ValidateUtil.isNull(notifyActionRelVO))
        {
            throw new MeoException(NotifyErrorCodeDefine.NOTIFICATION_ACTION_REL_IS_NULL);
        }
        /** Checking existence of action ID */        
        if (ValidateUtil.isEmpty(notifyActionRelVO.getActionId())) {
            throw new MeoException(NotifyErrorCodeDefine.NOTIFICATION_ACTION_ACTION_ID_IS_EMPTY);
        } else {
            if (ValidateUtil.isEmpty(notifyRserviceBO.getActionDefine(notifyActionRelVO.getActionId()))) {
                throw new MeoException(NotifyErrorCodeDefine.NOTIFICATION_ACTION_ACTION_ID_NOT_EXIST,
                        new Object[]{notifyActionRelVO.getActionId()});
            }
        }
        /** Checking existence of notify ID */ 
        if (ValidateUtil.isEmpty(notifyActionRelVO.getNotifyId())) {
            throw new MeoException(NotifyErrorCodeDefine.NOTIFICATION_ACTION_REL_NOTIFY_ID_IS_EMPTY);
        } else {
            if (ValidateUtil.isEmpty(configurationRepository.getSysNotificationDefByNotifId(notifyActionRelVO.getNotifyId()))) {
                throw new MeoException(NotifyErrorCodeDefine.NOTIFICATION_DEFINE_DOES_NOT_EXIST,
                        new Object[]{notifyActionRelVO.getNotifyId()});
            }
        }
        /** checking existence of receiverType */
        if (ValidateUtil.isEmpty(notifyActionRelVO.getReceiverType())) {
            throw new MeoException(NotifyErrorCodeDefine.NOTIFICATION_ACTION_REL_RECEIVER_TYPE_IS_EMPTY);
        }
        /** execution */
        final SysNotifyActionRel notifyActionRel = new SysNotifyActionRel();
        BeanUtils.copyProperties(notifyActionRelVO, notifyActionRel, "relId");
        final Long relId = configurationRepository.createSysNotifyActionRel(notifyActionRel);
        notifyActionRelVO.setRelId(relId);
    }
    
    private void validateAndExecuteTimeSegDtl(final TimeSegDtlVO timeSegDtlVO, final Integer timeMode) throws MeoException {
        /** execution */
        final SysTimeSegDtl sysTimeSegDtl = new SysTimeSegDtl();
        /** validate time mode */
        if (!TimeModeUtils.validateTimeMode(timeMode, timeSegDtlVO.getStartVal())) {
            throw new MeoException(NotifyErrorCodeDefine.NOTIFICATION_ACTION_TIME_MODE_ERROR, new Object[]{timeMode, timeSegDtlVO.getStartVal()});
        }
        if (!TimeModeUtils.validateTimeMode(timeMode, timeSegDtlVO.getEndVal())) {
            throw new MeoException(NotifyErrorCodeDefine.NOTIFICATION_ACTION_TIME_MODE_ERROR, new Object[]{timeMode, timeSegDtlVO.getEndVal()});
        }
        
        BeanUtils.copyProperties(timeSegDtlVO, sysTimeSegDtl, "dtlId");
        final Long dtlId = configurationRepository.createSysTimeSegDtl(sysTimeSegDtl);
        timeSegDtlVO.setDtlId(dtlId);
    }
    
    private void validateAndExecuteTimeSegDef(final TimeSegDefVO timeSegDefVO) throws MeoException {
        /** checking time mode */
        if (ValidateUtil.isEmpty(timeSegDefVO.getTimeMode())) {
            throw new MeoException(NotifyErrorCodeDefine.NOTIFICATION_ACTION_ACTION_ID_IS_EMPTY);
        }
        /** checking time segment name */
        if (ValidateUtil.isEmpty(timeSegDefVO.getSegName())) {
            throw new MeoException(NotifyErrorCodeDefine.NOTIFICATION_ACTION_SEG_NAME_IS_EMPTY);
        }
        /** execution */
        final SysTimeSegDef sysTimeSegDef = new SysTimeSegDef();
        BeanUtils.copyProperties(timeSegDefVO, sysTimeSegDef, "segId");
        final Long segId = configurationRepository.createSysTimeSegDef(sysTimeSegDef);
        timeSegDefVO.setSegId(segId);
        /** validate time segment detail (if any) */
        if (ValidateUtil.isNotEmpty(timeSegDefVO.getTimeSegmentDtlList())) {
            for (final TimeSegDtlVO vo : timeSegDefVO.getTimeSegmentDtlList()) {                
                vo.setSegId(timeSegDefVO.getSegId());
                validateAndExecuteTimeSegDtl(vo, timeSegDefVO.getTimeMode());                
            }
        }
        
    }
    
    private void validateAndExecuteNotificationTimeLimit(final NotificationTimeLimitVO notificationTimeLimitVO) throws MeoException {
        /** check existence of action ID */
        if (ValidateUtil.isEmpty(notificationTimeLimitVO.getActionId())) {
            throw new MeoException(NotifyErrorCodeDefine.NOTIFICATION_ACTION_ACTION_ID_IS_EMPTY);
        } else {
            if (ValidateUtil.isEmpty(notifyRserviceBO.getActionDefine(notificationTimeLimitVO.getActionId()))) {
                throw new MeoException(NotifyErrorCodeDefine.NOTIFICATION_ACTION_ACTION_ID_NOT_EXIST,
                        new Object[]{notificationTimeLimitVO.getActionId()});
            }
        }
        /** validate region code */
        if (ValidateUtil.isEmpty(notificationTimeLimitVO.getRegionCode())) {
            throw new MeoException(NotifyErrorCodeDefine.NOTIFICATION_ACTION_REGION_CODE_IS_EMPTY);
        }
        /** validate TimeSegmentList (if any) */
        if (ValidateUtil.isNotEmpty(notificationTimeLimitVO.getTimeSegmentList())) {
            for (final TimeSegDefVO timeSegDefVO : notificationTimeLimitVO.getTimeSegmentList()) {
                validateAndExecuteTimeSegDef(timeSegDefVO);
                final SysNotificationTimeLimit notificationTimeLimit = new SysNotificationTimeLimit();
                notificationTimeLimitVO.setSegId(timeSegDefVO.getSegId());
                BeanUtils.copyProperties(notificationTimeLimitVO, notificationTimeLimit, "timeLimitId");
                final Long timeLimitId = configurationRepository.createSysNotificationTimeLimit(notificationTimeLimit);
                notificationTimeLimitVO.setTimeLimitId(timeLimitId);
            }
        } else {
            final SysNotificationTimeLimit notificationTimeLimit = new SysNotificationTimeLimit();
            notificationTimeLimitVO.setSegId(null);
            BeanUtils.copyProperties(notificationTimeLimitVO, notificationTimeLimit);
            final Long timeLimitId = configurationRepository.createSysNotificationTimeLimit(notificationTimeLimit);
            notificationTimeLimitVO.setTimeLimitId(timeLimitId);
        }
    }
    
    private void validateAndExecuteNotificationNumLimit(final NotificationNumLimitVO notificationNumLimitVO) throws MeoException {
        /** checking action id */
        if (ValidateUtil.isEmpty(notificationNumLimitVO.getActionId())) {
            throw new MeoException(NotifyErrorCodeDefine.NOTIFICATION_ACTION_ACTION_ID_IS_EMPTY);
        } else {
            if (ValidateUtil.isEmpty(notifyRserviceBO.getActionDefine(notificationNumLimitVO.getActionId()))) {
                throw new MeoException(NotifyErrorCodeDefine.NOTIFICATION_ACTION_ACTION_ID_NOT_EXIST,
                        new Object[]{notificationNumLimitVO.getActionId()});
            }
        }
        /** checking region code */
        if (ValidateUtil.isEmpty(notificationNumLimitVO.getRegionCode())) {
            throw new MeoException(NotifyErrorCodeDefine.NOTIFICATION_ACTION_REGION_CODE_IS_EMPTY);
        }
        /** checking maximum number */
        if (ValidateUtil.isEmpty(notificationNumLimitVO.getMaxNum())) {
            throw new MeoException(NotifyErrorCodeDefine.NOTIFICATION_ACTION_MAX_NUM_IS_EMPTY);
        }
        /** checking cycle type */
        if (ValidateUtil.isEmpty(notificationNumLimitVO.getCycleType())) {
            throw new MeoException(NotifyErrorCodeDefine.NOTIFICATION_ACTION_CYCLE_TYPE_IS_EMPTY);
        }
        /** execution */
        final SysNotificationNumLimit notificationNumLimit = new SysNotificationNumLimit();
        BeanUtils.copyProperties(notificationNumLimitVO, notificationNumLimit, "numLimitId");
        final Long numLimitId = configurationRepository.createSysNotificationNumLimit(notificationNumLimit);
        notificationNumLimitVO.setNumLimitId(numLimitId);
    }
    
    @Override
    public void createActionNotification(final ActionNotificationVO actionNotificationVO)
    {
        if (ValidateUtil.isNull(actionNotificationVO))
        {
            throw new MeoException(NotifyErrorCodeDefine.NOTIFICATION_ACTION_REL_IS_NULL);
        }
        /** 1. validate & execute SysNotifyActionRel */
        validateAndExecuteSysNotifyActionRel(actionNotificationVO.getNotifyActionRelVO());        
        /** 2. validate NotificationTimeLimit */
        if (ValidateUtil.isNotEmpty(actionNotificationVO.getNotifyTimeLimitList())) {
            for (final NotificationTimeLimitVO vo : actionNotificationVO.getNotifyTimeLimitList()) {
                validateAndExecuteNotificationTimeLimit(vo);
            }            
        }
        /** 3. validate NotificationNumLimit */
        if (ValidateUtil.isNotEmpty(actionNotificationVO.getNotifyNumLimitList())) {
            for (final NotificationNumLimitVO vo : actionNotificationVO.getNotifyNumLimitList()) {
                validateAndExecuteNotificationNumLimit(vo);
            }
        }

    }

    @Override
    public PageInfo<ActionNotificationVO> getActionNotificationByNotifyAndActionId(final ActionNotificationQueryConditionVO conditionVO)
    {
        Integer pageSize = PageInfo.DEFAULT_PAGE_SIZE;
        Integer pageNo = PageInfo.DEFAULT_PAGE_NO;
        if (ValidateUtil.isNotNull(conditionVO))
        {
            pageSize = ValidateUtil.checkPageSize(conditionVO.getPageSize());
            pageNo = ValidateUtil.checkPageNo(conditionVO.getPageNo());
            
        }
        final PageInfo<ActionNotificationVO> pageInfo = new PageInfo<ActionNotificationVO>();
        final List<ActionNotificationVO> actionNotificationList = new ArrayList<ActionNotificationVO>();
        final List<SysNotifyActionRel> notifyActionRelList = configurationRepository.
                getSysNotifyActionRelByNotifyIdAndActionId(conditionVO.getNotifyId(), conditionVO.getActionId());
        if (ValidateUtil.isNotEmpty(notifyActionRelList)) {
            for (final SysNotifyActionRel notifyActionRel : notifyActionRelList) {
                final ActionNotificationVO actionNotificationVO = new ActionNotificationVO();
                /** handle SysNotifyActionRel */
                actionNotificationVO.setNotifyActionRelVO(collectNotifyActionRelVO(notifyActionRel, null));
                /** handle SysNotificationTimeLimit */
                actionNotificationVO.setNotifyTimeLimitList(collectNotificationTimeLimit(notifyActionRel.getActionId()));
                /** handle SysNotificationNumLimit */
                actionNotificationVO.setNotifyNumLimitList(collectNotificationNumLimit(notifyActionRel.getActionId()));
                
                actionNotificationList.add(actionNotificationVO);
            }
            final List<ActionNotificationVO> paginationList = actionNotificationList.subList((pageNo - 1) * pageSize,
                    (actionNotificationList.size() < pageSize) ? actionNotificationList.size() : pageSize);
            return pageInfo.createPageInfo(pageNo, pageSize, actionNotificationList.size(), paginationList);
        }
        
        return pageInfo.emptyPageInfo(pageSize);
    }
    
    @Override
    public List<ActionNotificationVO> getActionNotificationByActionIdAndNotifyType(Long actionId, Integer notifyType)
    {
        final List<ActionNotificationVO> actionNotificationList = new ArrayList<ActionNotificationVO>();
        final List<SysNotificationDef> notifyDefList = configurationRepository.getSysNotificationDefByNotifyType(notifyType);
        for (SysNotificationDef notifyDef : notifyDefList) {
            final List<SysNotifyActionRel> notifyActionRelList = configurationRepository.
                    getSysNotifyActionRelByNotifyIdAndActionId(notifyDef.getNotifyId(), actionId);
            if (ValidateUtil.isNotEmpty(notifyActionRelList)) {
                for (final SysNotifyActionRel notifyActionRel : notifyActionRelList) {
                    final ActionNotificationVO actionNotificationVO = new ActionNotificationVO();
                    /** handle SysNotifyActionRel */
                    actionNotificationVO.setNotifyActionRelVO(collectNotifyActionRelVO(notifyActionRel, notifyDef.getTemplateId()));
                    /** handle SysNotificationTimeLimit */
                    actionNotificationVO.setNotifyTimeLimitList(collectNotificationTimeLimit(notifyActionRel.getActionId()));
                    /** handle SysNotificationNumLimit */
                    actionNotificationVO.setNotifyNumLimitList(collectNotificationNumLimit(notifyActionRel.getActionId()));
                    
                    actionNotificationList.add(actionNotificationVO);
                }
            }
            return actionNotificationList;
        }
        return null;
    }
    
    private NotifyActionRelVO collectNotifyActionRelVO(final SysNotifyActionRel notifyActionRel, final Long templateId) {
        if (ValidateUtil.isNotNull(notifyActionRel)) {
            final NotifyActionRelVO notifyActionRelVO = new NotifyActionRelVO();
            BeanUtils.copyProperties(notifyActionRel, notifyActionRelVO);
            /** getting notification name */
            final NotificationDefVO notifyDefVO = getSysNotificationDefVOByNotifId(notifyActionRel.getNotifyId());
            if (ValidateUtil.isNotNull(notifyDefVO) && ValidateUtil.isNotNull(notifyDefVO.getNotifName())) {
                notifyActionRelVO.setNotificationName(notifyDefVO.getNotifName());
            }
            /** getting action name */
            final List<ActionDefine> actionList = notifyRserviceBO.getActionDefine(notifyActionRel.getActionId());
            if (ValidateUtil.isNotEmpty(actionList)) {
                notifyActionRelVO.setActionName(actionList.get(0).getActionName());
            }
            /** getting receiver type */
            if (ValidateUtil.isNotEmpty(notifyActionRel.getReceiverType())) {
                List<EnumDefine> receiverTypeList = notifyRserviceBO.getEnumByCode(
                        notifyActionRel.getReceiverType().toString(),
                        NotifyEnumDefine.RECEIVER_ENUM_TYPE.toString());
                if (ValidateUtil.isNotEmpty(receiverTypeList))
                    notifyActionRelVO.setReceiverTypeName(receiverTypeList.get(0).getEnumName());
            }
            /** template Id */
            if (ValidateUtil.isNotNull(templateId)) {
                notifyActionRelVO.setTemplateId(templateId);
            }
            
            return notifyActionRelVO;
        }
        return null;
    }
     
    private List<NotificationNumLimitVO> collectNotificationNumLimit(final Long actionId) {
        if (ValidateUtil.isNotNull(actionId)) {
            /** getting action name */
            final List<ActionDefine> actionList = notifyRserviceBO.getActionDefine(actionId);
            /** getting  NotificationNumLimit by action id*/
            List<SysNotificationNumLimit> numLimitList = configurationRepository.getSysNotificationNumLimit(actionId);
            List<NotificationNumLimitVO> numLimitVOList = null;
            if (ValidateUtil.isNotEmpty(numLimitList)) {
                numLimitVOList = new ArrayList<NotificationNumLimitVO>();
                for (final SysNotificationNumLimit numLimit : numLimitList) {
                    final NotificationNumLimitVO numLimitVO = new NotificationNumLimitVO();
                    BeanUtils.copyProperties(numLimit, numLimitVO);                    
                    if (ValidateUtil.isNotEmpty(actionList)) {
                        numLimitVO.setActionName(actionList.get(0).getActionName());
                    }
                    numLimitVOList.add(numLimitVO);
                }
            }
            
            return numLimitVOList;
        }
        return null;
    }
    
    private List<NotificationTimeLimitVO> collectNotificationTimeLimit(final Long actionId) {
        if (ValidateUtil.isNotNull(actionId)) {
            /** getting action name */
            final List<ActionDefine> actionList = notifyRserviceBO.getActionDefine(actionId);
            /** getting NotificationTimeLimit by action id */
           List<SysNotificationTimeLimit> timeLimitList = configurationRepository.getSysNotificationTimeLimitByActionId(actionId);
           List<NotificationTimeLimitVO> timeLimitVOList = null;
           if (ValidateUtil.isNotEmpty(timeLimitList)) {
               timeLimitVOList = new ArrayList<NotificationTimeLimitVO>();
               for (final SysNotificationTimeLimit timeLimit : timeLimitList) {
                   final NotificationTimeLimitVO timeLimitVO = new NotificationTimeLimitVO();
                   timeLimitVO.setTimeSegmentList(new ArrayList<TimeSegDefVO>());
                   BeanUtils.copyProperties(timeLimit, timeLimitVO);
                   /** setting action name */
                   if (ValidateUtil.isNotEmpty(actionList)) {
                       timeLimitVO.setActionName(actionList.get(0).getActionName());
                   }
                   /** getting time segment */
                   final TimeSegDefVO timeSegVO = collectTimeSegDef(timeLimit.getSegId());
                   if (ValidateUtil.isNotNull(timeSegVO)) {
                       timeLimitVO.getTimeSegmentList().add(timeSegVO);
                   }
                   timeLimitVOList.add(timeLimitVO);
               }
           }
           return timeLimitVOList;
        }
        return null;
    }
   
    private TimeSegDefVO collectTimeSegDef(final Long segId) {
        if (ValidateUtil.isNotNull(segId)) {
            List<SysTimeSegDef> timeSegList = configurationRepository.getSysTimeSegDefBySegId(segId);
            TimeSegDefVO timeSegVO = null;
            if (ValidateUtil.isNotEmpty(timeSegList)) {
                final SysTimeSegDef timeSeg = timeSegList.get(0);
                timeSegVO = new TimeSegDefVO();                
                BeanUtils.copyProperties(timeSeg, timeSegVO);
                /** setting time mode name */
                if (ValidateUtil.isNotNull(timeSeg.getTimeMode())) {
                    final List<EnumDefine> enumList = notifyRserviceBO.getEnumByCode(
                            timeSeg.getTimeMode().toString(),
                            NotifyEnumDefine.TIME_MODE_ENUM_TYPE.toString());
                    if (ValidateUtil.isNotEmpty(enumList))
                        timeSegVO.setTimeModeName(enumList.get(0).getEnumName());
                }
                /** getting time segment detail */
                List<TimeSegDtlVO> timeDetailList = collectTimeSegDtl(timeSeg.getSegId(), timeSeg.getTimeMode());
                if (ValidateUtil.isNotEmpty(timeDetailList)) {
                    timeSegVO.setTimeSegmentDtlList(timeDetailList);
                }
            }
            return timeSegVO;
        }
        return null;
    }
    
    private List<TimeSegDtlVO> collectTimeSegDtl(final Long segId, final Integer timeMode) {
        if (ValidateUtil.isNotNull(segId)) {
            List<SysTimeSegDtl> timeSegDtlList = configurationRepository.getSysTimeSegDtlBySegId(segId);
            List<TimeSegDtlVO> timeSegDtlVOList = null;
            if (ValidateUtil.isNotEmpty(timeSegDtlList)) {
                timeSegDtlVOList = new ArrayList<TimeSegDtlVO>();
                for (final SysTimeSegDtl timeSegDtl : timeSegDtlList) {
                    final TimeSegDtlVO vo = new TimeSegDtlVO();
                    /** validate time mode */
                    if (TimeModeUtils.validateTimeMode(timeMode, timeSegDtl.getStartVal())) {
                        vo.setStartVal(timeSegDtl.getStartVal());
                    }
                    if (TimeModeUtils.validateTimeMode(timeMode, timeSegDtl.getEndVal())) {
                        vo.setEndVal(timeSegDtl.getEndVal());
                    }                    
                    BeanUtils.copyProperties(timeSegDtl, vo, "startVal", "endVal");
                    timeSegDtlVOList.add(vo);
                }
            }
            return timeSegDtlVOList;
        }
        return null;
    }

    @Override
    public void deleteActionNotification(Long notifyId, Long actionId)
    {
        /** SysNotifyActionRel */
        final List<SysNotifyActionRel> notifyActionRelList = configurationRepository.getSysNotifyActionRelByNotifyIdAndActionId(notifyId, actionId);
        if (ValidateUtil.isNotEmpty(notifyActionRelList)) {
            for (final SysNotifyActionRel notifyActionRel : notifyActionRelList) {
                configurationRepository.deleteSysNotifyActionRel(notifyActionRel);
                /** SysNotificationNumLimit */
                final List<SysNotificationNumLimit> notifyNumLimitList = configurationRepository.
                    getSysNotificationNumLimit(notifyActionRel.getActionId());
                if (ValidateUtil.isNotEmpty(notifyNumLimitList)) {
                    for (final SysNotificationNumLimit numLimit : notifyNumLimitList) {
                        configurationRepository.deleteSysNotificationNumLimit(numLimit);
                    }
                }
                /** SysNotificationTimeLimit */
                final List<SysNotificationTimeLimit> timeLimitList = configurationRepository.
                        getSysNotificationTimeLimitByActionId(notifyActionRel.getActionId());
                if (ValidateUtil.isNotEmpty(timeLimitList)) {
                    for (final SysNotificationTimeLimit timeLimit : timeLimitList) {
                        configurationRepository.deleteSysNotificationTimeLimit(timeLimit);
                        /** SysTimeSegDef */
                        final List<SysTimeSegDef> timeSegDefList = configurationRepository.getSysTimeSegDefBySegId(timeLimit.getSegId());
                        if (ValidateUtil.isNotEmpty(timeSegDefList)) {
                            for (final SysTimeSegDef timeSeg : timeSegDefList) {
                                configurationRepository.deleteSysTimeSegDef(timeSeg);
                            }
                        }
                        /** SysTimeSegDtl */
                        List<SysTimeSegDtl> timeDtlList = configurationRepository.getSysTimeSegDtlBySegId(timeLimit.getSegId());
                        if (ValidateUtil.isNotEmpty(timeDtlList)) {
                            for (final SysTimeSegDtl timeDtl : timeDtlList) {
                                configurationRepository.deleteSysTimeSegDtl(timeDtl);
                            }
                        }
                    }
                }
            }
        } else {
            throw new MeoException(NotifyErrorCodeDefine.NOTIFICATION_ACTION_NOTIFY_ACTION_NOT_EXIST,
                    new Object[]{notifyId, actionId});
        }
        
    }    
    
}
