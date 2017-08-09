package com.asiainfo.meo.customer.resource.app.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.customer.resource.app.bo.ResourceBO;
import com.asiainfo.meo.customer.resource.app.model.entity.CmResource;
import com.asiainfo.meo.customer.resource.app.model.vo.PResource;
import com.asiainfo.meo.customer.resource.app.repository.CmResourceRepository;
import com.asiainfo.meo.customer.resource.app.repository.ProductBORepository;
import com.asiainfo.meo.customer.service.require.CustomerRserviceBO;
import com.asiainfo.meo.product.profile.app.model.entity.Product;
import com.asiainfo.meo.system.common.app.model.vo.EnumDefine;

public class ResourceBOImpl implements ResourceBO
{
    @Resource
    private ProductBORepository productBoRepository;
    
    @Resource
    private CustomerRserviceBO customerRserviceBO;
    
    @Resource
    private CmResourceRepository cmResourceRepository;
    
    public List<CmResource> getResouce(long custId)
    {
        return productBoRepository.getResouce(custId);
    }
    
    @Override
    public List<PResource> getResourceListbycustid(long custId, Integer pageSize, Integer pageNo)
    {
        List<PResource> pResourceList = new ArrayList<PResource>();
        List<CmResource>  cmResourceList = productBoRepository.getResourceListbycustid(custId, pageSize, pageNo);
        if (ValidateUtil.isEmpty(cmResourceList))
        {
           return pResourceList; 
        }
        for (CmResource cmResource : cmResourceList)
        {
            if (ValidateUtil.isEmpty(cmResource.getProductId()))
            {
                continue;
            }
            PResource pResource = new PResource();
            Product product = customerRserviceBO.getProductById(cmResource.getProductId());
            if (ValidateUtil.isNotEmpty(product))
            {
                pResource.setResourceName(product.getProductName());
                pResource.setDescription(product.getDescription());
            }
            pResource.setResourceId(cmResource.getResourceId());
            pResource.setResourceStsId(cmResource.getResSts());
            EnumDefine resourceStsValue = customerRserviceBO.getEnumDefByEnumCode(String.valueOf(cmResource.getResSts()), Integer.valueOf(PResource.RESOURCE_STATUS));
            if (ValidateUtil.isNotEmpty(resourceStsValue))
            {
                pResource.setResourceSts(resourceStsValue.getEnumName());
            }
            pResource.setCycleBeginDate(cmResource.getCycleBeginDate());
            pResource.setCycleEndDate(cmResource.getCycleEndDate());
            pResourceList.add(pResource);
        }
        return pResourceList;
    }

    @Override
    public CmResource addCmResource(CmResource cmResource)
    {
        return cmResourceRepository.addCmResource(cmResource);
    }

    @Override
    public CmResource getCmResourceById(Long id)
    {
        return cmResourceRepository.getCmResourceById(id);
    }

    @Override
    public CmResource updateCmResource(CmResource cmResource)
    {
        return cmResourceRepository.updateCmResource(cmResource);
    }

    @Override
    public PageInfo<PResource> getResourcePageInfoBycustId(Long custId, Integer resType, Integer resSts, Integer pageSize,
            Integer pageNo)
    {
        PageInfo<PResource> pageInfo = new PageInfo<PResource>();
        int totalSize = productBoRepository.getResourceListBycustIdTotalSize(custId, resType, resSts);
        if(totalSize==0)
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        List<CmResource> list = productBoRepository.getResourceListBycustId(custId, resType, resSts, pageSize, pageNo);
        if(ValidateUtil.isEmpty(list))
        {
            return pageInfo.emptyPageInfo(pageSize);
        }
        List<PResource> pResourceList = new ArrayList<PResource>();
        for (CmResource resource : list)
        {
            if (resource.getProductId()==null)
            {
                continue;
            }
            PResource pResource = new PResource();
            Product product = customerRserviceBO.getProductById(resource.getProductId());
            if (product!=null)
            {
                pResource.setResourceName(product.getProductName());
                pResource.setDescription(product.getDescription());
            }
            pResource.setResourceId(resource.getResourceId());
            pResource.setResourceStsId(resource.getResSts());
            EnumDefine resourceStsValue = customerRserviceBO.getEnumDefByEnumCode(String.valueOf(resource.getResSts()), Integer.valueOf(PResource.RESOURCE_STATUS));
            if (resourceStsValue!=null)
            {
                pResource.setResourceSts(resourceStsValue.getEnumName());
            }
            pResource.setCycleBeginDate(resource.getCycleBeginDate());
            pResource.setCycleEndDate(resource.getCycleEndDate());
            pResourceList.add(pResource);
        }
        return pageInfo.createPageInfo(pageNo, pageSize, totalSize, pResourceList);
    }
    
}
