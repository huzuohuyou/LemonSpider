package com.lemon.commons.enm;


/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月7日 下午9:18:18
 *
 */
public enum EnumAssetShareGroup implements IEnum{
	//------------------fields -------------------------
	Global  			(0, "全站共享"),
	School  		(1, "校内共享"),
	TeacherGroup	(2, "教师教研组内共享");

	//------------------instance methods -------------------------
	private int ch;
	private String value;
	private EnumAssetShareGroup(int ch, String value) {
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
		for(EnumAssetShareGroup e : EnumAssetShareGroup.values()) {
			EnumCenter.registe(EnumAssetShareGroup.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumAssetShareGroup.class, code);
	}

	public static EnumAssetShareGroup explain(int code) {
		IEnum ie = EnumCenter.explain(EnumAssetShareGroup.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumAssetShareGroup)ie;
		}
	}
}