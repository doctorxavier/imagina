package com.imagina.util.dao;

import java.io.Serializable;

import com.imagina.util.dao.filter.Ordering;

public class FilterField implements Serializable {

	public enum Predicate {
		EQUAL, NOTEQUAL, GREATERTHAN, GREATERTHANOREQUALTO, LESSTHAN, LESSTHANOREQUALTO, BETWEEN, ISNULL, ISNOTNULL;
	};

	private static final long	serialVersionUID	= -3791452633480173578L;

	private String				propertyName;

	private Object				filterValue;

	private Ordering			order				= Ordering.UNSORTED;

	private boolean				groupBy;

	private Predicate			predicate;

	public Predicate getPredicate() {
		return predicate;
	}

	public void setPredicate(Predicate predicate) {
		this.predicate = predicate;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Object getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(Object filterValue) {
		this.filterValue = filterValue;
	}

	public Ordering getOrder() {
		return order;
	}

	public void setOrder(Ordering order) {
		this.order = order;
	}

	public boolean isGroupBy() {
		return groupBy;
	}

	public void setGroupBy(boolean groupBy) {
		this.groupBy = groupBy;
	}

}
