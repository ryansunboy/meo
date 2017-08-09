package com.asiainfo.meo.prm.file.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.prm.file.app.model.entity.PartnerFileInfo;
import com.asiainfo.meo.prm.file.app.repository.PartnerFileInfoRepository;

public class PartnerFileInfoRepositoryImpl implements PartnerFileInfoRepository
{
    
    private final static String SEQ_PARTNER_FILE_ID = "SEQ_PARTNER_FILE_ID";
    
    @Resource
    HibernateRepository         hibernateRepository;
    
    /**
     * @Description: 保存partner文件信息
     * @Description: save partner file info
     * @author zhaozx
     * @param partnerFileInfo
     */
    public void savePartnerFileInfo(PartnerFileInfo partnerFileInfo)
    {
        partnerFileInfo.setFileId(getPartnerFileNextSequence());
        hibernateRepository.saveObject(partnerFileInfo);
    }
    
    /**
     * @Description: 修改partner文件信息
     * @Description: update partner file info
     * @author zhaozx
     * @param partnerFileInfo
     */
    public void updateParterFileInfo(PartnerFileInfo partnerFileInfo)
    {
        hibernateRepository.updateObject(partnerFileInfo);
    }
    
    /**
     * @Description: 获得partner特定文件类型的文件信息
     * @Description: get partner special file info
     * @author zhaozx
     * @param partnerId
     * @param fileType
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<PartnerFileInfo> getPartnerFileInfo(long partnerId, int fileType)
    {
        DetachedCriteria selectConditon = DetachedCriteria.forClass(PartnerFileInfo.class)
                .add(Restrictions.eq("partnerId", partnerId)).add(Restrictions.eq("fileType", fileType))
                .add(Restrictions.eq("sts", PartnerFileInfo.STS_VALID));
        return (List<PartnerFileInfo>) hibernateRepository.findByCriteria(selectConditon);
    }
    
    /**
     * @Description: 获得partner的文件信息
     * @Description: get partner file info
     * @author zhaozx
     * @param partnerId
     * @param fileType
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<PartnerFileInfo> getPartnerFileInfo(long partnerId)
    {
        DetachedCriteria selectConditon = DetachedCriteria.forClass(PartnerFileInfo.class)
                .add(Restrictions.eq("partnerId", partnerId)).add(Restrictions.eq("sts", PartnerFileInfo.STS_VALID));
        return (List<PartnerFileInfo>) hibernateRepository.findByCriteria(selectConditon);
    }
    
    /**
     * @Description: 获得下一个序列号
     * @Description: get next sequence
     * @author zhaozx
     * @return
     */
    @Override
    public long getPartnerFileNextSequence()
    {
        return ServiceLocatorFactory.getService(Sequence.class).next(SEQ_PARTNER_FILE_ID);
    }
}
