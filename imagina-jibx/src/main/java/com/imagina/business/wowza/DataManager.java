package com.imagina.business.wowza;

import java.util.List;

import com.imagina.model.wowza.Application;
import com.imagina.model.wowza.DataCenter;
import com.imagina.model.wowza.DataCenterInfo;
import com.imagina.model.wowza.EdgeInfo;
import com.imagina.model.wowza.VHost;
import com.imagina.model.wowza.WowzaInfo;
import com.imagina.model.wowza.WowzaMediaServerPro;

public class DataManager implements IDataManager {

	private IReader	reader	= new RemoteReader();

	public List<WowzaMediaServerPro> getData(DataCenter dataCenter) {
		return this.reader.getData(dataCenter);
	}

	public WowzaInfo loadData() {
		WowzaInfo wowzaInfo = new WowzaInfo();
		wowzaInfo.setDate(System.currentTimeMillis());
		
		DataCenterInfo dataCenterInfo = loadData(DataCenter.BARCELONA);
		wowzaInfo.getInfo().put("bcn", dataCenterInfo);

		dataCenterInfo = loadData(DataCenter.MADRID);
		wowzaInfo.getInfo().put("mad", dataCenterInfo);

		return wowzaInfo;
	}

	public DataCenterInfo loadData(DataCenter dataCenter) {
		DataCenterInfo dataCenterInfo = new DataCenterInfo();

		long connectionsCurrent = 0;
		long connectionsCurrentLive = 0;
		long connectionsCurrentVod = 0;

		for (WowzaMediaServerPro wowzaMediaServerPro : getData(dataCenter)) {
			
			long connectionsCurrentEdge = 0;
			long connectionsCurrentLiveEdge = 0;
			long connectionsCurrentVodEdge = 0;
			
			EdgeInfo edgeInfo = new EdgeInfo();
			for (VHost vHost : wowzaMediaServerPro.getVHosts()) {
				connectionsCurrent += vHost.getConnectionsCurrent();
				connectionsCurrentEdge += vHost.getConnectionsCurrent();
				for (Application application : vHost.getApplications()) {
					if ("niceLiveServer".equals(application.getName())) {
						connectionsCurrentLive += application.getConnectionsCurrent();
						connectionsCurrentLiveEdge += application.getConnectionsCurrent();
					} else if ("niceVodServer".equals(application.getName())) {
						connectionsCurrentVod += application.getConnectionsCurrent();
						connectionsCurrentVodEdge += application.getConnectionsCurrent();
					}
				}
			}
			
			edgeInfo.setNumCon(connectionsCurrentEdge);
			edgeInfo.setNumConLive(connectionsCurrentLiveEdge);
			edgeInfo.setNumConVod(connectionsCurrentVodEdge);
			
			dataCenterInfo.getEdgeInfos().put(wowzaMediaServerPro.getName(), edgeInfo);
		}
		dataCenterInfo.setNumCon(connectionsCurrent);
		dataCenterInfo.setNumConLive(connectionsCurrentLive);
		dataCenterInfo.setNumConVod(connectionsCurrentVod);

		return dataCenterInfo;
	}

}
