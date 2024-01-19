package com.imagina.dao.imagina;

import java.util.Date;
import java.util.List;

import com.imagina.model.DataCenterInfo;

public interface IDataCenterInfoDao {

	List<DataCenterInfo> getAveraged(Integer sec, Date from);
}
