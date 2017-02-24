package com.lemon.commons.enm;

/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月7日 下午9:18:18
 *
 */
public enum EnumSearchOperator implements IEnum {
	//------------------fields -------------------------
	EQ					(0, "EQ"),
	LIKE				(1, "LIKE"),
	GT					(2, "GT"),
	LT					(3, "LT"),
	GTE					(4, "GTE"),
	LTE					(5, "LTE"),
	EQ4Char				(6, "EQ4Char"),
	EQ4Int				(7, "EQ4Int"),
	NE					(8, "NE"),
	NE4Int  			(9, "NE4Int"),
	ChildOf 			(10, "ChildOf"),
	Between4Long		(11, "Between4Long"),
	IN					(12, "IN"),
	Between4Integer		(13, "Between4Integer"),
	Between4Date		(14, "Between4Date"),
	NotIN				(15, "NotIN"),
	IsNull              (16, "IsNull"),
	NotNull             (17, "NotNull"),
	EXP                 (18, "EXP");



	//------------------instance methods -------------------------
	private int code;
	private String value;
	private EnumSearchOperator(int code, String value) {
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
		for(EnumSearchOperator e : EnumSearchOperator.values()) {
			EnumCenter.registe(EnumSearchOperator.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumSearchOperator.class, code);
	}
}
