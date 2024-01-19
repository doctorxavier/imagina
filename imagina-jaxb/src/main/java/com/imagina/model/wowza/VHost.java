package com.imagina.model.wowza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"name", "connectionsLimit", "timeRunning", "connectionsCurrent", "connectionsTotal", "connectionsTotalAccepted",
		"connectionsTotalRejected", "applications"})
public class VHost extends AbstractConnectionCounter implements Serializable {

	private static final long	serialVersionUID	= 2731100951305263251L;
	
	private String				name;
	private Integer				connectionsLimit;
	private List<Application>	applications	= new ArrayList<Application>(0);

	@XmlElement(name = "Name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "ConnectionsLimit")
	public Integer getConnectionsLimit() {
		return connectionsLimit;
	}

	public void setConnectionsLimit(Integer connectionsLimit) {
		this.connectionsLimit = connectionsLimit;
	}

	@XmlElement(name = "Application")
	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

}

