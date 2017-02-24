package com.lemon.commons.enm;

/**
 * Created by yp on 2015/8/18.
 */
public enum EnumFeedbackType implements IEnum{
	Report(1,"举报视频"),
	Feedback(2,"反馈"),
	WenkuFeedback(3,"文库反馈"),
	AppFeedback(4,"APP问题反馈")
	;
	private int code;
	private String value;
	private EnumFeedbackType(int code, String value) {
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

	//------------------static methods -------------------------
	static {
		for(EnumFeedbackType e : EnumFeedbackType.values()) {
			EnumCenter.registe(EnumFeedbackType.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumFeedbackType.class, code);
	}
}