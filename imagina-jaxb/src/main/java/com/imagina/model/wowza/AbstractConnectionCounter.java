package com.imagina.model.wowza;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public abstract class AbstractConnectionCounter {

	private Long	connectionsCurrent;
	private Long	connectionsTotal;
	private Long	connectionsTotalAccepted;
	private Long	connectionsTotalRejected;
	private Double	timeRunning;

	@XmlElement(name = "ConnectionsCurrent")
	public Long getConnectionsCurrent() {
		return connectionsCurrent;
	}

	@XmlElement(name = "ConnectionsTotal")
	public Long getConnectionsTotal() {
		return connectionsTotal;
	}

	@XmlElement(name = "ConnectionsTotalAccepted")
	public Long getConnectionsTotalAccepted() {
		return connectionsTotalAccepted;
	}

	@XmlElement(name = "ConnectionsTotalRejected")
	public Long getConnectionsTotalRejected() {
		return connectionsTotalRejected;
	}

	@XmlElement(name = "TimeRunning")
	public Double getTimeRunning() {
		return timeRunning;
	}

	public void setConnectionsCurrent(Long connectionsCurrent) {
		this.connectionsCurrent = connectionsCurrent;
	}

	public void setConnectionsTotal(Long connectionsTotal) {
		this.connectionsTotal = connectionsTotal;
	}

	public void setConnectionsTotalAccepted(Long connectionsTotalAccepted) {
		this.connectionsTotalAccepted = connectionsTotalAccepted;
	}

	public void setConnectionsTotalRejected(Long connectionsTotalRejected) {
		this.connectionsTotalRejected = connectionsTotalRejected;
	}

	public void setTimeRunning(Double timeRunning) {
		this.timeRunning = timeRunning;
	}

}
