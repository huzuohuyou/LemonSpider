package com.lemon.commons.enm;

/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月7日 下午9:18:18
 *
 * public static final Integer STATUS_TEACHER_IDENTIFY_UNVALIDATED = 6001;
	public static final Integer STATUS_TEACHER_IDENTIFY_VALIDATING = 6002;
	public static final Integer STATUS_TEACHER_IDENTIFY_VALIDATED = 6003;
	public static Map<Integer, String> TEACHER_INDENTFY_STATUS_EXPLAIN = new HashMap<Integer, String>();
	static{
		TEACHER_INDENTFY_STATUS_EXPLAIN.put(STATUS_TEACHER_IDENTIFY_UNVALIDATED, "未认证");
		TEACHER_INDENTFY_STATUS_EXPLAIN.put(STATUS_TEACHER_IDENTIFY_VALIDATING, "认证中");
		TEACHER_INDENTFY_STATUS_EXPLAIN.put(STATUS_TEACHER_IDENTIFY_VALIDATED, "已认证");
	}
 */
public enum EnumUserStatus implements IEnum {
	//------------------fields -------------------------
	Status_OK 		(0, "OK"),
	Status_Locked 	(1, "用户被锁定，暂时不能登录"),
	Status_Forbiden	(2, "用户被禁止登录"),
	Status_Invalid	(3, "用户或者因为是某种类型用户，如学生组，而不能够登录。"),
	Status_IDENTIFY_UNVALIDATED (6001, "未认证"),
	Status_IDENTIFY_VALIDATING	(6002, "认证中"),
	Status_IDENTIFY_VALIDATED	(6003, "已认证");

	//------------------instance methods -------------------------
	private int code;
	private String value;
	private EnumUserStatus(int code, String value) {
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
		for(EnumUserStatus e : EnumUserStatus.values()) {
			EnumCenter.registe(EnumUserStatus.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumUserStatus.class, code);
	}
}