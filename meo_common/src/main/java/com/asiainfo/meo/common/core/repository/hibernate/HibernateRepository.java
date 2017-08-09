package com.asiainfo.meo.common.core.repository.hibernate;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

import com.asiainfo.meo.common.core.repository.Repository;

public interface HibernateRepository extends Repository
{
     List<?> findByCriteria(DetachedCriteria criteria);
    
     List<?> findByCriteria(DetachedCriteria criteria, int firstResults,int maxResults);
    
    <T> T findUniqueObjectByCriteria(DetachedCriteria criteria);
}
