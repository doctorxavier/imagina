package com.imagina.util.json;

import net.minidev.json.JSONObject;

public final class JSON {

	private JSON() {

	}

	public static String toJSONP(String callback, String name, Object data) {
		JSONObject jSONObject = new JSONObject();
		jSONObject.put(name, data);
		String response = "";
		if (callback != null) {
			response = callback + "(" + jSONObject.toJSONString() + ")";
		} else {
			response = jSONObject.toJSONString();
		}
		return response;
	}

}
