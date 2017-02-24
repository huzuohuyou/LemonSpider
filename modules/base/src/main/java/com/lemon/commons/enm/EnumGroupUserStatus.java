package com.lemon.commons.enm;

public enum EnumGroupUserStatus implements IEnum {
	// ------------------fields -------------------------
	Status_Apply(1, "已申请"), Status_Joined(2, "已加入"), Status_Refuse(3, "已拒绝"), Status_Deleted(4, "已删除"), Status_NotDeal(5, "未处理且班级被删除"),Status_DeleteGroup(6,"班级删除");

	// ------------------instance methods -------------------------
	private int code;
	private String value;

	private EnumGroupUserStatus(int code, String value) {
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
		for (EnumGroupUserStatus e : EnumGroupUserStatus.values()) {
			EnumCenter.registe(EnumGroupUserStatus.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumGroupUserStatus.class, code);
	}
}