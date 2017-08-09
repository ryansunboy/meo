package com.asiainfo.meo.customer.resource.app.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.customer.resource.app.model.entity.CmResource;
import com.asiainfo.meo.customer.resource.app.repository.ProductBORepository;


public class ProductBORepositoryImpl implements ProductBORepository
{
    @Resource
    private HibernateRepository hibernateRepository;
    
    @SuppressWarnings("unchecked")
    public List<CmResource> getResouce(long custId)
    {
        DetachedCriteria condition = DetachedCriteria.forClass(CmResource.class).add(Restrictions.eq("custId", custId));
        return (List<CmResource>) hibernateRepository.findByCriteria(condition);
    }
    
    
    public List<CmResource> getResourceListbycustid(long custId, Integer pageSize, Integer pageNo)
    {
        DetachedCriteria condition = DetachedCriteria.forClass(CmResource.class).add(Restrictions.eq("custId", custId));
        return (List<CmResource>) hibernateRepository.findByCriteria(condition, pageSize * (pageNo - 1),  pageSize);
    }


    private DetachedCriteria createDetachedCriteria(Long custId, Integer resType, Integer resSts)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(CmResource.class);
        criteria.add(Restrictions.eq("custId", custId));
        if(resType !=null)
        {
            criteria.add(Restrictions.eq("resType", resType));
        }
        if(resSts !=null)
        {
            criteria.add(Restrictions.eq("resSts", resSts));
        }
        return criteria;
    }
    
    @Override
    public List<CmResource> getResourceListBycustId(Long custId, Integer resType, Integer resSts, Integer pageSize, Integer pageNo)
    {
        DetachedCriteria criteria = createDetachedCriteria(custId, resType, resSts);
        return (List<CmResource>)hibernateRepository.findByCriteria(criteria, pageSize * (pageNo - 1),  pageSize);
    }


    @Override
    public int getResourceListBycustIdTotalSize(Long custId, Integer resType, Integer resSts)
    {
        DetachedCriteria criteria = createDetachedCriteria(custId, resType, resSts);
        List<CmResource> list = (List<CmResource>)hibernateRepository.findByCriteria(criteria);
        return ValidateUtil.isNotEmpty(list)?list.size():0;
    }
}
