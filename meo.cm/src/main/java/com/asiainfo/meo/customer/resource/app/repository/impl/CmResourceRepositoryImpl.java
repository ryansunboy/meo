package com.asiainfo.meo.customer.resource.app.repository.impl;

import javax.annotation.Resource;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.sequence.Sequence;
import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.customer.resource.app.model.entity.CmResource;
import com.asiainfo.meo.customer.resource.app.repository.CmResourceRepository;

public class CmResourceRepositoryImpl implements CmResourceRepository
{
    private final static String SEQ_RESOURCE_ID = "SEQ_RESOURCE_ID";
    
    @Resource
    private HibernateRepository hibernateRepository;
    
    @Override
    public CmResource addCmResource(CmResource cmResource)
    {
        Long resourceId = ServiceLocatorFactory.getService(Sequence.class).next(SEQ_RESOURCE_ID);
        cmResource.setResourceId(resourceId);
        cmResource.setCreateDate(DateTimeUtil.getNow());
        hibernateRepository.saveObject(cmResource);
        return getCmResourceById(resourceId);
    }

    @Override
    public CmResource getCmResourceById(Long id)
    {
        return hibernateRepository.get(CmResource.class, id);
    }

    @Override
    public CmResource updateCmResource(CmResource cmResource)
    {
        hibernateRepository.updateObject(cmResource);
        cmResource.setModifyDate(DateTimeUtil.getNow());
        return getCmResourceById(cmResource.getResourceId());
    }
    
}
