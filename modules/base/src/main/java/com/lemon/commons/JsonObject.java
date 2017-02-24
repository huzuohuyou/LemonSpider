package com.lemon.commons;

import org.apache.commons.httpclient.HttpStatus;

public final class JsonObject extends JsonBase<Object, JsonObject> {

	@Override
	public boolean isOk() {
		return errNum == HttpStatus.SC_OK;
	}

	public JsonObject setErrNum(int httpErrCode) {
		this.errNum = httpErrCode;
		return this;
	}
	
	public JsonObject setErrMsg(String httpErrMsg) {
		this.errMsg = httpErrMsg;
		return this;
	}
}
