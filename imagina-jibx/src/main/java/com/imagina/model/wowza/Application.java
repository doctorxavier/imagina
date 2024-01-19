package com.imagina.model.wowza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Application extends AbstractConnectionCounter implements Serializable {

	private static final long	serialVersionUID	= 1457259088194301045L;
	
	private String						name;
	private String						status;
	private List<ApplicationInstance>	applicationInstances	= new ArrayList<ApplicationInstance>(0);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ApplicationInstance> getApplicationInstances() {
		return applicationInstances;
	}

	public void setApplicationInstances(List<ApplicationInstance> applicationInstances) {
		this.applicationInstances = applicationInstances;
	}

}
