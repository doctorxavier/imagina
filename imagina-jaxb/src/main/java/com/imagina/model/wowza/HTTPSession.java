package com.imagina.model.wowza;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"sessionId", "ipAddress", "referrer", "queryString", "timeRunning", "dateStarted", "URI", "protocol", "sessionType", "port",
		"ioBytesSent", "ioBytesReceived", "ioLastRequest"})
public class HTTPSession extends AbstractSession implements Serializable {

	private static final long	serialVersionUID	= -6703726701902653181L;
	
	private String	sessionId;
	private String	sessionType;
	private Long	ioLastRequest;

	@XmlElement(name = "SessionId")
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@XmlElement(name = "SessionType")
	public String getSessionType() {
		return sessionType;
	}

	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}

	@XmlElement(name = "IoLastRequest")
	public Long getIoLastRequest() {
		return ioLastRequest;
	}

	public void setIoLastRequest(Long ioLastRequest) {
		this.ioLastRequest = ioLastRequest;
	}

}
