package com.asiainfo.meo.customer.resource.app.repository;

import java.util.List;

import com.asiainfo.meo.customer.resource.app.model.entity.CmResource;
import com.asiainfo.meo.customer.resource.app.model.vo.PResource;


public interface ProductBORepository
{
    List<CmResource> getResouce(long custId);
    
    public List<CmResource> getResourceListbycustid(long custId, Integer pageSize, Integer pageNo);
    
    /**
     * 
      * @Description: 根据 custId 、restType、resSts、pageNo、pageSize分页查询resource 信息
      * @Description: query resource pageInfo by custId 、restType、resSts、pageNo、pageSize
      * @modifyReason: 
      * @author zhengzy
      * @param resType
      * @param resSts
      * @param pageSize
      * @param pageNo
      * @return
     */
    public List<CmResource> getResourceListBycustId(Long custId,Integer resType,Integer resSts, Integer pageSize, Integer pageNo);
    
    public int getResourceListBycustIdTotalSize(Long custId,Integer resType,Integer resSts);
}
