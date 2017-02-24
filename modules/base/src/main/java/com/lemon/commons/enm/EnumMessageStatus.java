package com.lemon.commons.enm;


public enum EnumMessageStatus implements IEnum{
	//------------------fields -------------------------
	Status_NotRead		(0,	"未读"),
	Status_Read 		(1, "已读");


	//------------------instance methods -------------------------
	private int ch;
	private String value;
	private EnumMessageStatus(int ch, String value) {
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
		for(EnumMessageStatus e : EnumMessageStatus.values()) {
			EnumCenter.registe(EnumMessageStatus.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumMessageStatus.class, code);
	}

	public static EnumMessageStatus explain(int code) {
		IEnum ie = EnumCenter.explain(EnumMessageStatus.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumMessageStatus)ie;
		}
	}
}