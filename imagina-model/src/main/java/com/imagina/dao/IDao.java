package com.imagina.dao;

import java.util.List;
import java.util.Map;

import com.imagina.util.dao.DataModelFilter;

public interface IDao<T> {

	void create(T entity);

	List<T> getAll();

	T getEntityByPrimaryKey(T entity);

	T getEntityByRef(T entity);

	void update(T entity);

	void delete(T entity);

	T getById(Integer id);

	void evict(Object primaryKey);

	void detach(Object object);

	long getRowCount();

	List<T> getFilteredItems(DataModelFilter filters, Integer start, Integer rows) throws SecurityException, NoSuchFieldException;

	List<T> getFilteredItems(DataModelFilter filters) throws SecurityException, NoSuchFieldException;
	
	List<T> namedQuery(String name, Map<?, ?> param);
	
	@SuppressWarnings("rawtypes")
	List nativeQueryResultList(String sqlString, Map<?, ?> param);

}
