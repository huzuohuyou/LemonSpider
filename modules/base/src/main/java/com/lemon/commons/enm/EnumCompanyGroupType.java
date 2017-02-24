package com.lemon.commons.enm;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum EnumCompanyGroupType implements IEnum {
	// ------------------fields -------------------------
	Base            (0, "基础/默认分组", "base"),
    Company_Group   (4, "企业分组", "companyGroup"),
    Connect_Group   (5, "订阅/相关", "connectGroup"),
	Connect_Clazz   (6, "班级", "clazzGroup"),
	Studio          (7, "工作室","studio")
    ;

	// ------------------instance methods -------------------------
	private int code;
	private String value;
	private String type;

	private EnumCompanyGroupType(int code, String value, String type) {
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
		for (EnumCompanyGroupType e : EnumCompanyGroupType.values()) {
			EnumCenter.registe(EnumCompanyGroupType.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumCompanyGroupType.class, code);
	}

}