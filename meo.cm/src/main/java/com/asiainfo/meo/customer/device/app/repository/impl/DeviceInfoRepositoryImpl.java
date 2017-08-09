package com.asiainfo.meo.customer.device.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.customer.device.app.model.entity.DeviceInfo;
import com.asiainfo.meo.customer.device.app.repository.DeviceInfoRepository;

public class DeviceInfoRepositoryImpl implements DeviceInfoRepository
{
    private final static String SEQ_DEVICE_INFO_ID = "SEQ_DEVICE_INFO_ID";
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    /**
     * @Description: 保存设备信息
     * @Description: save device information
     * @author liuyang
     * @param deviceInfo 设备信息实体 device information entity
     */
    
    public void saveDeviceInfo(DeviceInfo deviceInfo)
    {
        deviceInfo.setDeviceId(ServiceLocatorFactory.getService(Sequence.class).next(SEQ_DEVICE_INFO_ID));
        hibernateRepository.saveObject(deviceInfo);
    }
    
    /**
     * @Description: 注销设备信息
     * @Description: delete device information
     * @author liuyang
     * @param deviceInfo 设备信息实体 device information entity
     */
    
    public void deleteDeviceInfo(DeviceInfo deviceInfo)
    {
        hibernateRepository.deleteObject(deviceInfo);
    }
    
    /**
     * @Description: 获得设备信息
     * @Description: get device information
     * @author liuyang
     * @param detachedCriteria 查询条件 select condition
     */
    @SuppressWarnings("unchecked")
    public List<DeviceInfo> getDeviceInfo(DetachedCriteria detachedCriteria)
    {
        return (List<DeviceInfo>) hibernateRepository.findByCriteria(detachedCriteria);
    }
    
    public DeviceInfo getDeviceInfoByUdid(String udid, Long userId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(DeviceInfo.class);
        criteria.add(Restrictions.eq("udid", udid));
        criteria.add(Restrictions.eq("userId", userId));
        criteria.add(Restrictions.eq("sts", DeviceInfo.STS_VALID));
        
        return hibernateRepository.findUniqueObjectByCriteria(criteria);
    }
    
    @Override
    public List<DeviceInfo> getDeviceInfoByUserIdandOSType(Integer osType, Long userId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(DeviceInfo.class);
        if (ValidateUtil.isNotEmpty(osType))
        {
            criteria.add(Restrictions.eq("osType", osType));
        }
        criteria.add(Restrictions.eq("userId", userId));
        criteria.add(Restrictions.eq("sts", DeviceInfo.STS_VALID));
        return (List<DeviceInfo>)hibernateRepository.findByCriteria(criteria);
    }
}
