package com.lemon.commons.enm;

/**
 * Created by ywp on 15/9/8.
 */
public enum  EnumUserCoinRuleAction implements IEnumProp {
	NewApplyActivity("NewApplyActivity","新用户申请活动成功"),
	UserPrefect("UserPrefect","用户完善信息"),
	BuildRealClazz("BuildRealClazz","建立实名班"),
	StudentJoin("StudentJoin","学生加入班级"),
	OldStudentJoin("OldStudentJoin","学生加入班级"),
	LogonMyHome("LogonMyHome","登陆事件"),
	Login      ("Login","用户登录"),
    SginByDay ("SginByDay","每日签到"),
	;

	private String ch;
	private String value;
	private EnumUserCoinRuleAction(String ch, String value) {
		this.ch = ch;
		this.value = value;
	}


	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String getName() {
		return ch;
	}

	@Override
	public int getValueAsInt() {
		return 0;
	}

	@Override
	public long getValueAsLong() {
		return 0;
	}

	@Override
	public int getCode() {
		return ch.hashCode();
	}

	//------------------static methods -------------------------
	static {
		for(EnumUserCoinRuleAction e : EnumUserCoinRuleAction.values()) {
			EnumCenter.registe(EnumUserCoinRuleAction.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumUserCoinRuleAction.class, code);
	}
}