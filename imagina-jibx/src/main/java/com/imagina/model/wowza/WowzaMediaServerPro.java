package com.imagina.model.wowza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WowzaMediaServerPro implements Serializable {

	private static final long	serialVersionUID	= 4541872669789262215L;

	private String				name;
	private List<VHost>			vHosts				= new ArrayList<VHost>(0);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<VHost> getVHosts() {
		return vHosts;
	}

	public void setVHosts(List<VHost> vHosts) {
		this.vHosts = vHosts;
	}

}
