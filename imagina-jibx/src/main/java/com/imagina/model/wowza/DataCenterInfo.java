package com.imagina.model.wowza;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DataCenterInfo implements Serializable {

	private static final long		serialVersionUID	= -7855692405240366612L;

	private long					numCon;
	private long					numConLive;
	private long					numConVod;
	private double					upload;
	private double					download;

	private Map<String, EdgeInfo>	edgeInfos			= new HashMap<String, EdgeInfo>();

	public double getUpload() {
		return upload;
	}

	public void setUpload(double upload) {
		this.upload = upload;
	}

	public double getDownload() {
		return download;
	}

	public void setDownload(double download) {
		this.download = download;
	}

	public Map<String, EdgeInfo> getEdgeInfos() {
		return edgeInfos;
	}

	public void setEdgeInfos(Map<String, EdgeInfo> edgeInfos) {
		this.edgeInfos = edgeInfos;
	}

	public long getNumCon() {
		return numCon;
	}

	public void setNumCon(long numCon) {
		this.numCon = numCon;
	}

	public long getNumConLive() {
		return numConLive;
	}

	public void setNumConLive(long numConLive) {
		this.numConLive = numConLive;
	}

	public long getNumConVod() {
		return numConVod;
	}

	public void setNumConVod(long numConVod) {
		this.numConVod = numConVod;
	}

}
