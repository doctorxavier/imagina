package com.imagina.dao.imagina;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imagina.dao.AbstractDao;
import com.imagina.model.DataCenterInfo;
import com.imagina.model.EdgeInfo;
import com.imagina.model.Info;
import com.imagina.util.dao.DataModelFilter;
import com.imagina.util.dao.FilterField;
import com.imagina.util.dao.FilterField.Predicate;

public class InfoDao extends AbstractDao<Info> implements IInfoDao {

	protected static Logger	logger	= LoggerFactory.getLogger(InfoDao.class);

	public Info getByIdFilled(Integer id) {
		Info info = new Info();
		info.setId(id);
		info = this.getEntityByPrimaryKey(info);
		cleanFilled(info);
		return info;
	}

	public void save(Info info) {
		this.create(info);
	}

	@Override
	public Info getById(Integer id) {
		Info info = new Info();
		info.setId(id);
		info = this.getEntityByPrimaryKey(info);
		if (info != null) {
			clean(info);
		}

		return info;
	}
	
	public List<Info> getAllFiltered(Integer sec) {

		List<Info> infos = null;

		try {

			infos = this.getAllFilter(sec);
			if (infos != null) {
				for (Info info : infos) {
					clean(info);
				}
			}

		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}

		return infos;
	}

	public List<Info> getAllFilteredFilled(Integer sec) {

		List<Info> infos = null;

		try {

			infos = getAllFilter(sec);
			if (infos != null) {
				for (Info info : infos) {
					cleanFilled(info);
				}
			}

		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}

		return infos;
	}
	
	private List<Info> getAllFilter(Integer sec) {
		DataModelFilter dataFilters = new DataModelFilter();
		List<FilterField> filters = new ArrayList<FilterField>(0);
		FilterField filterField = new FilterField();
		filterField.setPropertyName("sec");

		filterField.setFilterValue(sec);
		filterField.setPredicate(Predicate.EQUAL);

		filters.add(filterField);

		dataFilters.setFilters(filters);

		List<Info> infos = null;

		try {

			infos = this.getFilteredItems(dataFilters);

		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}

		return infos;
	}

	public List<Info> getAllFiltered(Date date) {

		List<Info> infos = null;

		try {

			infos = this.getAllFilter(date);
			if (infos != null) {
				for (Info info : infos) {
					clean(info);
				}
			}

		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}

		return infos;
	}

	public List<Info> getAllFilteredFilled(Date date) {

		List<Info> infos = null;

		try {

			infos = getAllFilter(date);
			if (infos != null) {
				for (Info info : infos) {
					cleanFilled(info);
				}
			}

		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}

		return infos;
	}
	
	public int cleanFrom(Date date) {
		String query = "DELETE FROM info WHERE info.date < ?";
		Map<Integer, Date> param = new HashMap<Integer, Date>();		
		param.put(1, date);
		return this.execute(query, param);
	}
	
	public int cleanFrom(Date date, Integer sec) {
		String query = "DELETE FROM info WHERE info.date < ? AND info.sec = ? ";
		Map<Integer, Object> param = new HashMap<Integer, Object>();		
		param.put(1, date);
		param.put(2, sec);
		return this.execute(query, param);
	}

	private List<Info> getAllFilter(Date date) {
		DataModelFilter dataFilters = new DataModelFilter();
		List<FilterField> filters = new ArrayList<FilterField>(0);
		FilterField filterField = new FilterField();
		filterField.setPropertyName("date");

		filterField.setFilterValue(date);
		filterField.setPredicate(Predicate.GREATERTHANOREQUALTO);

		filters.add(filterField);

		dataFilters.setFilters(filters);

		List<Info> infos = null;

		try {

			infos = this.getFilteredItems(dataFilters);

		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}

		return infos;
	}
	
	private void cleanFilled(Info info) {
		if (info != null) {
			List<DataCenterInfo> dataCenterInfos = new ArrayList<DataCenterInfo>(info.getDataCenterInfos());
			info.setDataCenterInfos(dataCenterInfos);
			for (DataCenterInfo dataCenterInfo : dataCenterInfos) {
				List<EdgeInfo> edgeInfos = new ArrayList<EdgeInfo>(dataCenterInfo.getEdgeInfos());
				dataCenterInfo.setEdgeInfos(edgeInfos);
			}
		}
	}
	
	private void clean(Info info) {
		if (info != null) {

			List<DataCenterInfo> dataCenterInfos = new ArrayList<DataCenterInfo>(info.getDataCenterInfos());
			info.setDataCenterInfos(dataCenterInfos);
			for (DataCenterInfo dataCenterInfo : dataCenterInfos) {
				dataCenterInfo.setEdgeInfos(new ArrayList<EdgeInfo>(0));
			}

		}
	}

	@SuppressWarnings("unchecked")
	private Info getLastItem() {
		String query = "SELECT auto_increment - 1 FROM information_schema.tables WHERE table_name = \'info\'";
		List<BigInteger> result = this.nativeQueryResultList(query, null);
		Info info = null;
		if (result != null && result.size() > 0) {
			Integer id = result.get(0).intValue();
			info = new Info();
			info.setId(id);
			info = this.getEntityByPrimaryKey(info);
		}
		
		return info;
	}
	
	public Info getLast() {
		Info info = this.getLastItem();
		clean(info);
		return info;
	}
	
	public Info getLastFilled() {
		Info info = this.getLastItem();
		cleanFilled(info);
		return info;
	}

}
