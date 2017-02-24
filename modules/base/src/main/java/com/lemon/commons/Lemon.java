package com.lemon.commons;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public abstract class Lemon implements Serializable {

	@Override
	public String toString() {
		return "Lemon: " + ToStringBuilder.reflectionToString(this);
	}

	public String toJsonString() {
		return JsonMapper.sharedInstance().object2json(this);
	}
}