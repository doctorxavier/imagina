package com.imagina.dao.nice;

import java.util.List;

import com.imagina.dao.AbstractDao;
import com.imagina.model.nice.Stream;

public class StreamDao extends AbstractDao<Stream> implements IStreamDao {
	
	public List<Stream> getStreams() {
		return this.namedQuery("streams", null);
	}

}
