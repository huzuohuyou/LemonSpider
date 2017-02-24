package com.lemon.commons;

import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpStatus;

import com.lemon.commons.log.Log;

public final class JsonHttp extends JsonBase<String, JsonHttp> {
	private final static Log log = Log.getLogger(JsonHttp.class);

	public JsonHttp() {
		super();
		retData = "";
	}

	@Override
	public boolean isOk() {
		return errNum==HttpStatus.SC_OK;
	}

	public String getRetData4NonUtf8(String charsetName) {
		String s = retData;
		try {
			s = new String(retData.getBytes("ISO-8859-1"), charsetName);
		} catch (UnsupportedEncodingException e) {
			log.error(e);
			s = retData;
		}
		return s;
	}

	public JsonHttp setErrNum(int httpErrCode) {
		this.errNum = httpErrCode;
		return this;
	}

	public JsonHttp setErrMsg(String httpErrMsg) {
		this.errMsg = httpErrMsg;
		return this;
	}
}
