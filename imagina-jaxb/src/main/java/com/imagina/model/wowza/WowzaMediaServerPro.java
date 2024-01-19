package com.imagina.model.wowza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "WowzaMediaServerPro")
public class WowzaMediaServerPro implements Serializable {

	private static final long	serialVersionUID	= -7867310312148809355L;

	private String				name;
	private List<VHost>			vHosts				= new ArrayList<VHost>(0);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name = "VHost")
	public List<VHost> getVHosts() {
		return vHosts;
	}

	public void setVHosts(List<VHost> vHosts) {
		this.vHosts = vHosts;
	}

}
