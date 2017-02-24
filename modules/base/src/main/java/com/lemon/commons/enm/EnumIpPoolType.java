package com.lemon.commons.enm;

public enum EnumIpPoolType implements IEnum{
	//------------------fields -------------------------
    Normal 			        (0, "默认"),
	Upload_Media  			(1, "上传视频");

	//------------------instance methods -------------------------
	private int ch;
	private String value;
	private EnumIpPoolType(int ch, String value) {
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
		for(EnumIpPoolType e : EnumIpPoolType.values()) {
			EnumCenter.registe(EnumIpPoolType.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumIpPoolType.class, code);
	}

	public static EnumIpPoolType explain(int code) {
		IEnum ie = EnumCenter.explain(EnumIpPoolType.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumIpPoolType)ie;
		}
	}

}
