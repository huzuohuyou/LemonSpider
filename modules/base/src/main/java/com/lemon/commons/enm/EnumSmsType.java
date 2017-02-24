package com.lemon.commons.enm;

public enum EnumSmsType implements IEnum {
	// ------------------fields -------------------------
	Sms_Register(1, "register"), Sms_Pwd(2, "password"), Sms_RegisterOrLogin(3, "registerorlogin"),Sms_ChangePhone(4,"changephone"),Sms_AppFindPwd(5,"findPwd");

	// ------------------instance methods -------------------------
	private int code;
	private String value;

	private EnumSmsType(int code, String value) {
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
		for (EnumSmsType e : EnumSmsType.values()) {
			EnumCenter.registe(EnumSmsType.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumSmsType.class, code);
	}
}