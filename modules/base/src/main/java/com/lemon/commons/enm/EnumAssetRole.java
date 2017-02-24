package com.lemon.commons.enm;

public enum EnumAssetRole implements IEnum{
	View 			(1, "预览"),
	Refer			(2, "查阅"),
	Download  		(3, "下载");

	private int ch;
	private String value;
	private EnumAssetRole(int ch, String value) {
		this.ch = ch;
		this.value = value;
	}


	@Override
	public String getValue() {
		return value;
	}

	@Override
	public int getCode() {
		return ch;
	}


	//------------------static methods -------------------------
	static {
		for(EnumAssetRole e : EnumAssetRole.values()) {
			EnumCenter.registe(EnumAssetRole.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumAssetRole.class, code);
	}

	public static EnumAssetRole explain(int code) {
		IEnum ie = EnumCenter.explain(EnumAssetRole.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumAssetRole)ie;
		}
	}

}
