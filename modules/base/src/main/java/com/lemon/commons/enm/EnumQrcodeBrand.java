package com.lemon.commons.enm;

/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月7日 下午9:18:18
 */
public enum EnumQrcodeBrand implements IEnum {
	//------------------fields -------------------------
	BRAND_MSZX  		(0, "名师在线"),
	BRAND_YYZB  		(1, "英语周报"),
	BRAND_YNWH			(2, "益鸟文化"),
	;

	//------------------instance methods -------------------------
	private int code;
	private String value;
	private EnumQrcodeBrand(int code, String value) {
		this.code = code;
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}

	@Override
	public int getCode() {
		return code;
	}


	//------------------static methods -------------------------
	static {
		for(EnumQrcodeBrand e : EnumQrcodeBrand.values()) {
			EnumCenter.registe(EnumQrcodeBrand.class, e);
			EnumCenter.registeValues(EnumQrcodeBrand.class, e);
		}
	}

	public static EnumQrcodeBrand explain(int code) {
		IEnum ie = EnumCenter.explain(EnumQrcodeBrand.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumQrcodeBrand)ie;
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumQrcodeBrand.class, code);
	}

	/**
	 * 请注意对应的在上面static里面注册registeValues
	 * @param val
	 * @return
	 */
	public static EnumQrcodeBrand explainValue(String val) {
		IEnum ie = EnumCenter.explainValue(EnumQrcodeBrand.class, val);
		if(ie == null) {
			return null;
		} else {
			return (EnumQrcodeBrand)ie;
		}
	}
	
	
	//------------------extended static methods -------------------------
	public final static EnumQrcodeBrand[] BRANDS = {BRAND_MSZX, BRAND_YYZB, BRAND_YNWH};
	

}