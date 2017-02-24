package com.lemon.commons.enm;

public enum EnumClassType implements IEnum {
	// ------------------fields -------------------------
	Class_Base              (1, "基础班", "base"),
    Class_Real              (2, "实名班", "real"),
    Class_Network           (3, "网络班", "net"),
    ;

	// ------------------instance methods -------------------------
	private int code;
	private String value;
	private String type;

	private EnumClassType(int code, String value, String type) {
		this.code = code;
		this.value = value;
		this.type = type;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getValue() {
		return value;
	}

	public String getType() {
		return type;
	}

	// ------------------static methods -------------------------
	static {
		for (EnumClassType e : EnumClassType.values()) {
			EnumCenter.registe(EnumClassType.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumClassType.class, code);
	}
}