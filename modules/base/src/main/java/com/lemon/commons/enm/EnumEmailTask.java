package com.lemon.commons.enm;



/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月7日 下午9:18:18
 *
 */
public enum EnumEmailTask implements IEnum {
	//------------------fields -------------------------
	User_Normal			(0, "默认"),
	User_Teacher		(1, "老师"),
	User_Student		(2, "学生"),
	User_Administrator	(99, "管理员"),
	;


	//------------------instance methods -------------------------
	private int code;
	private String value;
	private EnumEmailTask(int code, String value) {
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
		for(EnumEmailTask e : EnumEmailTask.values()) {
			EnumCenter.registe(EnumEmailTask.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumEmailTask.class, code);
	}
}