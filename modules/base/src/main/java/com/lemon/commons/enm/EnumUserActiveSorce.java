package com.lemon.commons.enm;

public enum EnumUserActiveSorce implements IEnum{
	;

	@Override
	public int getCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	//------------------static methods -------------------------
	static {
		for(EnumUserActiveSorce e : EnumUserActiveSorce.values()) {
			EnumCenter.registe(EnumUserActiveSorce.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumUserActiveSorce.class, code);
	}

	public static EnumUserActiveSorce explain(int code) {
		IEnum ie = EnumCenter.explain(EnumUserActiveSorce.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumUserActiveSorce)ie;
		}
	}

}
