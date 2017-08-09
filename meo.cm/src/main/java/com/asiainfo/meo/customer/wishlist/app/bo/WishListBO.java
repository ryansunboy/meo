package com.asiainfo.meo.customer.wishlist.app.bo;


import java.util.List;

import com.asiainfo.meo.customer.wishlist.app.model.entity.WishList;
import com.asiainfo.meo.customer.wishlist.app.model.vo.WishListVO;

public interface WishListBO
{
    /**
      * @Description: (添加wishlist)
      * @Description: (add wishlist)
      * @modifyReason: 
      * @author lill
      * @param wishList    
      */
    void saveWishList(WishList wishList);
    
    /**
      * @Description: (移除wishlist)
      * @Description: (remove wishlist)
      * @modifyReason: 
      * @author lill
      * @param custId
      * @param productId    
      */
    void deleteWishList(long custId, Long wishListId);
    
    /**
      * @Description: (获取wish清单)
      * @Description: (get wishlist)
      * @modifyReason: 
      * @author lill
      * @param custId
      * @return    
      */
    List<WishListVO> getWishListVO(long custId);
    
    /**
     * @Description: (根据wishlistId和custId获取wishlist)
     * @Description: (get wishlist from wishlistId and custId)
     * @modifyReason: 
     * @author lill
     * @param map
     * @return    
     * 
     */
//    Collection<WishList> getWishList(long custId, Long wishListId);
}
