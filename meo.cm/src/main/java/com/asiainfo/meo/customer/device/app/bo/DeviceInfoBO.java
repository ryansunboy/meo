package com.asiainfo.meo.customer.device.app.bo;

import java.sql.Timestamp;
import java.util.List;

import com.asiainfo.meo.customer.device.app.model.entity.DeviceInfo;
import com.asiainfo.meo.customer.device.app.model.vo.DeviceInfoVO;


/**
 * @Description: 设备信息BO
 * @Description: device information BO
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @author zhaozx
 * @Date 2015-3-14 下午04:43:39
 * @version 1.0
 */
public interface DeviceInfoBO
{
    /**
     * @Description: 创建设备信息
     * @Description: create device information
     * @author zhaozx
     * @param deviceInfo 设备信息实体 deviceinfo entity
     */
    void saveDeviceInfo(DeviceInfo deviceInfo);
    
    /**
     * @Description: 注销设备信息
     * @Description: deactivate device information
     * @author zhaozx
     * @param userId user id
     * @param modifyDate modify date
     */
    void deleteDeviceInfo(long userId, Timestamp modifyDate);
    
    /**
     * @Description: 获得最后设备登录的信息
     * @Description: get last login information
     * @author zhaozx
     * @param userId user id
     * @param time last login times
     * @return
     */
    //List<DeviceInfoHis> getLastLoginDeviceInfo(long userId, int time);
    
    DeviceInfo getDeviceInfoByUdid(String udid,Long userId);
    
    public List<DeviceInfoVO> getDeviceInfoByUserIdandOSType(Integer osType, Long userId);
    
}
