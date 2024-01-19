package com.imagina.util.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataModelFilter implements Serializable {

	private static final long	serialVersionUID	= -6986134562276552508L;

	private List<FilterField>	filters				= new ArrayList<FilterField>(0);

	public List<FilterField> getFilters() {
		return filters;
	}

	public void setFilters(List<FilterField> filters) {
		this.filters = filters;
	}

}
