package com.lemon.commons.enm;

public enum EnumEmail implements IEnum {
	// ------------------fields -------------------------
	Type_Identity(1, "验证邮箱"), Type_GetPwd(2, "找回密码"), Status_valid(3, "未使用"), Status_used(4, "已使用");

	// ------------------instance methods -------------------------
	private int code;
	private String value;

	private EnumEmail(int code, String value) {
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
		for (EnumEmail e : EnumEmail.values()) {
			EnumCenter.registe(EnumEmail.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumEmail.class, code);
	}
}