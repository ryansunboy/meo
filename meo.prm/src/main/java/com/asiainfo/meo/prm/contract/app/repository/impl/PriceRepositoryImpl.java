package com.asiainfo.meo.prm.contract.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.prm.contract.app.model.entity.Price;
import com.asiainfo.meo.prm.contract.app.repository.PriceRepository;

public class PriceRepositoryImpl implements PriceRepository
{
    private static final String SEQ_PRICE_ID = "SEQ_PRICE_ID";
    
    @Resource
    HibernateRepository         hibernateRepository;
    
    /**
     * @Description: 保存合同价格
     * @Description: save price
     * @author zhaozx
     * @param Price
     */
    @Override
    public Price savePrice(Price price)
    {
        price.setPriceId(ServiceLocatorFactory.getService(Sequence.class).next(SEQ_PRICE_ID));
        hibernateRepository.saveObject(price);
        return getPrice(price.getPriceId());
    }
    
    /**
     * @Description: 修改合同价格
     * @Description: update contract price
     * @author zhaozx
     * @param Price
     */
    @Override
    public Price updatePrice(Price price)
    {
        hibernateRepository.updateObject(price);
        return getPrice(price.getPriceId()); 
    }
    
    /**
     * @Description:通过price id获得合同价格
     * @Description: get contract price entity by price id
     * @param priceId
     * @return Price
     * @author zhaozx
     */
    @Override
    public Price getPrice(long priceId)
    {
        return hibernateRepository.get(Price.class, priceId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Price> getPrice(DetachedCriteria criteria)
    {
        return (List<Price>)hibernateRepository.findByCriteria(criteria);
    }

    @Override
    public void deletePrice(Long id)
    {
        Price price = getPrice(id);
        hibernateRepository.deleteObject(price);
    }

    @Override
    public List<Price> getPricesByIds(Long[] ids)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Price.class);
        criteria.add(Restrictions.in("priceId", ids));
        return (List<Price>)hibernateRepository.findByCriteria(criteria);
    }
}
