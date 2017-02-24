package com.lemon.commons;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public final class JsonRest extends JsonBase<Map<String, ?>, JsonRest> {

	public final static JsonRest jsonOK = new JsonRest("");
	
	private String url;
	
	public JsonRest() {
		super();
	}
	
	public JsonRest(String actualMsg) {
		super(actualMsg);
	}
	
	public JsonRest(Err err) {
		super(err);
	}

	
	@JsonIgnore
	@Override
	public boolean isOk() {
		return errNum==0;
	}

	@JsonIgnore
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
}
