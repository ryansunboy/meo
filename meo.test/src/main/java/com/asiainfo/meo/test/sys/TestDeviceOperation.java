package com.asiainfo.meo.test.sys;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.sys.device.app.bo.DeviceInfoBO;
import com.asiainfo.meo.sys.device.app.model.entity.DeviceInfo;
import com.asiainfo.meo.sys.device.app.model.entity.DeviceInfoHis;

public class TestDeviceOperation
{
    /**
     * @Description: 创建设备信息
     * @Description: create device information
     * @modifyReason:
     * @author zhaozx
     */
    Logger logger = Logger.getLogger(this.getClass());
    
    @Test
    public void testCreateDevice() throws IOException
    {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceToken("990099");
        deviceInfo.setDeviceType(1);
        deviceInfo.setOsVersion(DeviceInfo.OS_VERSION_ANDROID);
        deviceInfo.setUserId(1000L);
        deviceInfo.setImei("100000000");
        logger.info("in:"+ JsonUtil.writeObjectAsString(deviceInfo));
        DeviceInfoBO deviceBO = ServiceLocatorFactory.getService(DeviceInfoBO.class);
        deviceBO.saveDeviceInfo(deviceInfo);
        logger.info("out:"+ JsonUtil.writeObjectAsString(deviceInfo));
    }
    
    /**
     * @Description: 注销设备
     * @Description: deactivate device
     * @modifyReason:
     * @author zhaozx
     */
    @Test
    public void testDeactiveDevice()
    {
        Timestamp modifyDate = DateTimeUtil.getNow();
        long userId = 1000;
        logger.info("in:"+ "userId="+ userId+ "\t"+ "modifyDate="+ modifyDate);
        
        DeviceInfoBO deviceBO = ServiceLocatorFactory.getService(DeviceInfoBO.class);
        deviceBO.deleteDeviceInfo(userId, modifyDate);
    }
    
    /**
     * @Description: 最后登录的信息
     * @Description: (English description)
     * @modifyReason:
     * @author zhaozx
     * @throws IOException 
     */
    @Test
    public void testLastLoginTimes() throws IOException
    {
        DeviceInfoBO deviceBO = ServiceLocatorFactory.getService(DeviceInfoBO.class);
        long userId = 1000;
        int times = 1;
        logger.info("in:"+ "userId="+ userId+ "\t"+ "times="+ times);
        
        List<DeviceInfoHis> deviceInfoHisList = deviceBO.getLastLoginDeviceInfo(userId, times);
        logger.info("out:"+JsonUtil.writeObjectAsString(deviceInfoHisList));
    }
}
