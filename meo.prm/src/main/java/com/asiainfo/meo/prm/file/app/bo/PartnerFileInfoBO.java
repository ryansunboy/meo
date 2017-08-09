package com.asiainfo.meo.prm.file.app.bo;

import java.util.List;

import com.asiainfo.meo.prm.file.app.model.entity.PartnerFileInfo;

public interface PartnerFileInfoBO
{
    /**
     * @Description: 保存partner文件信息
     * @Description: save partner file info
     * @author zhaozx
     * @param partnerFileInfo
     */
    void savePartnerFileInfo(PartnerFileInfo partnerFileInfo);
    
    /**
     * @Description: 修改partner文件信息
     * @Description: update partner file info
     * @author zhaozx
     * @param partnerFileInfo
     */
    void updateParterFileInfo(PartnerFileInfo partnerFileInfo);
    
    /**
     * @Description: 获得partner特定文件类型的文件信息
     * @Description: get partner special file info
     * @author zhaozx
     * @param partnerId
     * @param fileType
     * @return
     */
    List<PartnerFileInfo> getPartnerFileInfo(long partnerId, int fileType);
    
    /**
     * @Description: 获得partner的文件信息
     * @Description: get partner file info
     * @author zhaozx
     * @param partnerId
     * @param fileType
     * @return
     */
    List<PartnerFileInfo> getPartnerFileInfo(long partnerId);
}
