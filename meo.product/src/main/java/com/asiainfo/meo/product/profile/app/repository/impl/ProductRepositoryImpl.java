package com.asiainfo.meo.product.profile.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.SequenceUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.product.profile.app.model.entity.DiscountRule;
import com.asiainfo.meo.product.profile.app.model.entity.PdPrice;
import com.asiainfo.meo.product.profile.app.model.entity.Product;
import com.asiainfo.meo.product.profile.app.model.entity.ProductSpec;
import com.asiainfo.meo.product.profile.app.model.vo.ProductBasicInfoQueryConditionVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductBasicInfoVO;
import com.asiainfo.meo.product.profile.app.repository.ProductRepository;

public class ProductRepositoryImpl implements ProductRepository
{
    private static final String SEQ_PRODUCT_ID = "SEQ_PRODUCT_ID";
    
    @Resource
    private HibernateRepository hibernateRepository;
   
    public List<Product> getPdProduct(ProductBasicInfoVO pdProductVO, Integer pageNo, Integer pageSize)
    {

        DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
        if (ValidateUtil.isNotEmpty(pdProductVO.getProductCategoryId()))
        {
            criteria.add(Restrictions.eq("categoryId", pdProductVO.getProductCategoryId()));
        }
        if (ValidateUtil.isNotEmpty(pdProductVO.getProductName()))
        {
            criteria.add(Restrictions.like("productName", "%" + pdProductVO.getProductName() + "%"));
        }
        if (ValidateUtil.isNotEmpty(pdProductVO.getProductValidDate()))
        {
            criteria.add(Restrictions.ge("validDate", pdProductVO.getProductValidDate()));
        }
        if (ValidateUtil.isNotEmpty(pdProductVO.getProductExpiredDate()))
        {
            criteria.add(Restrictions.le("expiredDate", pdProductVO.getProductExpiredDate()));
        }
        if (ValidateUtil.isNotEmpty(pdProductVO.getProductId()))
        {
            criteria.add(Restrictions.eq("productId", pdProductVO.getProductId()));
        }  
        criteria.addOrder(Property.forName("productId").desc());
        return (List<Product>) hibernateRepository.findByCriteria(criteria, pageSize* (pageNo- 1), pageSize);
    }

    public List<ProductSpec> getPdProductSpec(ProductBasicInfoVO pdProductSpecVO)
    {
        DetachedCriteria criteria=DetachedCriteria.forClass(ProductSpec.class);
        if (ValidateUtil.isNotEmpty(pdProductSpecVO.getActivationValidity()))
        {
            criteria.add(Restrictions.eq("activationPeriod", pdProductSpecVO.getActivationValidity()));
        }
        if (ValidateUtil.isNotEmpty(pdProductSpecVO.getProductValidity()))
        {
            criteria.add(Restrictions.eq("validationPeriod", pdProductSpecVO.getProductValidity()));
        }  
        return (List<ProductSpec>) hibernateRepository.findByCriteria(criteria);
    }
    
    private DetachedCriteria getProductListDetachedCriteria(final ProductBasicInfoQueryConditionVO conditionVO)
    {
        final DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
        criteria.add(Restrictions.eq("sts", Product.STS_VALID));
        if (ValidateUtil.isNotNull(conditionVO))
        {
        }
        return criteria;
    }

    public List<Product> getProductList(final ProductBasicInfoQueryConditionVO conditionVO,
            final Integer pageSize, final Integer pageNo)
    {
        DetachedCriteria criteria = getProductListDetachedCriteria(conditionVO);
        return (List<Product>) hibernateRepository.findByCriteria(criteria, pageSize * (pageNo- 1), pageSize);
    }
    
    @Override
    public Integer getProductListTotalSize(ProductBasicInfoQueryConditionVO conditionVO)
    {
        Integer totalSize = null;
        DetachedCriteria criteria = getProductListDetachedCriteria(conditionVO);
        final List<Product> list = (List<Product>) hibernateRepository.findByCriteria(criteria);
        if (ValidateUtil.isNotEmpty(list))
        {
            totalSize = list.size();
        }
        return totalSize;
    }

    public Product getProductById(Long id)
    {
        return hibernateRepository.get(Product.class, id);
    }

    public Product addProduct(Product product)
    { 
        product.setProductId(((Sequence)ServiceLocatorFactory.getService(Sequence.class)).next(SEQ_PRODUCT_ID));
        hibernateRepository.saveObject(product);
        return getProductById(product.getProductId());
    }

    public Product updateProduct(Product product)
    {
        hibernateRepository.updateObject(product);
        return getProductById(product.getProductId());
    }

    public List<Product> getProduct(Long productId)
    {
        DetachedCriteria condition = DetachedCriteria.forClass(Product.class).add(Restrictions.eq("productId", productId));
        return (List<Product>) hibernateRepository.findByCriteria(condition);
    }

    @Override
    public List<Product> getAppProductList(List<Long> productIds, Integer pageNo, Integer pageSize)
    {
        DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
        criteria.add(Restrictions.in("productId", productIds)).add(Restrictions.eq("prodSts", Product.STS_ACTIVE));
        return (List<Product>) hibernateRepository.findByCriteria(criteria, pageSize* (pageNo- 1), pageSize);
    }

    @Override
    public void createPriceDef(PdPrice price)
    {
        price.setCreateDate(DateTimeUtil.getNow());
        price.setSts(PdPrice.STS_VALID);
        price.setPriceId(SequenceUtil.getSequence(PdPrice.SEQ_PD_PRICE_DEF));
        hibernateRepository.saveObject(price);
    }

    @Override
    public void modifyPriceDef(PdPrice price)
    {
        price.setModifyDate(DateTimeUtil.getNow());
        hibernateRepository.updateObject(price);
    }

    @Override
    public PdPrice getPriceDefByPriceId(Long priceId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(PdPrice.class)
                .add(Restrictions.eq("priceId", priceId))
                .add(Restrictions.eq("sts", PdPrice.STS_VALID));
        return hibernateRepository.findUniqueObjectByCriteria(criteria);
    }

    @Override
    public List<PdPrice> getPriceDefList(Integer pageNo, Integer pageSize, Long priceId, String priceName)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(PdPrice.class)
                .add(Restrictions.eq("sts", PdPrice.STS_VALID));
        if (ValidateUtil.isNotEmpty(priceId))
        {
            criteria.add(Restrictions.sqlRestriction("CAST({alias}.PRICE_ID AS CHAR) like ?", "%"+ priceId+ "%",
                    StandardBasicTypes.STRING));
        }
        if (ValidateUtil.isNotEmpty(priceName))
        {
            criteria.add(Restrictions.like("priceName", "%"+priceName+"%"));
        }
        return (List<PdPrice>)hibernateRepository.findByCriteria(criteria, (pageNo - 1) * pageSize, pageSize);
    }

    @Override
    public Integer getPriceDefTotalSize()
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(PdPrice.class)
                .add(Restrictions.eq("sts", PdPrice.STS_VALID));
        List<PdPrice> list =  (List<PdPrice>)hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(list) ? 0 : list.size();
    }

    @Override
    public void createDiscountRuleDef(DiscountRule discountRule)
    {
        discountRule.setCreateDate(DateTimeUtil.getNow());
        discountRule.setSts(PdPrice.STS_VALID);
        discountRule.setDiscountId(SequenceUtil.getSequence(DiscountRule.SEQ_PD_DISCOUNT_RULE_DEF));
        hibernateRepository.saveObject(discountRule);   
    }

    @Override
    public void modifyDiscountRuleDef(DiscountRule discountRule)
    {
        discountRule.setModifyDate(DateTimeUtil.getNow());
        hibernateRepository.updateObject(discountRule);
    }

    @Override
    public DiscountRule getDiscountRuleDefByDiscountId(Long discountId)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(DiscountRule.class)
                .add(Restrictions.eq("discountId", discountId))
                .add(Restrictions.eq("sts", PdPrice.STS_VALID));
        return hibernateRepository.findUniqueObjectByCriteria(criteria);
    }

    @Override
    public List<DiscountRule> getDiscountRuleDefList(Integer pageNo, Integer pageSize, Long discountId, String discountName)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(DiscountRule.class)
                .add(Restrictions.eq("sts", PdPrice.STS_VALID));
        if (ValidateUtil.isNotEmpty(discountId))
        {
            criteria.add(Restrictions.sqlRestriction("CAST({alias}.DISCOUNT_ID AS CHAR) like ?", "%"+ discountId+ "%",
                    StandardBasicTypes.STRING));
        }
        if (ValidateUtil.isNotEmpty(discountName))
        {
            criteria.add(Restrictions.like("discountName", "%"+discountName+"%"));
        }
        return (List<DiscountRule>)hibernateRepository.findByCriteria(criteria, (pageNo - 1) * pageSize, pageSize);
    }

    @Override
    public Integer getDiscountRuleDefTotalSize()
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(DiscountRule.class)
                .add(Restrictions.eq("sts", PdPrice.STS_VALID));
        List<DiscountRule> list =  (List<DiscountRule>)hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isEmpty(list) ? 0 : list.size();
    }

}
