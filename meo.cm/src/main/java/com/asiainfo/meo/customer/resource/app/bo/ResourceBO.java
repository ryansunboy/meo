package com.asiainfo.meo.customer.resource.app.bo;

import java.util.List;

import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.customer.resource.app.model.entity.CmResource;
import com.asiainfo.meo.customer.resource.app.model.vo.PResource;

public interface ResourceBO
{
    /**
     * @Description: (得到客户产品实例)
     * @Description: (get resource information)
     * @modifyReason:
     * @author lill
     * @param custId
     * @return
     */
    List<CmResource> getResouce(long custId);
    
    List<PResource> getResourceListbycustid(long custId, Integer pageSize, Integer pageNo);
    
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
    public PageInfo<PResource> getResourcePageInfoBycustId(Long custId,Integer resType,Integer resSts, Integer pageSize, Integer pageNo);
    
    public CmResource addCmResource(CmResource cmResource);
    
    public CmResource getCmResourceById(Long id);
    
    public CmResource updateCmResource(CmResource cmResource);
}
