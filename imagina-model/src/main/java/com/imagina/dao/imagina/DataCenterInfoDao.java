package com.imagina.dao.imagina;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imagina.dao.AbstractDao;
import com.imagina.model.DataCenterInfo;

public class DataCenterInfoDao extends AbstractDao<DataCenterInfo> implements IDataCenterInfoDao {

	protected static Logger	logger	= LoggerFactory.getLogger(DataCenterInfoDao.class);

	public List<DataCenterInfo> getAveraged(Integer sec, Date from) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("date", from);
		param.put("sec", sec);
		return this.namedQuery("getAverage", param);
	}

}
