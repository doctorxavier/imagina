package com.imagina.dao.nice;

import java.util.List;

import com.imagina.dao.AbstractDao;

public class SystemDao extends AbstractDao<com.imagina.model.nice.System> implements ISystemDao {
	
	public List<com.imagina.model.nice.System> getSystems() {
		return this.namedQuery("systems", null);
	}

}
