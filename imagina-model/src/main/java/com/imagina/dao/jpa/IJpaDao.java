package com.imagina.dao.jpa;

import java.util.List;
import java.util.Map;

import com.imagina.util.dao.DataModelFilter;

public interface IJpaDao {

	void detach(Object obj);

	void evict(Class<?> entityClass, Object primaryKey);

	@SuppressWarnings("rawtypes")
	List getFilteredItems(Class<?> entityClass, DataModelFilter filters) throws SecurityException, NoSuchFieldException;

	@SuppressWarnings("rawtypes")
	List getFilteredItems(Class<?> entityClass, DataModelFilter filters, Integer start, Integer rows) throws SecurityException, NoSuchFieldException;
	
	long getRowCount(Class<?> entityClass);

	int getRowCount(Class<?> entityClass, DataModelFilter filters) throws SecurityException, NoSuchFieldException;
	
	@SuppressWarnings("rawtypes")
	List listEntities(Class<?> entityClass);
	
	Object load(Class<?> entityClass, Object primaryKey);
	
	Object loadById(Class<?> entityClass, Integer id);

	Object loadByRef(Class<?> entityClass, Object primaryKey);

	Object merge(Object obj);
	
	@SuppressWarnings("rawtypes")
	List pagedEntities(Class<?> entityClass, int page, int pageSize);
	
	void persist(Object obj);
	
	void remove(Object obj, Integer id);
	
	@SuppressWarnings("rawtypes")
	List namedQuery(String name, Map<?, ?> param);
	
	@SuppressWarnings("rawtypes")
	List namedQuery(String name, Map<?, ?> param, Class<?> entityClass);
	
	@SuppressWarnings("rawtypes")
	List nativeQueryResultList(String sqlString, Map<?, ?> param);
	
	@SuppressWarnings("rawtypes")
	List nativeQueryResultList(String sqlString, Map<?, ?> param, Class<?> entityClass);
	
	@SuppressWarnings("rawtypes")
	List queryResultList(String sqlString, Map<?, ?> param);
	
	@SuppressWarnings("rawtypes")
	List queryResultList(String sqlString, Map<?, ?> param, Class<?> entityClass);
	
	int execute(String query, Map<?, ?> param);

}
