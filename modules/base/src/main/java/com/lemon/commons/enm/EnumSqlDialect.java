package com.lemon.commons.enm;



/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月7日 下午9:18:18
 *
 */
public enum EnumSqlDialect implements IEnum{
	//------------------fields -------------------------
	Bit_And  		(1, "_and_"),
	Bit_Or  		(2, "_or_"),
	Bit_Xor			(3, "_xor_"),

	Mod				(5, "_mod_"),

	Limit			(11, "_limit_"),
	RegReplace      (12,"reg_replace");

	//------------------instance methods -------------------------
	private int ch;
	private String value;
	private EnumSqlDialect(int ch, String value) {
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
		for(EnumSqlDialect e : EnumSqlDialect.values()) {
			EnumCenter.registe(EnumSqlDialect.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumSqlDialect.class, code);
	};
}
