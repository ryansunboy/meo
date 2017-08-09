package com.asiainfo.meo.common.core.repository.hibernate.impl;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.ParameterMode;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.Work;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.asiainfo.meo.common.core.repository.hibernate.HibernateRepository;
import com.asiainfo.meo.common.core.utils.ValidateUtil;

public class HibernateRepositoryImpl extends HibernateDaoSupport implements HibernateRepository
{
    
    public List<?> findByCriteria(DetachedCriteria criteria)
    {
        return getHibernateTemplate().findByCriteria(criteria);
    }
    
    public List<?> findByCriteria(DetachedCriteria criteria, int firstResults, int maxResults)
    {
        return getHibernateTemplate().findByCriteria(criteria, firstResults, maxResults);
    }
    
    public <T> T findUniqueObjectByCriteria(DetachedCriteria criteria)
    {
        List<?> list = findByCriteria(criteria);
        return convertListToUniqueObject(list);
    }
    
    public List<?> findByNamedQuery(String queryName, Object... values)
    {
        return getHibernateTemplate().findByNamedQuery(queryName, values);
    }
    
    public int executeByNativeSql(final String queryString, final Object... values)
    {
        return getHibernateTemplate().bulkUpdate(queryString, values);
    }
    
    public int executeByNameSql(final String queryName, final Object... values)
    {
        return getHibernateTemplate().execute(new HibernateCallback<Integer>()
        {
            
            public Integer doInHibernate(Session session) throws HibernateException
            {
                Query queryObject = session.getNamedQuery(queryName);
                if (values!= null)
                {
                    for (int i = 0; i< values.length; i++ )
                    {
                        queryObject.setParameter(i, values[i]);
                    }
                }
                return queryObject.executeUpdate();
            }
        });
    }
    
    public void updateObject(Object entity)
    {
        getHibernateTemplate().update(entity);
    }
    
    public void deleteObject(Object entity)
    {
        getHibernateTemplate().delete(entity);
    }
    
    // TODO
    public void deleteAll(Collection<?> entities)
    {
        getHibernateTemplate().deleteAll(entities);
    }
    
    public void saveObject(Object entity)
    {
        getHibernateTemplate().save(entity);
    }
    
    public void saveOrUpdate(Object entity)
    {
        getHibernateTemplate().saveOrUpdate(entity);
    }
    
    // TODO
    public void saveOrUpdataAll(Collection<?> entities)
    {
        // getHibernateTemplate().saveOrUpdateAll(entities);
        // getHibernateTemplate().execute(new Hibernate)
    }
    
    public List<?> findByNativeSql(String queryString, Object... values)
    {
        return getHibernateTemplate().find(queryString, values);
    }
    
    public List<Map<String, Object>> findMapByNativeSql(final String queryString, final Object... values)
    {
        List<?> list = getHibernateTemplate().execute(new HibernateCallback<List<?>>()
        {
            public List<?> doInHibernate(Session session) throws HibernateException
            {
                Query queryObject = session.createSQLQuery(queryString);
                queryObject.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                if (values!= null)
                {
                    for (int i = 0; i< values.length; i++ )
                    {
                        queryObject.setParameter(i, values[i]);
                    }
                }
                return queryObject.list();
            }
        });
        return (List<Map<String, Object>>) list;
    }
    
    public <T> T findUniqueObjectByNamedQuery(String queryName, Object... values)
    {
        List<?> list = findByNamedQuery(queryName, values);
        return convertListToUniqueObject(list);
    }
    
    public <T> T findUniqueObjectByNativeSql(String queryStrings, Object... values)
    {
        List<?> list = findByNativeSql(queryStrings, values);
        return convertListToUniqueObject(list);
    }
    
    public Map<String, Object> findUniqueMapByNativeSql(String queryString, Object... values)
    {
        List<Map<String, Object>> list = findMapByNativeSql(queryString, values);
        return convertListToUniqueObject(list);
    }
    
    public Object[] executeProcedure(final String procedureName, final Object[] input, final int[] outPutType)
    {
        Object[] result = (Object[]) getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Object>()
        {
            public Object doInHibernate(Session session) throws HibernateException
            {
                final Object[] result = new Object[outPutType.length];
                session.doWork(new Work()
                {
                    
                    public void execute(Connection connection) throws SQLException
                    {
                        CallableStatement cs = connection.prepareCall(procedureName);
                        for (int i = 0; i< input.length; i++ )
                        {
                            cs.setObject(i+ 1, input[i]);
                        }
                        for (int j = 0; j< outPutType.length; j++ )
                        {
                            cs.registerOutParameter(j+ input.length+ 1, outPutType[j]);
                        }
                        cs.execute();
                        for (int k = 0; k< outPutType.length; k++ )
                        {
                            result[k] = cs.getObject(k+ input.length+ 1);
                        }
                        
                    }
                });
                
                return result;
            }
        });
        return result;
    }
    
    private <T> T convertListToUniqueObject(List<?> resultList)
    {
        if (ValidateUtil.isEmpty(resultList))
        {
            // throw new MeoException("Can not find any result,please check your data or hibernate configuration");
            return null;
        }
        if (resultList.size()> 1)
        {
            throw new IllegalArgumentException("The query list result is not unique....");
        }
        return (T) resultList.get(0);
    }
    
    public <T> T get(Class<T> entityClass, Serializable id)
    {
        return getHibernateTemplate().get(entityClass, id);
    }
    
    public <T> T load(Class<T> entityClass, Serializable id)
    {
        return getHibernateTemplate().load(entityClass, id);
    }
    
    public <T> List<T> loadAll(Class<T> entityClass)
    {
        return getHibernateTemplate().loadAll(entityClass);
    }
    
    public Map<String, Object> executeProcedure(final String procedureName, final Map<String, Object> input,
            final Map<String, Class<?>> outPut)
    {
        getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Object>()
        {
            
            public Object doInHibernate(Session session) throws HibernateException
            {
                ProcedureCall procedureCall = session.createStoredProcedureCall(procedureName);
                if (!ValidateUtil.isEmpty(input))
                {
                    Set<Entry<String, Object>> inputEntries = input.entrySet();
                    
                    for (Entry<String, Object> inputEntry : inputEntries)
                    {
                        String key = inputEntry.getKey();
                        Object value = inputEntry.getValue();
                        procedureCall.registerParameter(key, Object.class, ParameterMode.IN).bindValue(value);
                    }
                }
                
                ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
                
                if (!ValidateUtil.isEmpty(outPut))
                {
                    Set<Entry<String, Class<?>>> outPutEntries = outPut.entrySet();
                    
                    for (Entry<String, Class<?>> outPutEntry : outPutEntries)
                    {
                        String key = outPutEntry.getKey();
                        Class<?> clazz = outPutEntry.getValue();
                        
                        procedureCall.registerParameter(key, clazz, ParameterMode.OUT);
                    }
                    
                }
                
                return null;
            }
        });
        return null;
    }
    
    public <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> valueParams)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
        criteria.add(Restrictions.allEq(valueParams));
        return (List<T>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    public <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> valueParams, int firstResults, int maxResults)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
        criteria.add(Restrictions.allEq(valueParams));
        return (List<T>) getHibernateTemplate().findByCriteria(criteria, firstResults, maxResults);
    }
    
    /*
     * (Not Javadoc) <p>Title: executeByNameSqlAndNamedParam</p> <p>Description: </p>
     * @param queryName
     * @param paramName
     * @param value
     * @return
     * @see com.asiainfo.meo.common.core.repository.Repository#executeByNameSqlAndNamedParam(java.lang.String, java.lang.String[],
     * java.lang.Object[])
     */
    @Override
    public int executeByNameSqlAndNamedParam(final String queryName, final String[] paramNames, final Object[] values)
    {
        if (values!= null&& (paramNames== null|| paramNames.length!= values.length))
        {
            throw new IllegalArgumentException("Length of paramNames array must match length of values array");
        }
        return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Integer>()
        {
            
            @Override
            public Integer doInHibernate(Session session) throws HibernateException
            {
                
                Query queryObject = session.getNamedQuery(queryName);
                if (values!= null)
                {
                    for (int i = 0; i< values.length; i++ )
                    {
                        applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
                    }
                }
                return queryObject.executeUpdate();
            }
        });
    }
    
    private void applyNamedParameterToQuery(Query queryObject, String paramName, Object value) throws HibernateException
    {
        
        if (value instanceof Collection)
        {
            queryObject.setParameterList(paramName, (Collection<?>) value);
        }
        else if (value instanceof Object[])
        {
            queryObject.setParameterList(paramName, (Object[]) value);
        }
        else
        {
            queryObject.setParameter(paramName, value);
        }
    }

    /* (Not Javadoc)                                         
      * <p>Title: findByNamedQueryAndNamedParam</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param queryName
      * @param paramNames
      * @param values
      * @return                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.common.core.repository.Repository#findByNamedQueryAndNamedParam(java.lang.String, java.lang.String[], java.lang.Object[])                                                                                                                                                                                                                                                                      
      */
    @Override
    public List<?> findByNamedQueryAndNamedParam(String queryName, String[] paramNames, Object[] values)
    {
        // TODO Auto-generated method stub
        return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, paramNames, values);
    }

    /* (Not Javadoc)                                         
      * <p>Title: findByNamedQueryAndNamedParam</p>                                                                                                                                                                                                                                             
      * <p>Description: </p>                                                                                                                                                                                                                                                          
      * @param queryName
      * @param paramName
      * @param value
      * @return                                                                                                                                                                                                                                                                                   
      * @see com.asiainfo.meo.common.core.repository.Repository#findByNamedQueryAndNamedParam(java.lang.String, java.lang.String, java.lang.Object)                                                                                                                                                                                                                                                                      
      */
    @Override
    public List<?> findByNamedQueryAndNamedParam(String queryName, String paramName, Object value)
    {
        // TODO Auto-generated method stub
        return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, paramName, value);
    }

}
