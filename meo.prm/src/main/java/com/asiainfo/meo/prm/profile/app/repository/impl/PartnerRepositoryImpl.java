package com.asiainfo.meo.prm.profile.app.repository.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.prm.profile.app.model.entity.Partner;
import com.asiainfo.meo.prm.profile.app.model.entity.StaffPartnerRel;
import com.asiainfo.meo.prm.profile.app.repository.PartnerRepository;

public class PartnerRepositoryImpl implements PartnerRepository
{
    
    private static final String SEQ_PARTNER_ID = "SEQ_PARTNER_ID";
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    /**
     * @Description: 保存partner
     * @Description: save partner
     * @author zhaozx
     * @param partner
     */
    @Override
    public void savePartner(Partner partner)
    {
        partner.setPartnerId(ServiceLocatorFactory.getService(Sequence.class).next(SEQ_PARTNER_ID));
        hibernateRepository.saveObject(partner);
    }
    
    /**
     * @Description: 修改partner
     * @Description: update partner
     * @author zhaozx
     * @param partner
     */
    @Override
    public void updatePartner(Partner partner)
    {
        hibernateRepository.updateObject(partner);
    }
    
    /**
     * @Description: 获得partner
     * @Description: get partner
     * @author zhaozx
     * @param partnerId partner id
     * @return
     */
    @Override
    public Partner getPartner(long partnerId)
    {
        return hibernateRepository.get(Partner.class, partnerId);
    }
    
    /**
     * @Description: 获得特定状态的partner
     * @Description: get partner
     * @author zhaozx
     * @param partnerId partner id
     * @param partnerSts partner sts
     * @return
     */
    @Override
    public Partner getPartner(long partnerId, int partnerSts)
    {
        // get partner
        DetachedCriteria criteria = DetachedCriteria.forClass(Partner.class).add(Restrictions.eq("partnerId", partnerId))
                .add(Restrictions.eq("sts", partnerSts));
        
        Partner partner = (Partner) hibernateRepository.findUniqueObjectByCriteria(criteria);
        return partner;
    }
    
    /**
     * @Description: 通过sql查询partner信息
     * @Description: select partner information by sql
     * @author zhaozx
     * @param sqlName
     * @param values
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> queryPartnerInfoByNameSql(String sqlName, Object... values)
    {
        return (List<Map<String, Object>>) hibernateRepository.findByNamedQuery(sqlName, values);
    }
    
    @Override
    public List<Partner> getPartnerList(Long partnerId, Integer pageSize, Integer pageNo)
    {
        int firstResults = (pageNo- 1)* pageSize;
        
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Partner.class);
        Conjunction andConnection = Restrictions.conjunction();
        if (ValidateUtil.isNotEmpty(partnerId))
        {
            andConnection.add(Restrictions.sqlRestriction("CAST({alias}.PARTNER_ID AS CHAR) like ?", "%"+ partnerId+ "%",
                    StandardBasicTypes.STRING));
        }
        detachedCriteria.add(andConnection).addOrder(Order.asc("partnerId"));
        
        return (List<Partner>) hibernateRepository.findByCriteria(detachedCriteria, firstResults, pageSize);
        
    }
    
}
