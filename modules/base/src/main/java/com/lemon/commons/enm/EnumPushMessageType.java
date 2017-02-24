package com.lemon.commons.enm;

public enum EnumPushMessageType implements IEnum{

	//------------------fields -------------------------
	To_Null				(0,	"默认"),
	To_User  			(1, "用户消息组播"),
	To_ClaStu	  		(2, "班级消息组播"),
	To_School	  		(3, "学校消息组播"),
	;


	//------------------instance methods -------------------------
	private int ch;
	private String value;
	private EnumPushMessageType(int ch, String value) {
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
		for(EnumPushMessageType e : EnumPushMessageType.values()) {
			EnumCenter.registe(EnumPushMessageType.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumPushMessageType.class, code);
	}

	public static EnumPushMessageType explain(int code) {
		IEnum ie = EnumCenter.explain(EnumPushMessageType.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumPushMessageType)ie;
		}
	}

}
