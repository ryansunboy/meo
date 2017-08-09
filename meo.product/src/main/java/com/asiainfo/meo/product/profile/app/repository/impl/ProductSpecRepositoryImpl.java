package com.asiainfo.meo.product.profile.app.repository.impl;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.product.profile.app.model.entity.Product;
import com.asiainfo.meo.product.profile.app.model.entity.ProductSpec;
import com.asiainfo.meo.product.profile.app.repository.ProductSpecRepository;

public class ProductSpecRepositoryImpl implements ProductSpecRepository
{
    private static final String SEQ_PRODUCTSPEC_ID = "SEQ_PRODUCTSPEC_ID";

    @Resource
    private HibernateRepository hibernateRepository;
    
    public ProductSpec getProductSpecById(Long id)
    {
        return hibernateRepository.get(ProductSpec.class,id);
    }

    public ProductSpec addProductSpec(ProductSpec productSpec)
    {
        productSpec.setProSpecId(((Sequence)ServiceLocatorFactory.getService(Sequence.class)).next(SEQ_PRODUCTSPEC_ID));
        hibernateRepository.saveObject(productSpec);
        return getProductSpecById(productSpec.getProSpecId());
    }

    public ProductSpec updateProductSpec(ProductSpec productSpec)
    {
        hibernateRepository.updateObject(productSpec);
        return getProductSpecById(productSpec.getProSpecId());
    }

    public ProductSpec getProductSpecificationInfo(Long productId)
    {
        DetachedCriteria criteria=DetachedCriteria.forClass(ProductSpec.class);
        criteria.add(Restrictions.eq("productId", productId));
        return (ProductSpec)hibernateRepository.findUniqueObjectByCriteria(criteria);
    }
}
