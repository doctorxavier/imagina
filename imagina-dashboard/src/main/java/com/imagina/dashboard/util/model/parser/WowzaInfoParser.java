package com.imagina.dashboard.util.model.parser;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.imagina.model.DataCenter;
import com.imagina.model.DataCenterInfo;
import com.imagina.model.EdgeInfo;
import com.imagina.model.Info;
import com.imagina.model.wowza.WowzaInfo;

public class WowzaInfoParser {

	public Info unwrapDataCenterInfo(WowzaInfo wowzaInfo) {

		Info info = new Info();
		info.setDate(new Date(wowzaInfo.getDate()));

		for (Map.Entry<String, com.imagina.model.wowza.DataCenterInfo> entryDataCenterInfo : wowzaInfo.getInfo().entrySet()) {

			com.imagina.model.wowza.DataCenterInfo dataCenterInfoWowza = entryDataCenterInfo.getValue();
			DataCenterInfo dataCenterInfo = new DataCenterInfo();

			dataCenterInfo.setDataCenter(DataCenter.parse(entryDataCenterInfo.getKey()));

			dataCenterInfo.setInfo(info);
			
			dataCenterInfo.setDownload(dataCenterInfoWowza.getDownload());
			dataCenterInfo.setNumCon(dataCenterInfoWowza.getNumCon());
			dataCenterInfo.setNumConLive(dataCenterInfoWowza.getNumConLive());
			dataCenterInfo.setNumConVod(dataCenterInfoWowza.getNumConVod());
			dataCenterInfo.setUpload(dataCenterInfoWowza.getUpload());

			for (Map.Entry<String, com.imagina.model.wowza.EdgeInfo> entryEdgeInfo : dataCenterInfoWowza.getEdgeInfos().entrySet()) {
				com.imagina.model.wowza.EdgeInfo edgeInfoWowza = entryEdgeInfo.getValue();

				EdgeInfo edgeInfo = new EdgeInfo();
				edgeInfo.setEdge(Integer.valueOf(StringUtils.right(entryEdgeInfo.getKey(), 1)));
				
				edgeInfo.setDataCenterInfo(dataCenterInfo);
				
				edgeInfo.setNumCon(edgeInfoWowza.getNumCon());
				edgeInfo.setNumConLive(edgeInfoWowza.getNumConLive());
				edgeInfo.setNumConVod(edgeInfoWowza.getNumConVod());

				dataCenterInfo.getEdgeInfos().add(edgeInfo);
			}
			info.getDataCenterInfos().add(dataCenterInfo);
		}

		return info;
	}

	public WowzaInfo wrapDataCenterInfo(Info info) {
		WowzaInfo wowzaInfo = new WowzaInfo();
		
		wowzaInfo.setDate(info.getDate().getTime());

		for (DataCenterInfo dataCenterInfo : info.getDataCenterInfos()) {
			com.imagina.model.wowza.DataCenterInfo dataCenterInfoWowza = new com.imagina.model.wowza.DataCenterInfo();

			dataCenterInfoWowza.setDownload(dataCenterInfo.getDownload());
			dataCenterInfoWowza.setNumCon(dataCenterInfo.getNumCon());
			dataCenterInfoWowza.setNumConLive(dataCenterInfo.getNumConLive());
			dataCenterInfoWowza.setNumConVod(dataCenterInfo.getNumConVod());
			dataCenterInfoWowza.setUpload(dataCenterInfo.getUpload());

			for (EdgeInfo edgeInfo : dataCenterInfo.getEdgeInfos()) {
				com.imagina.model.wowza.EdgeInfo edgeInfoWowza = new com.imagina.model.wowza.EdgeInfo();
				edgeInfoWowza.setNumCon(edgeInfo.getNumCon());
				edgeInfoWowza.setNumConLive(edgeInfo.getNumConLive());
				edgeInfoWowza.setNumConVod(edgeInfo.getNumConVod());

				dataCenterInfoWowza.getEdgeInfos().put("edge0" + edgeInfo.getEdge() , edgeInfoWowza);
			}
			wowzaInfo.getInfo().put(DataCenter.parse(dataCenterInfo.getDataCenter()), dataCenterInfoWowza);
		}

		return wowzaInfo;
	}

}
