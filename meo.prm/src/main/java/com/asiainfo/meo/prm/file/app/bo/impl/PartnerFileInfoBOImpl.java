package com.asiainfo.meo.prm.file.app.bo.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asiainfo.meo.common.core.exception.CommonErrorConstant;
import com.asiainfo.meo.common.core.exception.MeoException;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.prm.define.PrmConstantDefine;
import com.asiainfo.meo.prm.file.app.bo.PartnerFileInfoBO;
import com.asiainfo.meo.prm.file.app.model.entity.PartnerFileInfo;
import com.asiainfo.meo.prm.file.app.repository.PartnerFileInfoRepository;

public class PartnerFileInfoBOImpl implements PartnerFileInfoBO
{
    private static final Log LOG = LogFactory.getLog(PartnerFileInfoBOImpl.class);

    @Resource
    private PartnerFileInfoRepository partnerFileInfoRepository;
    
    /**
     * @Description: 淇濆瓨partner鏂囦欢淇℃伅
     * @Description: save partner file info
     * @author zhaozx
     * @param partnerFileInfo
     */
    public void savePartnerFileInfo(PartnerFileInfo partnerFileInfo)
    {
        initPartnerFileInfoParameter(partnerFileInfo);
        partnerFileInfoRepository.savePartnerFileInfo(partnerFileInfo);
    }
    
    /**
     * @Description: 淇敼partner鏂囦欢淇℃伅
     * @Description: update partner file info
     * @author zhaozx
     * @param partnerFileInfo
     */
    public void updateParterFileInfo(PartnerFileInfo partnerFileInfo)
    {
        LOG.debug("update partner file info begin!");
        
        Timestamp modifyDate = DateTimeUtil.getNow();
        
        Long parnterId = partnerFileInfo.getPartnerId();
        
        Integer fileType = partnerFileInfo.getFileType();
        
        if (ValidateUtil.isNull(parnterId))
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,new Object[]{PrmConstantDefine.PARTNER_ID});
        
        if (ValidateUtil.isNull(fileType))
            throw new MeoException(CommonErrorConstant.PARAMETER_IS_NULL_OR_EMPTY,new Object[]{PrmConstantDefine.PARTNER_FILE_TYPE});
        
        List<PartnerFileInfo> partnerFileInfoList = getPartnerFileInfo(parnterId, fileType);
        
        if(ValidateUtil.isNotEmpty(partnerFileInfoList))
            for (PartnerFileInfo oldPartnerFileInfo : partnerFileInfoList)
            {
                oldPartnerFileInfo.setModifyDate(modifyDate);
                oldPartnerFileInfo.setSts(PartnerFileInfo.STS_INVALID);
                partnerFileInfoRepository.updateParterFileInfo(oldPartnerFileInfo);
            }
        
        initPartnerFileInfoParameter(partnerFileInfo);
        partnerFileInfoRepository.savePartnerFileInfo(partnerFileInfo);
        LOG.debug("update partner file info end!");
    }
    
    /**
     * @Description: 鑾峰緱partner鐗瑰畾鏂囦欢绫诲瀷鐨勬枃浠朵俊鎭�
     * @Description: get partner special file info
     * @author zhaozx
     * @param partnerId
     * @param fileType
     * @return
     */
    public List<PartnerFileInfo> getPartnerFileInfo(long partnerId, int fileType)
    {
        return partnerFileInfoRepository.getPartnerFileInfo(partnerId, fileType);
    }
    
    /**
     * @Description: 鑾峰緱partner鐨勬枃浠朵俊鎭�
     * @Description: get partner file info
     * @author zhaozx
     * @param partnerId
     * @param fileType
     * @return
     */
    public List<PartnerFileInfo> getPartnerFileInfo(long partnerId)
    {
        return partnerFileInfoRepository.getPartnerFileInfo(partnerId);
    }
    
    /**
     * @Description: 鑾峰緱partner鏂囦欢涓嬩竴涓簭鍒�
     * @Description: get partner file next sequence
     * @author zhaozx
     * @return
     */
    private long getPartnerFileNextSequence()
    {
        return partnerFileInfoRepository.getPartnerFileNextSequence();
    }
    
    /**
     * @Description: 鍒濆鍖杙artner鏂囦欢淇℃伅鍙傛暟
     * @Description: init partner info parameter
     * @author zhaozx
     * @param partnerFileInfo
     */
    private void initPartnerFileInfoParameter(PartnerFileInfo partnerFileInfo)
    {
        if(ValidateUtil.isEmpty(partnerFileInfo))
            return;
        Timestamp createDate = DateTimeUtil.getNow();
        File file = new File(partnerFileInfo.getFileUrl());
        
        partnerFileInfo.setCreateDate(createDate);
        partnerFileInfo.setModifyDate(createDate);
        partnerFileInfo.setSts(PartnerFileInfo.STS_VALID);
        partnerFileInfo.setFileName(file.getName());
        
    }
}
