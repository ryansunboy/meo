package com.asiainfo.meo.customer.wishlist.app.repository.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.customer.wishlist.app.model.entity.WishList;
import com.asiainfo.meo.customer.wishlist.app.repository.WishListRepository;

public class WishListRepositoryImpl implements WishListRepository
{
    @Resource
    private HibernateRepository hibernateRepository;
    
    private final static String SEQ_WISHLIST_ID = "SEQ_WISHLIST_ID";
    
    public void saveWishList(WishList wishList)
    {
        Sequence sequence = ServiceLocatorFactory.getService(Sequence.class);
        wishList.setId(sequence.next(SEQ_WISHLIST_ID));
        wishList.setSts(WishList.WISHLIST_STATUS_VALID);
        wishList.setCreateDate(DateTimeUtil.getNow());
        hibernateRepository.saveObject(wishList);
        
    }
    
    public void deleteWishList(Collection<WishList> entities)
    {
        hibernateRepository.deleteAll(entities);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<WishList> getWishList(DetachedCriteria conditon)
    {
        return (List<WishList>) hibernateRepository.findByCriteria(conditon);
    }
}
