package com.imagina.model.wowza;

import java.io.Serializable;

public class EdgeInfo implements Serializable {

	private static final long	serialVersionUID	= -7660934263125933774L;

	private long				numCon;
	private long				numConLive;
	private long				numConVod;

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
