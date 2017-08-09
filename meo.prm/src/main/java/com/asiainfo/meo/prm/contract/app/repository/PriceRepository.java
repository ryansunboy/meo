package com.asiainfo.meo.prm.contract.app.repository;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.asiainfo.meo.prm.contract.app.model.entity.Price;

public interface PriceRepository
{
    /**
     * @Description: 保存合同价格
     * @Description: save price
     * @author zhaozx
     * @param Price
     */
    Price savePrice(Price price);
    
    /**
     * @Description: 修改合同价格
     * @Description: update price
     * @author zhaozx
     * @param contractPrice
     */
    Price updatePrice(Price price);
    
    /**
     * @Description:通过price id获得合同价格
     * @Description: get contract price entity by price id
     * @param priceId
     * @return ContractPrice
     * @author zhaozx
     */
    Price getPrice(long priceId);
    
    /**
      * @Description: (通过条件查询合同列表)
      * @Description: (get  price list from param)
      * @modifyReason: 
      * @author lill
      * @param criteria
      * @return    
      */
    List<Price> getPrice(DetachedCriteria criteria);
    
    /**
     * 
      * @Description: 删除价格
      * @Description: remove price
      * @modifyReason: 
      * @author zhengzy
      * @param id
     */
    public void deletePrice(Long id);
    
    /**
     * 
      * @Description: 根据价格id集合查询价格
      * @Description: query prices by ids
      * @modifyReason: 
      * @author zhengzy
      * @param ids
      * @return
     */
    public List<Price> getPricesByIds(Long[] ids);
    
}
