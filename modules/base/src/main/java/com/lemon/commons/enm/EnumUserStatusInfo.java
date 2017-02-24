package com.lemon.commons.enm;

public enum EnumUserStatusInfo implements IEnumMask {

	/**
	 * 目前使用到7位，使用超过10位，
	 * 请注意修改Cons.UserStatus里面的运算数值
	 */

	// ------------------fields -------------------------
	Status_FILLED_PERSON_INFO	(1, 	 "个人信息已完善"),
	Status_TEACHER_IDENTIFY		(1 << 1, "证件已上传"),
	Status_TEACHER_IDENTIFIED	(1 << 2, "证件已认证"),
	Status_EMAIL_SENT			(1 << 3, "邮件已发送"),
	Status_EMAIL_VALID			(1 << 4, "邮箱已确认"),
	Status_DOWNLOAD_APP_IPAD	(1 << 5, "已下载App"),
	Status_PHONE_IDENTIFIED		(1 << 6, "手机已验证"),
	Status_SCHOOL_IDENTIFIED	(1 << 7, "学校用户认证");

	// ------------------instance methods -------------------------
	private int code;
	private String value;

	EnumUserStatusInfo(int code, String value) {
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

	@Override
	public boolean isIncludeBit(int bitMask) {
		return (code & bitMask) == code;
	}

	@Override
	public int addMaskBit(int bitMask) {
		return bitMask |= code;
	}

	@Override
	public int clearMaskBit(int bitMask) {
		int m = ~code; //code取反
		return bitMask & m;
	}

	// ------------------static methods -------------------------
	private static int MASK_ALL = 1;
	static {
		for (EnumUserStatusInfo en : EnumUserStatusInfo.values()) {
			EnumCenter.registe(EnumUserStatusInfo.class, en);
			MASK_ALL |= en.code;
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainMaskAsString(EnumUserStatusInfo.class, code);
	}

	public static boolean isAllBitsDone(Integer status) {
		return MASK_ALL == (status & MASK_ALL);
	}
}
