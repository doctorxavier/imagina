package com.imagina.model.wowza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.imagina.util.xml.jaxb.StringAdapter;

@XmlType(propOrder = {"name", "timeRunning", "connectionsCurrent", "connectionsTotal", "connectionsTotalAccepted", "connectionsTotalRejected",
		"RTMPConnectionCount", "RTPConnectionCount", "cupertinoConnectionCount", "smoothConnectionCount", "sanJoseConnectionCount", "RTMPSessionCount",
		"HTTPSessionCount", "RTPSessionCount", "clients", "HTTPSessions", "RTPSessions"})
public class ApplicationInstance extends AbstractConnectionCounter implements Serializable {

	private static final long	serialVersionUID	= -6785084541267593154L;
	
	private String				name;
	private Long				rTMPConnectionCount;
	private Long				rTPConnectionCount;
	private Long				cupertinoConnectionCount;
	private Long				smoothConnectionCount;
	private Long				sanJoseConnectionCount;
	private Integer					rTMPSessionCount;
	private Integer					hTTPSessionCount;
	private Integer					rTPSessionCount;
	private List<Client>		clients			= new ArrayList<Client>(0);
	private List<HTTPSession>	hTTPSessions	= new ArrayList<HTTPSession>(0);
	private List<HTTPSession>	rTPSessions		= new ArrayList<HTTPSession>(0);

	@XmlElement(name = "RTPSession")
	public List<HTTPSession> getRTPSessions() {
		return rTPSessions;
	}

	public void setRTPSessions(List<HTTPSession> rTPSessions) {
		this.rTPSessions = rTPSessions;
	}

	@XmlElement(name = "Name")
	@XmlJavaTypeAdapter(value = StringAdapter.class)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "RTMPConnectionCount")
	public Long getRTMPConnectionCount() {
		return rTMPConnectionCount;
	}

	public void setRTMPConnectionCount(Long rTMPConnectionCount) {
		this.rTMPConnectionCount = rTMPConnectionCount;
	}

	@XmlElement(name = "RTPConnectionCount")
	public Long getRTPConnectionCount() {
		return rTPConnectionCount;
	}

	public void setRTPConnectionCount(Long rTPConnectionCount) {
		this.rTPConnectionCount = rTPConnectionCount;
	}

	@XmlElement(name = "CupertinoConnectionCount")
	public Long getCupertinoConnectionCount() {
		return cupertinoConnectionCount;
	}

	public void setCupertinoConnectionCount(Long cupertinoConnectionCount) {
		this.cupertinoConnectionCount = cupertinoConnectionCount;
	}

	@XmlElement(name = "SmoothConnectionCount")
	public Long getSmoothConnectionCount() {
		return smoothConnectionCount;
	}

	public void setSmoothConnectionCount(Long smoothConnectionCount) {
		this.smoothConnectionCount = smoothConnectionCount;
	}

	@XmlElement(name = "SanJoseConnectionCount")
	public Long getSanJoseConnectionCount() {
		return sanJoseConnectionCount;
	}

	public void setSanJoseConnectionCount(Long sanJoseConnectionCount) {
		this.sanJoseConnectionCount = sanJoseConnectionCount;
	}

	@XmlElement(name = "RTMPSessionCount")
	public Integer getRTMPSessionCount() {
		return rTMPSessionCount;
	}

	public void setRTMPSessionCount(Integer rTMPSessionCount) {
		this.rTMPSessionCount = rTMPSessionCount;
	}

	@XmlElement(name = "HTTPSessionCount")
	public Integer getHTTPSessionCount() {
		return hTTPSessionCount;
	}

	public void setHTTPSessionCount(Integer hTTPSessionCount) {
		this.hTTPSessionCount = hTTPSessionCount;
	}

	@XmlElement(name = "RTPSessionCount")
	public Integer getRTPSessionCount() {
		return rTPSessionCount;
	}

	public void setRTPSessionCount(Integer rTPSessionCount) {
		this.rTPSessionCount = rTPSessionCount;
	}

	@XmlElement(name = "Client")
	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	@XmlElement(name = "HTTPSession")
	public List<HTTPSession> getHTTPSessions() {
		return hTTPSessions;
	}

	public void setHTTPSessions(List<HTTPSession> hTTPSessions) {
		this.hTTPSessions = hTTPSessions;
	}
}
