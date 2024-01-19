package com.imagina.model.wowza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VHost extends AbstractConnectionCounter implements Serializable {

	private static final long	serialVersionUID	= 7873696297738946598L;
	
	private String				name;
	private int					connectionsLimit;
	private List<Application>	applications	= new ArrayList<Application>(0);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getConnectionsLimit() {
		return connectionsLimit;
	}

	public void setConnectionsLimit(int connectionsLimit) {
		this.connectionsLimit = connectionsLimit;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

}
