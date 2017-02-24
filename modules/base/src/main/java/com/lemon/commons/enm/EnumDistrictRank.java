package com.lemon.commons.enm;

public enum EnumDistrictRank implements IEnum{

	Province	(1, "省"),
	City		(2, "地市"),
	County		(3, "区县");

	private int ch;
	private String value;
	private EnumDistrictRank(int ch, String value) {
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
		for(EnumDistrictRank e : EnumDistrictRank.values()) {
			EnumCenter.registe(EnumDistrictRank.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumDistrictRank.class, code);
	}
}