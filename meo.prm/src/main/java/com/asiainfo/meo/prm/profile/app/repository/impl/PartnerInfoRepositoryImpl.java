package com.asiainfo.meo.prm.profile.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.prm.profile.app.model.entity.PartnerInfo;
import com.asiainfo.meo.prm.profile.app.model.entity.StaffPartnerRel;
import com.asiainfo.meo.prm.profile.app.repository.PartnerInfoRepository;

public class PartnerInfoRepositoryImpl implements PartnerInfoRepository
{
    @Resource
    private HibernateRepository hibernateRepository;
    
    private static final String SEQ_PARTNER_INFO_ID = "SEQ_PARTNER_INFO_ID";
    
    /**
     * @Description: 保存partner信息
     * @Description: save partner information
     * @author zhaozx
     * @param partnerInfo
     */
    @Override
    public void savePartnerinfo(PartnerInfo partnerInfo)
    {
        partnerInfo.setId(ServiceLocatorFactory.getService(Sequence.class).next(SEQ_PARTNER_INFO_ID));
        hibernateRepository.saveObject(partnerInfo);
    }
    
    /**
     * @Description: 获得partner信息
     * @Description: get partner information
     * @author zhaozx
     * @param partnerId
     * @return PartnerInfo partnerInfo entity
     */
    
    @Override
    public PartnerInfo getPartnerInfo(long partnerId)
    {
        // select condition by partnerId
        DetachedCriteria criteria = DetachedCriteria.forClass(PartnerInfo.class).add(Restrictions.eq("partnerId", partnerId));
        PartnerInfo partnerInfoList = (PartnerInfo) hibernateRepository.findUniqueObjectByCriteria(criteria);
        return partnerInfoList;
    }
    
    /**
     * @Description: 修改partner information
     * @Description: update partner information
     * @author zhaozx
     * @param partnerInfo partnerInfo entity
     */
    @Override
    public void updatePartnerInfo(PartnerInfo partnerInfo)
    {
        hibernateRepository.updateObject(partnerInfo);
    }
    
    /**
     * @Description: 注销partner信息
     * @Description: delete partner information
     * @author zhaozx
     * @param partnerInfo partner信息实体 partner information entity
     */
    @Override
    public void deletePartnerInfo(PartnerInfo partnerInfo)
    {
        hibernateRepository.deleteObject(partnerInfo);
    }
    
    /**
     * @Description: 获得partner
     * @Description: get partner info list
     * @author zhaozx
     * @param detachedCriteria
     * @return List<PartnerInfo>
     */
    @Override
    public List<PartnerInfo> getPartnerInfo(DetachedCriteria criteria)
    {
        return (List<PartnerInfo>) hibernateRepository.findByCriteria(criteria);
    }
    
    @Override
    public List<PartnerInfo> getpartnerInfo(List<Long> partnerIds, String companyName)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(PartnerInfo.class);
        criteria.add(Restrictions.in("partnerId", partnerIds));
        if (ValidateUtil.isNotEmpty(companyName))
        {
            criteria.add(Restrictions.like("companyName", "%"+companyName+"%"));
        }
        criteria.addOrder(Property.forName("partnerId").desc());
        return (List<PartnerInfo>) hibernateRepository.findByCriteria(criteria);
    }
    
    /**
     * @Description: 获得partner信息
     * @Description: get partner info
     * @author zhaozx
     * @param selectCondition
     * @param pageSize
     * @param pageNo
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<PartnerInfo> getPartnerInfo(DetachedCriteria selectCondition, int pageSize, int pageNo)
    {
        return (List<PartnerInfo>) hibernateRepository.findByCriteria(selectCondition, (pageNo- 1)* pageSize, pageSize);
    }
    
    @Override
    public List<PartnerInfo> getAllPartnerInfo(Long partnerId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(PartnerInfo.class);
        if (ValidateUtil.isNotNull(partnerId))
        {
            criteria.add(Restrictions.eq("partnerId", partnerId));
        }
        List<PartnerInfo> list = (List<PartnerInfo>) hibernateRepository.findByCriteria(criteria);
        return list;
    }
    
    private DetachedCriteria createPartnerInfoDetachedCriteria(Long partnerId, String companyName)
    {
        DetachedCriteria selectCondition = DetachedCriteria.forClass(PartnerInfo.class);
        Disjunction orConnection = Restrictions.disjunction();// list condition use or connection
        Conjunction andConnection = Restrictions.conjunction();// parameter use and connection
        if (ValidateUtil.isNotNull(partnerId))
        {
            andConnection.add(Restrictions.sqlRestriction("CAST({alias}.PARTNER_ID AS CHAR) like ?", "%"+ partnerId+ "%",
                    StandardBasicTypes.STRING));
            // andConnection.add(Restrictions.like("partnerId", condition.getPartnerId().toString(),MatchMode.ANYWHERE));
        }
        
        if (ValidateUtil.isNotEmpty(companyName))
        {
            andConnection.add(Restrictions.like("companyName", "%"+companyName+"%"));
        }
        orConnection.add(andConnection);
        selectCondition.add(orConnection);// add select conditionss
        selectCondition.addOrder(Order.asc("partnerId"));// add order condition
        return selectCondition;
    }
    
    public List<PartnerInfo> getPartnerInfo(Long partnerId, String companyName, Integer pageSize, Integer pageNo)
    {
        DetachedCriteria selectCondition = createPartnerInfoDetachedCriteria(partnerId, companyName);
        return (List<PartnerInfo>)hibernateRepository.findByCriteria(selectCondition, (pageNo- 1)* pageSize, pageSize);
    }
    
    public int getPartnerInfoTotalSize(Long partnerId, String companyName)
    {
        DetachedCriteria selectCondition = createPartnerInfoDetachedCriteria(partnerId, companyName);
        List<PartnerInfo> list = (List<PartnerInfo>)hibernateRepository.findByCriteria(selectCondition);
        return ValidateUtil.isEmpty(list)?0:list.size();
    }
}
