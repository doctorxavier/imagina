package com.imagina.dao.nagios;

import java.util.List;

import com.imagina.dao.AbstractDao;
import com.imagina.model.nagios.Bitrate;

public class BitrateDao extends AbstractDao<Bitrate> implements IBitrateDao {

	public List<Bitrate> getBitrates() {
		return this.namedQuery("bitrates", null);
	}
	
}
