package com.lemon.commons.enm;

public enum EnumMessageType implements IEnum{
	//------------------fields -------------------------
	Message_System  		(1, "系统消息"),
	Message_Apply	  		(2, "班级验证消息"),
	Message_Coin            (3, "积分消息")
	;
	//		Message_Apply_Feedback  (3, "系统消息处理结果");


	//------------------instance methods -------------------------
	private int ch;
	private String value;
	private EnumMessageType(int ch, String value) {
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
		for(EnumMessageType e : EnumMessageType.values()) {
			EnumCenter.registe(EnumMessageType.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumMessageType.class, code);
	}

	public static EnumMessageType explain(int code) {
		IEnum ie = EnumCenter.explain(EnumMessageType.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumMessageType)ie;
		}
	}
}