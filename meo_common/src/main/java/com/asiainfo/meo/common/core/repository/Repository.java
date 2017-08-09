package com.asiainfo.meo.common.core.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface Repository 
{
    void saveObject(Object entity);
    
    void updateObject(Object entity);
    
    void deleteObject(Object entity);
    
    void saveOrUpdate(Object entity);
    
    void deleteAll(Collection<?> entities);
    
    void saveOrUpdataAll(Collection<?> entities);
    
    Object[] executeProcedure(String procedureName,Object[] input,int[] outPutType);
    
    Map<String,Object> executeProcedure(String procedureName,Map<String,Object> input,Map<String,Class<?>> outPutType);
    
    List<?> findByNamedQuery(String queryName, Object... values) ;
    
    List<?> findByNativeSql(String queryString, Object... values);
    
    List<Map<String,Object>> findMapByNativeSql(String queryString, Object ... values);
    
    <T> T findUniqueObjectByNamedQuery(String queryName, Object... values);
    
    <T> T findUniqueObjectByNativeSql(String queryString, Object... values);
    
    Map<String,Object> findUniqueMapByNativeSql(String queryString, Object... values);
    
    int executeByNativeSql(String queryString, Object... values);
    
    int executeByNameSql(String queryString, Object... values);
    
    int executeByNameSqlAndNamedParam(String queryName,String[] paramName,Object[] value);
    
    List<?> findByNamedQueryAndNamedParam(String queryName, String[] paramNames,Object[] values) ;
    
    List<?> findByNamedQueryAndNamedParam(String queryName, String paramName,Object value) ;
    
    <T> T get(Class<T> entityClass, Serializable id);
    
    <T> List<T> findByParams(Class<T> entityClass,Map<String,Object> params);
    
    <T> List<T> findByParams(Class<T> entityClass,Map<String,Object> params,int firstResults,int maxResults);
    
    <T> T load(Class<T> entityClass, Serializable id);
    
    <T> List<T> loadAll(Class<T> entityClass);
}
