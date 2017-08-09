package com.asiainfo.meo.customer.resource.app.repository;

import com.asiainfo.meo.customer.resource.app.model.entity.CmResource;

public interface CmResourceRepository
{
    public CmResource addCmResource(CmResource cmResource);
    
    public CmResource getCmResourceById(Long id);
    
    public CmResource updateCmResource(CmResource cmResource);
}
