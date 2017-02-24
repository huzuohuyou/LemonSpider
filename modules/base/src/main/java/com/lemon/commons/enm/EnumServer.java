package com.lemon.commons.enm;

import com.lemon.commons.Debug;

/*
 * @version v3 2015年5月7日 下午9:18:18
 *
 */
public enum EnumServer implements IEnum {
	// ------------------fields -------------------------

	URL_WEB(0, Debug.Web_Url.get("URL_WEB")),
	URL_MEDIA(1, Debug.Web_Url.get("URL_MEDIA")),
	URL_OFFICE(4, Debug.Web_Url.get("URL_OFFICE")),
	URL_Static_Url(5,Debug.Web_Url.get("URL_STATIC_URL"));

	// ------------------instance methods -------------------------
	private int code;
	private String value;
	private EnumServer(int code, String value) {
		this.code = code;
		this.value = value;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getValue() {
		return value;
	}

	// ------------------static methods -------------------------
	static {
		for (EnumServer e : EnumServer.values()) {
			EnumCenter.registe(EnumServer.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumServer.class, code);
	}
}