package com.asiainfo.meo.customer.wishlist.app.repository;

import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.asiainfo.meo.customer.wishlist.app.model.entity.WishList;

public interface WishListRepository
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
   void deleteWishList(Collection<WishList> entities);
   
   
   /**
     * @Description: (获取wish清单)
     * @Description: (get wishlist)
     * @modifyReason: 
     * @author lill
     * @param custId
     * @return    
     */
//   List<WishListVO> getWishListVO(long custId);
   

   
   List<WishList>  getWishList(DetachedCriteria conditon);
}
 