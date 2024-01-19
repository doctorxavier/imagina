package com.imagina.model;

import java.io.Serializable;

import net.minidev.json.JSONObject;

public class StreamInfo implements Serializable {

	private static final long	serialVersionUID	= -4548304166492686078L;
	
	private String				name;
	private String				url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		JSONObject jSONObject = new JSONObject();
		jSONObject.put("name", this.name);
		jSONObject.put("url", this.url);
		return jSONObject.toJSONString();
	}

}
