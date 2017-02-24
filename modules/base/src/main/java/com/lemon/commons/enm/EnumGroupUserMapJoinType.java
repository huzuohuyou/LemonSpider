package com.lemon.commons.enm;

public enum EnumGroupUserMapJoinType implements IEnum {
	// ------------------fields -------------------------
	Directly(1, "直接加入"),
	Pass_verification(2, "通过验证");

	// ------------------instance methods -------------------------
	private int code;
	private String value;

	private EnumGroupUserMapJoinType(int code, String value) {
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
		for (EnumGroupUserMapJoinType e : EnumGroupUserMapJoinType.values()) {
			EnumCenter.registe(EnumGroupUserMapJoinType.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumGroupUserMapJoinType.class, code);
	}
}