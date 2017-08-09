package com.asiainfo.meo.prm.profile.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.prm.profile.app.model.entity.StaffPartnerRel;
import com.asiainfo.meo.prm.profile.app.repository.StaffPartnerRelRepository;

public class StaffPartnerRelRepositoryImpl implements StaffPartnerRelRepository
{
    private static final String SEQ_STAFF_PARTNER_REL_ID = "SEQ_STAFF_PARTNER_REL_ID";
    
    @Resource
    HibernateRepository         hibernateRepository;
    
    /**
     * @Description: 闁兼儳鍢茬欢鐪昦rtner闁告稒锚娴兼劙宕楀畷鍥厙闁告帗顨夐妴锟�     * @Description: get partner and staff relation list
     * @author zhaozx
     * @param saleId
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<StaffPartnerRel> getPartnerIdList(long saleId)
    {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StaffPartnerRel.class).add(
                Restrictions.eq("staffId", saleId));
        return (List<StaffPartnerRel>) hibernateRepository.findByCriteria(detachedCriteria);
    }
    
    /**
     * @Description: 闁告帗绋戠紓鎾诲川濡桨绱ｉ柛婊冾唹artner闁汇劌瀚崣褏鍖栭敓锟�    * @Description: create partner and staff relation
     * @author zhaozx
     * @param staffPartnerRel
     */
    @Override
    public void saveStaffPartnerRel(StaffPartnerRel staffPartnerRel)
    {
        staffPartnerRel.setId(ServiceLocatorFactory.getService(Sequence.class).next(SEQ_STAFF_PARTNER_REL_ID));
        hibernateRepository.saveObject(staffPartnerRel);
    }
    
    /**
     * @Description: 闁兼儳鍢茬欢鐪昦rtner闁告稒锚娴兼劙宕楀畷鍥厙闁告帗顨夐妴锟�     * @Description: get partner and staff relation list
     * @author zhaozx
     * @param saleId
     * @param partnerId
     * @param pageSize
     * @param pageNo
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<StaffPartnerRel> getPartnerIdList(long saleId, Long partnerId, int pageSize, int pageNo)
    {
        DetachedCriteria criteria = createStaffPartnerDetachedCriteria(saleId, partnerId);
        return (List<StaffPartnerRel>) hibernateRepository.findByCriteria(criteria, (pageNo- 1)* pageSize, pageSize);
    }

    private DetachedCriteria createStaffPartnerDetachedCriteria(long saleId, Long partnerId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(StaffPartnerRel.class);
        criteria.add(Restrictions.eq("staffId", saleId));
        if (ValidateUtil.isNotNull(partnerId))
        {
            criteria.add(Restrictions.sqlRestriction("CAST({alias}.PARTNER_ID AS CHAR) like ?", "%"+ partnerId+ "%",
                    StandardBasicTypes.STRING));
        }
        criteria.addOrder(Property.forName("id").desc());
        return criteria;
    }

    @Override
    public int getPartnerIdListTotalSize(long saleId, Long partnerId)
    {
        DetachedCriteria criteria = createStaffPartnerDetachedCriteria(saleId, partnerId);
        List<StaffPartnerRel> list = (List<StaffPartnerRel>) hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(list)?0:list.size();
    }
    
    @Override
    public StaffPartnerRel getStaffPartnerRel(Long partnerId)
    {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StaffPartnerRel.class).add(
                Restrictions.eq("partnerId", partnerId));
        return (StaffPartnerRel)hibernateRepository.findUniqueObjectByCriteria(detachedCriteria);
    }
    
}
