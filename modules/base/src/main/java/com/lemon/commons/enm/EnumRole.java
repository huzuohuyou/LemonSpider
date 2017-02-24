package com.lemon.commons.enm;



/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月7日 下午9:18:18
 *
 */
public enum EnumRole implements IEnum{
	//------------------fields -------------------------
	Role_Teacher  		(1, "t"),
	Role_Student  		(2, "s"),
	Role_Admin			(3, "a"),
	Role_Orginization	(4, "v"),
	Role_Fee			(5, "fee");

	//------------------instance methods -------------------------
	private int ch;
	private String value;
	private EnumRole(int ch, String value) {
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
		for(EnumRole e : EnumRole.values()) {
			EnumCenter.registe(EnumRole.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumRole.class, code);
	}

	public static EnumRole explain(int code) {
		IEnum ie = EnumCenter.explain(EnumRole.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumRole)ie;
		}
	}
}
