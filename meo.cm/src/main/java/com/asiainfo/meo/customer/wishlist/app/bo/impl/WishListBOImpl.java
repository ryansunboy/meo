package com.asiainfo.meo.customer.wishlist.app.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.customer.service.require.CustomerRserviceBO;
import com.asiainfo.meo.customer.wishlist.app.bo.WishListBO;
import com.asiainfo.meo.customer.wishlist.app.model.entity.WishList;
import com.asiainfo.meo.customer.wishlist.app.model.vo.WishListVO;
import com.asiainfo.meo.customer.wishlist.app.repository.WishListRepository;
import com.asiainfo.meo.product.profile.app.model.entity.Product;

public class WishListBOImpl implements WishListBO
{
    @Resource
    private WishListRepository wishListRepository;
    
    @Resource(name = "customerRserviceBO")
    CustomerRserviceBO        custRserviceBO;
    
    public void saveWishList(WishList wishList)
    {
        wishListRepository.saveWishList(wishList);
    }
    
    public void deleteWishList(long custId, Long wishListId)
    {
        /*
        Map<String, Object> map = new HashMap<String, Object>();
        if(ValidateUtil.isNotNull(wishListId) && !"".equals(wishListId)){
            map.put("custId", custId);
            map.put("id", wishListId);
        }else{
            map.put("custId", custId);
        }
        */
        DetachedCriteria condition =DetachedCriteria.forClass(WishList.class).add(Restrictions.eq("custId", custId));
        if(ValidateUtil.isNotNull(wishListId))
        {
            condition.add(Restrictions.eq("id", wishListId));
        }
        List<WishList> wishListlist = getWishList(condition);
        
        wishListRepository.deleteWishList(wishListlist);
    }
    
       
    
    public List<WishListVO> getWishListVO(long custId)
    {
        /*
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("custId", custId);
        */
        
        DetachedCriteria condition=DetachedCriteria.forClass(WishList.class).add(Restrictions.eq("custId", custId));
        
        List<WishListVO> wishListVOlist = new ArrayList<WishListVO>();
   
        List<WishList> wishListlist =getWishList(condition);
        
        if(ValidateUtil.isEmpty(wishListlist))
        {
            return null;
        }
        
        for (WishList wishList : wishListlist)
        {
            Long productId=Long.valueOf(wishList.getProductId());
            
            List<Product> productList = getProduct(productId);
            
            // for(Product product : productList){
            Product product = productList.get(0);
            WishListVO wishListVO = new WishListVO();
            wishListVO.setProdId((int)product.getProductId());
            wishListVO.setProdName(product.getProductName());
            wishListVO.setProdSts(product.getProdSts());
            wishListVO.setCategoryId(product.getCategoryId().intValue());
//            wishListVO.setImagUrl(product.getImagUrl());
            wishListVO.setValidDate(product.getValidDate().getTime());
            wishListVO.setExpiredDate(product.getExpiredDate().getTime());
            wishListVOlist.add(wishListVO);
            
        }
        
        return wishListVOlist;
        // return wishListRepository.getWishListVO(custId);
    }
    
    private List<WishList> getWishList(DetachedCriteria condition)
    {
      return wishListRepository.getWishList(condition);
    }
    
    private  List<Product> getProduct(Long productId)
    {
        return custRserviceBO.getProduct(productId);
    }
}
