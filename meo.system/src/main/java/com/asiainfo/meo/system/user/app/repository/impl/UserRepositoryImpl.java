package com.asiainfo.meo.system.user.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.SequenceUtil;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.system.common.app.model.entity.SysPortalUser;
import com.asiainfo.meo.system.user.app.model.entity.SysCustomerLogin;
import com.asiainfo.meo.system.user.app.model.entity.SysCustomerPwd;
import com.asiainfo.meo.system.user.app.model.entity.SysPortalLogin;
import com.asiainfo.meo.system.user.app.model.entity.SysPortalPwd;
import com.asiainfo.meo.system.user.app.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository
{
    
    @Resource
    private HibernateRepository repository;
    
    public SysCustomerLogin querySysUserLogin(String loginAcct)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysCustomerLogin.class);
        criteria.add(Restrictions.eq("loginAcct", loginAcct));
        criteria.add(Restrictions.ne("sts", SysCustomerLogin.STS_USER_DEACTIVATE));
        List<SysCustomerLogin> userLogin = (List<SysCustomerLogin>) repository.findByCriteria(criteria);
        if (ValidateUtil.isNotEmpty(userLogin))
        {
            return userLogin.get(0);
        }
        return null;
    }
    
    public SysCustomerPwd querySysUserPwd(Long loginId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysCustomerPwd.class);
        criteria.add(Restrictions.eq("loginId", loginId));
        criteria.add(Restrictions.eq("sts", SysCustomerPwd.STS_PWD_ACTIVATE));
        List<SysCustomerPwd> userPwd = (List<SysCustomerPwd>) repository.findByCriteria(criteria);
        if (ValidateUtil.isNotEmpty(userPwd))
        {
            return userPwd.get(0);
        }
        
        return null;
    }
    
    public void saveSysUserLogin(SysCustomerLogin login)
    {
        login.setLoginId(SequenceUtil.getSequence(SysCustomerLogin.SYS_CUSTOMER_LOGIN_SEQ));
        login.setCreateTime(DateTimeUtil.getNow());
        login.setSts(SysCustomerLogin.STS_USER_INACTIVATE);
        repository.saveObject(login);
    }
    
    public void saveSysUserPwd(SysCustomerPwd pwd)
    {
        pwd.setId(SequenceUtil.getSequence(SysCustomerPwd.SYS_CUSTOMER_PWD_SEQ));
        pwd.setSts(SysCustomerPwd.STS_PWD_ACTIVATE);
        pwd.setCreateDate(DateTimeUtil.getNow());
        repository.saveObject(pwd);
    }
    
    public void saveSysPortalLogin(SysPortalLogin login)
    {
        login.setLoginId(SequenceUtil.getSequence(SysPortalLogin.SYS_PORTAL_LOGIN_SEQ));
        login.setCreateTime(DateTimeUtil.getNow());
        login.setSts(SysCustomerLogin.STS_USER_INACTIVATE);
        repository.saveObject(login);
    }
    
    public void saveSysPortalPwd(SysPortalPwd pwd)
    {
        pwd.setId(SequenceUtil.getSequence(SysPortalPwd.SYS_PORTAL_PWD_SEQ));
        pwd.setSts(SysCustomerPwd.STS_PWD_ACTIVATE);
        pwd.setCreateDate(DateTimeUtil.getNow());
        repository.saveObject(pwd);
    }
    
    public SysPortalLogin querySysPartnerLogin(String loginAcct)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysPortalLogin.class);
        criteria.add(Restrictions.eq("loginAcct", loginAcct));
        criteria.add(Restrictions.ne("sts", SysPortalLogin.STS_PORTAL_DEACTIVATE));
        SysPortalLogin userLogin = repository.findUniqueObjectByCriteria(criteria);
        if (ValidateUtil.isNotEmpty(userLogin))
        {
            return userLogin;
        }
        return null;
    }
    
    public SysPortalPwd querySysPartnerPwd(long loginId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysPortalPwd.class);
        criteria.add(Restrictions.eq("loginId", loginId));
        criteria.add(Restrictions.eq("sts", SysPortalPwd.STS_PWD_ACTIVATE));
        SysPortalPwd userPwd = repository.findUniqueObjectByCriteria(criteria);
        if (ValidateUtil.isNotEmpty(userPwd))
        {
            return userPwd;
        }
        
        return null;
    }
    
    public void updateCustomerPassword(SysCustomerPwd pwd)
    {
        if (ValidateUtil.isNotEmpty(pwd))
        {
            pwd.setModifyDate(DateTimeUtil.getNow());
            repository.updateObject(pwd);
        }
    }
    
    public void updatePortalPassword(SysPortalPwd pwd)
    {
        if (ValidateUtil.isNotEmpty(pwd))
        {
            pwd.setModifyDate(DateTimeUtil.getNow());
            repository.updateObject(pwd);
        }
        
    }
    
    public SysPortalUser querySysPortalUserByUserId(Long userId)
    {
        if (ValidateUtil.isNotEmpty(userId))
        {
            DetachedCriteria criteria = DetachedCriteria.forClass(SysPortalUser.class);
            criteria.add(Restrictions.eq("userId", userId));
            criteria.add(Restrictions.eq("sts", SysPortalUser.STS_ACTIVATE));
            return repository.findUniqueObjectByCriteria(criteria);
        }
        return null;
    }
    
    public void saveSysPortalUser(SysPortalUser portalUser)
    {
        if (ValidateUtil.isNotEmpty(portalUser))
        {
            portalUser.setCreateDate(DateTimeUtil.getNow());
            portalUser.setSts(SysPortalUser.STS_ACTIVATE);
            portalUser.setUserId(SequenceUtil.getSequence(SysPortalUser.SEQ_SYS_PORTAL_USER));
            repository.saveObject(portalUser);
        }
        
    }
    
    public SysCustomerLogin querySysCustLoginByCustId(Long objectId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysCustomerLogin.class);
        criteria.add(Restrictions.eq("custId", objectId));
        criteria.add(Restrictions.ne("sts", SysCustomerLogin.STS_USER_DEACTIVATE));
        
        return repository.findUniqueObjectByCriteria(criteria);
    }
    
    public void updateCustomerLogin(SysCustomerLogin login)
    {
        if (ValidateUtil.isNotEmpty(login))
        {
            login.setModifyTime(DateTimeUtil.getNow());
            repository.updateObject(login);
        }
        
    }
    
    public SysPortalLogin querySysPortalLoginByObjectId(Long objectId, Integer objectType)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(SysPortalUser.class);
        criteria.add(Restrictions.eq("objectId", objectId));
        criteria.add(Restrictions.eq("objectType", objectType));
        criteria.add(Restrictions.eq("sts", SysPortalUser.STS_ACTIVATE));
        SysPortalUser user = repository.findUniqueObjectByCriteria(criteria);
        if (ValidateUtil.isNotEmpty(user))
        {
            DetachedCriteria cri = DetachedCriteria.forClass(SysPortalLogin.class);
            cri.add(Restrictions.eq("userId", user.getUserId()));
            cri.add(Restrictions.ne("sts", SysPortalLogin.STS_PORTAL_DEACTIVATE));
            return repository.findUniqueObjectByCriteria(cri);
        }
        return null;
    }
    
    
    public void updatePortalLogin(SysPortalLogin login)
    {
        if (ValidateUtil.isNotEmpty(login))
        {
            login.setModifyTime(DateTimeUtil.getNow());
            repository.updateObject(login);
        }
        
    }

    @Override
    public SysPortalLogin querySysPortalLoginByUserId(Long userId)
    {
        DetachedCriteria cri = DetachedCriteria.forClass(SysPortalLogin.class);
        cri.add(Restrictions.eq("userId", userId));
        cri.add(Restrictions.eq("sts", SysPortalLogin.STS_PORTAL_ACTIVATE));
        return repository.findUniqueObjectByCriteria(cri);
    }
    
   
    
}
