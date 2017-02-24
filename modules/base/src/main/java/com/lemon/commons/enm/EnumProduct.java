package com.lemon.commons.enm;

/**
 * Created by ywp on 15/9/7.
 */
public enum EnumProduct implements IEnum {
	FreeRateiPad(1,"全新免息iPad"),
	SecondiPad(2,"二手iPad Air,iPadmini")
	;

	private int ch;
	private String value;
	private EnumProduct(int ch, String value) {
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
		for(EnumProduct e : EnumProduct.values()) {
			EnumCenter.registe(EnumProduct.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumProduct.class, code);
	}
}