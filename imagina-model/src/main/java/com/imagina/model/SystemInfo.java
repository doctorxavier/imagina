package com.imagina.model;

import java.io.Serializable;

import net.minidev.json.JSONObject;

public class SystemInfo implements Serializable {

	private static final long	serialVersionUID	= -7256011859844277086L;
	private String				name;
	private int					count;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		JSONObject jSONObject = new JSONObject();
		jSONObject.put("name", this.name);
		jSONObject.put("count", this.count);
		return jSONObject.toJSONString();
	}

}
