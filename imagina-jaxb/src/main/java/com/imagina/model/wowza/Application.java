package com.imagina.model.wowza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.imagina.util.xml.jaxb.StringAdapter;

@XmlType(propOrder = {"name", "status", "timeRunning", "connectionsCurrent", "connectionsTotal", "connectionsTotalAccepted", "connectionsTotalRejected",
		"applicationInstances"})
public class Application extends AbstractConnectionCounter implements Serializable {

	private static final long	serialVersionUID	= 8225736642807593209L;
	
	private String						name;
	private String						status;
	private List<ApplicationInstance>	applicationInstances	= new ArrayList<ApplicationInstance>(0);

	@XmlElement(name = "Name")
	@XmlJavaTypeAdapter(value = StringAdapter.class)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "Status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@XmlElement(name = "ApplicationInstance")
	public List<ApplicationInstance> getApplicationInstances() {
		return applicationInstances;
	}

	public void setApplicationInstances(List<ApplicationInstance> applicationInstances) {
		this.applicationInstances = applicationInstances;
	}

}
