package com.imagina.dao.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import com.imagina.util.Utilities;
import com.imagina.util.dao.DataModelFilter;
import com.imagina.util.dao.FilterField;
import com.imagina.util.dao.filter.Ordering;

public class JpaDao implements IJpaDao {

	protected EntityManager	em;

	public EntityManager getEntityManager() {
		return em;
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private <T> TypedQuery createQueryWithCriterias(Class entityClass, DataModelFilter filters) throws SecurityException, NoSuchFieldException {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<?> criteria = builder.createQuery(entityClass);
		Root root = criteria.from(entityClass);

		if (filters != null) {

			javax.persistence.criteria.Predicate predicate = builder.conjunction();
			List<FilterField> filterFields = filters.getFilters();
			List<Order> orders = new ArrayList<Order>(0);

			for (FilterField filterField : filterFields) {
				Object value = filterField.getFilterValue();

				if (filterField.getFilterValue() != null) {

					switch (filterField.getPredicate()) {
						case BETWEEN:
							break;
						case EQUAL:
							if (value instanceof Integer) {
								Expression<Integer> expression = root.get(filterField.getPropertyName());
								predicate = builder.and(predicate, builder.equal(expression, (Integer) value));
							}
							break;
						case GREATERTHAN:
							break;
						case GREATERTHANOREQUALTO:
							if (value instanceof Date) {
								Expression<Date> expression = root.get(filterField.getPropertyName());
								predicate = builder.and(predicate, builder.greaterThanOrEqualTo(expression, (Date) value));
							} else if (value instanceof String) {
								Expression<String> expression = root.get(filterField.getPropertyName());
								predicate = builder.and(predicate, builder.greaterThanOrEqualTo(expression, (String) value));
							}
							break;
						case ISNOTNULL:
							break;
						case ISNULL:
							break;
						case LESSTHAN:
							break;
						case LESSTHANOREQUALTO:
							break;
						case NOTEQUAL:
							break;
						default:
							break;
					}
				}

				if (filterField.getOrder() != null) {
					Order order = null;
					if (filterField.getOrder().equals(Ordering.ASCENDING)) {
						order = builder.asc(root.get(filterField.getPropertyName()));
						orders.add(order);
					} else if (filterField.getOrder().equals(Ordering.DESCENDING)) {
						order = builder.desc(root.get(filterField.getPropertyName()));
						orders.add(order);
					}
				}
			}

			criteria.where(predicate);
			criteria.orderBy(orders);

		}

		return em.createQuery(criteria);
	}

	public void detach(Object obj) {
		em.detach(obj);
	}

	public void evict(Class<?> entityClass, Object primaryKey) {
		em.getEntityManagerFactory().getCache().evict(entityClass, primaryKey);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getFilteredItems(Class<?> entityClass, DataModelFilter filters) throws SecurityException, NoSuchFieldException {
		TypedQuery query = this.createQueryWithCriterias(entityClass, filters);
		List result = query.getResultList();
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getFilteredItems(Class<?> entityClass, DataModelFilter filters, Integer start, Integer rows) throws SecurityException, NoSuchFieldException {
		TypedQuery query = this.createQueryWithCriterias(entityClass, filters);

		if (start != null && rows != null) {
			query.setFirstResult(start);
			query.setMaxResults(rows);
		}

		List result = query.getResultList();
		return result;
	}

	public long getRowCount(Class<?> entityClass) {
		Query q = em.createQuery("select count(entity) from " + entityClass.getSimpleName() + " entity");
		return (Long) q.getSingleResult();
	}

	@Override
	public int getRowCount(Class<?> entityClass, DataModelFilter filters) throws NoSuchFieldException {
		return this.getFilteredItems(entityClass, filters).size();
	}

	@SuppressWarnings("rawtypes")
	public List listEntities(Class<?> entityClass) {
		return em.createQuery("select entity from " + entityClass.getSimpleName() + " entity").getResultList();
	}

	public Object load(Class<?> entityClass, Object primaryKey) {
		return em.find(entityClass, primaryKey);
	}

	public Object loadById(Class<?> entityClass, Integer id) {
		return em.createQuery("select entity from " + entityClass.getSimpleName() + " entity where entity.id = " + id).getSingleResult();
	}

	public Object loadByRef(Class<?> entityClass, Object primaryKey) {
		return em.getReference(entityClass, primaryKey);
	}

	@SuppressWarnings("rawtypes")
	public List loadCollection(String nameQuery, String[] ids) {
		Collection<Integer> idsTransformed = new ArrayList<Integer>(0);
		Utilities.transformStrInt(ids, idsTransformed);

		Map<Object, Object> map = new HashMap<Object, Object>(0);
		map.put("ids", idsTransformed);

		return namedQuery(nameQuery, map);
	}

	public Object merge(Object obj) {
		return em.merge(obj);
	}

	@SuppressWarnings("rawtypes")
	public List namedQuery(String name, Map<?, ?> param) {
		return this.namedQuery(name, param, null);
	}

	@SuppressWarnings("rawtypes")
	public List namedQuery(String name, Map<?, ?> param, Class<?> entityClass) {
		Query q = null;
		if (entityClass == null) {
			q = em.createNamedQuery(name);
		} else {
			q = em.createNamedQuery(name, entityClass);
		}
		if (param != null) {
			this.setParameters(q, param);
		}
		return q.getResultList();
	}

	public Object namedQuerySingle(String name, Map<?, ?> param) {
		Query q = em.createNamedQuery(name);
		if (param != null) {
			for (Object key : param.keySet()) {
				String parameter = (String) key;
				Object obj = param.get(key);
				if (obj instanceof Collection<?>) {
					q.setParameter(parameter, (Collection<?>) param.get(key));
				} else {
					q.setParameter(parameter, param.get(key));
				}
			}
		}
		try {
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public List nativeQueryResultList(String sqlString, Map<?, ?> param) {
		return this.nativeQueryResultList(sqlString, param, null);
	}

	@SuppressWarnings("rawtypes")
	public List queryResultList(String sqlString, Map<?, ?> param) {
		return this.queryResultList(sqlString, param, null);
	}

	@SuppressWarnings("rawtypes")
	public List queryResultList(String sqlString, Map<?, ?> param, Class<?> entityClass) {
		Query q = null;
		if (entityClass == null) {
			q = em.createQuery(sqlString);
		} else {
			q = em.createQuery(sqlString, entityClass);
		}
		if (param != null) {
			for (Object key : param.keySet()) {
				Integer parameter = (Integer) key;
				Object obj = param.get(key);
				if (obj instanceof Collection<?>) {
					q.setParameter(parameter, (Collection<?>) param.get(key));
				} else if (obj instanceof Date) {
					q.setParameter(parameter, (Date) param.get(key), TemporalType.TIMESTAMP);
				} else {
					q.setParameter(parameter, param.get(key));
				}
			}
		}
		return q.getResultList();
	}

	@SuppressWarnings("rawtypes")
	public List nativeQueryResultList(String sqlString, Map<?, ?> param, Class<?> entityClass) {
		Query q = null;
		if (entityClass == null) {
			q = em.createNativeQuery(sqlString);
		} else {
			q = em.createNativeQuery(sqlString, entityClass);
		}
		if (param != null) {
			for (Object key : param.keySet()) {
				Integer parameter = (Integer) key;
				Object obj = param.get(key);
				if (obj instanceof Collection<?>) {
					q.setParameter(parameter, (Collection<?>) param.get(key));
				} else if (obj instanceof Date) {
					q.setParameter(parameter, (Date) param.get(key), TemporalType.TIMESTAMP);
				} else {
					q.setParameter(parameter, param.get(key));
				}
			}
		}
		return q.getResultList();
	}

	public Integer nextId(String generatorName) {
		Query query = em.createNativeQuery("select k.last_used_id from keygen k where k.table_name = ?");
		query.setParameter(1, generatorName);
		Integer nextId = (Integer) query.getSingleResult() + 1;

		return nextId;
	}

	@SuppressWarnings("rawtypes")
	public List pagedEntities(Class<?> entityClass, int page, int pageSize) {
		Query query = em.createQuery("select entity from " + entityClass.getSimpleName() + " entity");
		query.setFirstResult(page * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	public void persist(Object obj) {
		em.persist(obj);
	}

	public void remove(Object obj, Integer id) {
		Object objCopy = em.find(obj.getClass(), id);
		em.remove(objCopy);
	}

	private void setParameters(Query q, Map<?, ?> param) {
		for (Object key : param.keySet()) {
			Object obj = param.get(key);

			if (key instanceof String) {
				String parameter = (String) key;
				if (obj instanceof Collection<?>) {
					q.setParameter(parameter, (Collection<?>) param.get(key));
				} else if (obj instanceof Date) {
					q.setParameter(parameter, (Date) param.get(key), TemporalType.TIMESTAMP);
				} else {
					q.setParameter(parameter, param.get(key));
				}
			} else if (key instanceof Integer) {
				Integer parameter = (Integer) key;
				if (obj instanceof Collection<?>) {
					q.setParameter(parameter, (Collection<?>) param.get(key));
				} else if (obj instanceof Date) {
					q.setParameter(parameter, (Date) param.get(key), TemporalType.TIMESTAMP);
				} else {
					q.setParameter(parameter, param.get(key));
				}
			}
		}
	}

	public int execute(String query, Map<?, ?> param) {
		Query q = em.createNativeQuery(query);
		if (param != null) {
			for (Object key : param.keySet()) {
				Integer parameter = (Integer) key;
				Object obj = param.get(key);
				if (obj instanceof Collection<?>) {
					q.setParameter(parameter, (Collection<?>) param.get(key));
				} else if (obj instanceof Date) {
					q.setParameter(parameter, (Date) param.get(key), TemporalType.TIMESTAMP);
				} else {
					q.setParameter(parameter, param.get(key));
				}
			}
		}
		return q.executeUpdate();
	}

}
