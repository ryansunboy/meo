package com.asiainfo.meo.customer.device.app.bo.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.customer.device.app.bo.DeviceInfoBO;
import com.asiainfo.meo.customer.device.app.model.entity.DeviceInfo;
import com.asiainfo.meo.customer.device.app.model.entity.DeviceInfoHis;
import com.asiainfo.meo.customer.device.app.model.vo.DeviceInfoVO;
import com.asiainfo.meo.customer.device.app.repository.DeviceInfoRepository;


/**
 * @Description: 设备信息BO
 * @Description: device information BO
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @author zhaozx
 * @Date 2015-3-14 下午05:41:38
 * @version 1.0
 */
public class DeviceInfoBOImpl implements DeviceInfoBO
{
    @Resource
    DeviceInfoRepository    deviceInfoRepository;
    
    
    
    /**
     * @Description: 获得设备信息
     * @Description: get device info
     * @author zhaozx
     * @param criteria 查询条件 select condition
     * @return List<DeviceInfo>
     */
    private List<DeviceInfo> getDeviceInfo(DetachedCriteria criteria)
    {
        return deviceInfoRepository.getDeviceInfo(criteria);
    }
    
   /* *//**
     * @Description: 保存设备历史信息
     * @Description: save device history information
     * @author zhaozx
     * @param deviceInfoHis 设备历史信息实体 deviceInfoHis entity
     *//*
    private void saveDeviceInfoHis(DeviceInfoHis deviceInfoHis)
    {
        deviceInfoHisRepository.saveDeviceInfoHis(deviceInfoHis);// save device history informtion
    }*/
    
    /**
     * @Description: 注销设备信息
     * @Description: delete device information
     * @author zhaozx
     * @param deviceInfo 设备信息实体 deviceInfo entity
     */
    private void deleteDeviceInfo(DeviceInfo deviceInfo)
    {
        deviceInfoRepository.deleteDeviceInfo(deviceInfo);
    }
    
   /* *//**
     * @Description: 获得设备信息历史
     * @Description: get device information history
     * @author zhaozx
     * @param criteria 查询条件 select condition
     * @param firstResults 起始位置 start location
     * @param maxResults 查询多少个 select number
     * @return List<DeviceInfoHis>
     *//*
    private List<DeviceInfoHis> getDeviceInfoHis(DetachedCriteria criteria, int firstResults, int maxResults)
    {
        return deviceInfoHisRepository.getDeviceInfoHis(criteria, firstResults, maxResults);
    }*/
    
    /**
     * @Description: 创建设备信息
     * @Description: create device information
     * @author zhaozx
     * @param deviceInfo 设备信息实体 deviceinfo entity
     */
    public void saveDeviceInfo(DeviceInfo deviceInfo)
    {
        Timestamp modifyDate = DateTimeUtil.getNow();
        deviceInfo.setCreateDate(modifyDate);
        //deviceInfo.setModifyDate(modifyDate);
        deviceInfo.setSts(DeviceInfo.STS_VALID);
        deviceInfoRepository.saveDeviceInfo(deviceInfo);
    }
    
    /**
     * @Description: 注销设备信息
     * @Description: deactivate device information
     * @author zhaozx
     * @param userId 用户id user id
     * @param modifyDate 修改时间 modify date
     */
    public void deleteDeviceInfo(long userId, Timestamp modifyDate)
    {
        // select condition for DeviceInfo
        DetachedCriteria criteria = DetachedCriteria.forClass(DeviceInfo.class).add(Restrictions.eq("userId", userId));
        
        List<DeviceInfo> deviceInfoList = getDeviceInfo(criteria);// get device information list
        
        if (deviceInfoList!= null&& deviceInfoList.size()> 0)
        {
            DeviceInfo deviceInfo = deviceInfoList.get(0);// get device information
            
            DeviceInfoHis deviceInfoHis = new DeviceInfoHis();
            
           // copyDeviceInfoToHistory(deviceInfo, deviceInfoHis, modifyDate);// copy device information to history
            
            //saveDeviceInfoHis(deviceInfoHis);// save device history informtion
            
            deleteDeviceInfo(deviceInfo);// delete device information
        }
    }

    /**
     * @Description:根据设备唯一标识查询设备信息
     * @Description: get the device information with udid
     * @author zhaozx
     * @param userId user id
     * @param time last login times
     * @return
     */
  
    public DeviceInfo getDeviceInfoByUdid(String udid,Long userId)
    {
        if(ValidateUtil.isNotNull(udid)&&!udid.equals("")&&ValidateUtil.isNotEmpty(userId)){
            return deviceInfoRepository.getDeviceInfoByUdid(udid,userId);
        }
        return null;
      
        
    }

    @Override
    public List<DeviceInfoVO> getDeviceInfoByUserIdandOSType(Integer osType, Long userId)
    {
        if (ValidateUtil.isEmpty(userId))
        {
            return null;
        }
        List<DeviceInfoVO> list = new ArrayList<DeviceInfoVO>();
        List<DeviceInfo> deviceInfoList = deviceInfoRepository.getDeviceInfoByUserIdandOSType(osType, userId);
        for(DeviceInfo deviceInfo : deviceInfoList) 
        {
            DeviceInfoVO deviceInfoVO = new DeviceInfoVO();
            deviceInfoVO.setDeviceId(deviceInfo.getDeviceId());
            deviceInfoVO.setDeviceToken(deviceInfo.getDeviceToken());
            deviceInfoVO.setDeviceType(deviceInfo.getDeviceType());
            deviceInfoVO.setOsType(deviceInfo.getOsType());
            deviceInfoVO.setOsVersion(deviceInfo.getOsVersion());
            deviceInfoVO.setUdid(deviceInfo.getUdid());
            deviceInfoVO.setUserId(deviceInfo.getUserId());
            list.add(deviceInfoVO);
        }
        return list;
    }
    
    /**
     * @Description: 获得最后设备登录的信息
     * @Description: get last login information
     * @author zhaozx
     * @param userId user id
     * @param time last login times
     * @return
     */
    /*  @Override
    public List<DeviceInfoHis> getLastLoginDeviceInfo(long userId, int time)
    {
        // select condition for DeviceInfoHis
        DetachedCriteria criteria = DetachedCriteria.forClass(DeviceInfoHis.class).add(Restrictions.eq("userId", userId))
                .addOrder(Order.desc("modifyDate"));
        
        List<DeviceInfoHis> deviceInfoHisList = getDeviceInfoHis(criteria, 0, time);// get device information history
        return deviceInfoHisList;
    }*/
}
