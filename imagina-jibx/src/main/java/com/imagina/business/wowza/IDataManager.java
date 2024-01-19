package com.imagina.business.wowza;

import com.imagina.model.wowza.DataCenter;
import com.imagina.model.wowza.DataCenterInfo;
import com.imagina.model.wowza.WowzaInfo;

public interface IDataManager {

	WowzaInfo loadData();
	
	DataCenterInfo loadData(DataCenter dataCenter);
	
}
