package com.asiainfo.meo.customer.device.app.repository;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.asiainfo.meo.customer.device.app.model.entity.DeviceInfo;



public interface DeviceInfoRepository
{
    /**
     * @Description: 保存设备信息
     * @Description: save device information
     * @author liuyang
     * @param deviceInfo 设备信息实体 device information entity
     */
    void saveDeviceInfo(DeviceInfo deviceInfo);
    
    /**
     * @Description: 注销设备信息
     * @Description: delete device information
     * @author liuyang
     * @param deviceInfo 设备信息实体 device information entity
     */
    void deleteDeviceInfo(DeviceInfo deviceInfo);
    
    /**
     * @Description: 获得设备信息
     * @Description: get device information
     * @author liuyang
     * @param detachedCriteria 查询条件 select condition
     */
    List<DeviceInfo> getDeviceInfo(DetachedCriteria detachedCriteria);

    DeviceInfo getDeviceInfoByUdid(String udid,Long userId);
    
    List<DeviceInfo> getDeviceInfoByUserIdandOSType(Integer osType, Long userId);
}
