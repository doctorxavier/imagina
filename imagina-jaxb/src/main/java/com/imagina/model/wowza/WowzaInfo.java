package com.imagina.model.wowza;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class WowzaInfo implements Serializable {

	private static final long			serialVersionUID	= 5940003317531929132L;

	private long						date;
	private Map<String, DataCenterInfo>	info				= new HashMap<String, DataCenterInfo>();

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public Map<String, DataCenterInfo> getInfo() {
		return info;
	}

	public void setInfo(Map<String, DataCenterInfo> info) {
		this.info = info;
	}

}
