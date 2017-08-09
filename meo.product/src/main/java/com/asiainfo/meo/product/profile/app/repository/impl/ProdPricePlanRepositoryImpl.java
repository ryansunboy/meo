package com.asiainfo.meo.product.profile.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.product.profile.app.model.entity.ProdPricePlan;
import com.asiainfo.meo.product.profile.app.repository.ProdPricePlanRepository;

public class ProdPricePlanRepositoryImpl implements ProdPricePlanRepository
{
    private static final String SEQ_PRODPRICEPLAN_ID = "SEQ_PRODPRICEPLAN_ID";
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    public ProdPricePlan getProdPricePlanById(Long pricePlanId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(ProdPricePlan.class).add(Restrictions.eq("pricePlanId", pricePlanId));
        return (ProdPricePlan)hibernateRepository.findUniqueObjectByCriteria(criteria);
    }
   
    public ProdPricePlan getVaildProdPricePlanById(Long pricePlanId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(ProdPricePlan.class).add(Restrictions.eq("pricePlanId", pricePlanId))
                .add(Restrictions.eq("sts", ProdPricePlan.STS_VALID));
        return (ProdPricePlan)hibernateRepository.findUniqueObjectByCriteria(criteria);
    }
    
    public ProdPricePlan getProdPricePlanByPriceId(Long priceId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(ProdPricePlan.class).add(Restrictions.eq("priceId", priceId));
        List<ProdPricePlan> list = (List<ProdPricePlan>) hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(list) ? null : list.get(0);
    }
    
    public ProdPricePlan addProdPricePlan(ProdPricePlan prodPricePlan)
    {
        Long id = ((Sequence) ServiceLocatorFactory.getService(Sequence.class)).next(SEQ_PRODPRICEPLAN_ID);
        prodPricePlan.setPricePlanId(id);
        hibernateRepository.saveObject(prodPricePlan);
        return getProdPricePlanById(id);
    }
    
    public ProdPricePlan updateProdPricePlan(ProdPricePlan prodPricePlan)
    {
        hibernateRepository.updateObject(prodPricePlan);
        return getProdPricePlanById(prodPricePlan.getPricePlanId());
    }

    public List<ProdPricePlan> getProdPricePlanByProdId(Long productId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(ProdPricePlan.class).add(Restrictions.eq("productId", productId));
        return (List<ProdPricePlan>)hibernateRepository.findByCriteria(criteria);
    }
    
    
    public List<ProdPricePlan> getProdPricePlanListByProductId(Long productId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(ProdPricePlan.class).add(Restrictions.eq("productId", productId))
                .add(Restrictions.eq("sts", ProdPricePlan.STS_VALID));
        return (List<ProdPricePlan>) hibernateRepository.findByCriteria(criteria);
    }
}
