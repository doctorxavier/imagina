package com.imagina.model.wowza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApplicationInstance extends AbstractConnectionCounter implements Serializable {

	private static final long	serialVersionUID	= -1277138967902945953L;
	
	private String				name;
	private long				rTMPConnectionCount;
	private long				rTPConnectionCount;
	private long				cupertinoConnectionCount;
	private long				smoothConnectionCount;
	private long				sanJoseConnectionCount;
	private int					rTMPSessionCount;
	private int					hTTPSessionCount;
	private int					rTPSessionCount;
	private List<Client>		clients			= new ArrayList<Client>(0);
	private List<HTTPSession>	hTTPSessions	= new ArrayList<HTTPSession>(0);
	private List<HTTPSession>	rTPSessions		= new ArrayList<HTTPSession>(0);

	public List<HTTPSession> getRTPSessions() {
		return rTPSessions;
	}

	public void setRTPSessions(List<HTTPSession> rTPSessions) {
		this.rTPSessions = rTPSessions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getRTMPConnectionCount() {
		return rTMPConnectionCount;
	}

	public void setRTMPConnectionCount(long rTMPConnectionCount) {
		this.rTMPConnectionCount = rTMPConnectionCount;
	}

	public long getRTPConnectionCount() {
		return rTPConnectionCount;
	}

	public void setRTPConnectionCount(long rTPConnectionCount) {
		this.rTPConnectionCount = rTPConnectionCount;
	}

	public long getCupertinoConnectionCount() {
		return cupertinoConnectionCount;
	}

	public void setCupertinoConnectionCount(long cupertinoConnectionCount) {
		this.cupertinoConnectionCount = cupertinoConnectionCount;
	}

	public long getSmoothConnectionCount() {
		return smoothConnectionCount;
	}

	public void setSmoothConnectionCount(long smoothConnectionCount) {
		this.smoothConnectionCount = smoothConnectionCount;
	}

	public long getSanJoseConnectionCount() {
		return sanJoseConnectionCount;
	}

	public void setSanJoseConnectionCount(long sanJoseConnectionCount) {
		this.sanJoseConnectionCount = sanJoseConnectionCount;
	}

	public int getRTMPSessionCount() {
		return rTMPSessionCount;
	}

	public void setRTMPSessionCount(int rTMPSessionCount) {
		this.rTMPSessionCount = rTMPSessionCount;
	}

	public int getHTTPSessionCount() {
		return hTTPSessionCount;
	}

	public void setHTTPSessionCount(int hTTPSessionCount) {
		this.hTTPSessionCount = hTTPSessionCount;
	}

	public int getRTPSessionCount() {
		return rTPSessionCount;
	}

	public void setRTPSessionCount(int rTPSessionCount) {
		this.rTPSessionCount = rTPSessionCount;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<HTTPSession> getHTTPSessions() {
		return hTTPSessions;
	}

	public void setHTTPSessions(List<HTTPSession> hTTPSessions) {
		this.hTTPSessions = hTTPSessions;
	}
}
