package com.asiainfo.meo.product.profile.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.product.profile.app.model.entity.ProdMnoMapping;
import com.asiainfo.meo.product.profile.app.repository.ProdMnoMappingRepository;

public class ProdMnoMappingRepositoryImpl implements ProdMnoMappingRepository
{
    private static final String SEQ_PRODMNOMAPPING_ID = "SEQ_PRODMNOMAPPING_ID";
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    public ProdMnoMapping getProdMnoMappingById(Long id)
    {
        return hibernateRepository.get(ProdMnoMapping.class, id);
    }
    
    public List<ProdMnoMapping> getProdMnoMappingListByProductId(Long productId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(ProdMnoMapping.class).add(Restrictions.eq("productId", productId))
                .add(Restrictions.eq("sts", ProdMnoMapping.STS_VALID));
        return (List<ProdMnoMapping>) hibernateRepository.findByCriteria(criteria);
    }
    
    public ProdMnoMapping addProdMnoMapping(ProdMnoMapping prodMnoMapping)
    {
        Long id = ((Sequence) ServiceLocatorFactory.getService(Sequence.class)).next(SEQ_PRODMNOMAPPING_ID);
        prodMnoMapping.setId(id);
        hibernateRepository.saveObject(prodMnoMapping);
        return getProdMnoMappingById(id);
    }
    
    public ProdMnoMapping updateProdMnoMapping(ProdMnoMapping prodMnoMapping)
    {
        hibernateRepository.updateObject(prodMnoMapping);
        return getProdMnoMappingById(prodMnoMapping.getId());
    }
    
    public void deleteProdMnoMappingById(Long id)
    {
        ProdMnoMapping pmm = getProdMnoMappingById(id);
        hibernateRepository.deleteObject(pmm);
    }

    @Override
    public ProdMnoMapping getProdMnoMappingByProdIdAndMnoId(Long productId, Integer mnoId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(ProdMnoMapping.class)
                .add(Restrictions.eq("productId", productId))
                .add(Restrictions.eq("sts", ProdMnoMapping.STS_VALID))
                .add(Restrictions.eq("mnoId", mnoId));
                
        return hibernateRepository.findUniqueObjectByCriteria(criteria);
    }

    @Override
    public ProdMnoMapping getProdMnoMappingByMnodIdAndProductIdAndmnoProdId(Integer mnoId, Long productId, Long mnoProdId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(ProdMnoMapping.class)
                .add(Restrictions.eq("productId", productId))
                .add(Restrictions.eq("sts", ProdMnoMapping.STS_VALID))
                .add(Restrictions.eq("mnoId", mnoId))
                .add(Restrictions.eq("mnoProdId", mnoProdId));
        List<ProdMnoMapping> list =(List<ProdMnoMapping>)hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(list)?null:list.get(0);
    }

    @Override
    public List<ProdMnoMapping> getProdMnoMappingByMnoId(Integer mnoId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(ProdMnoMapping.class);
        criteria.add(Restrictions.eq("mnoId", mnoId)).add(Restrictions.eq("sts", ProdMnoMapping.STS_VALID));
        return (List<ProdMnoMapping>)hibernateRepository.findByCriteria(criteria);
    }
    
}
