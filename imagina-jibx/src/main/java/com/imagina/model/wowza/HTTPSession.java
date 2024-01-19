package com.imagina.model.wowza;

import java.io.Serializable;

public class HTTPSession extends AbstractSession implements Serializable {

	private static final long	serialVersionUID	= 5362242402295403790L;
	
	private String	sessionId;
	private String	sessionType;
	private long	ioLastRequest;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionType() {
		return sessionType;
	}

	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}

	public long getIoLastRequest() {
		return ioLastRequest;
	}

	public void setIoLastRequest(long ioLastRequest) {
		this.ioLastRequest = ioLastRequest;
	}

}
