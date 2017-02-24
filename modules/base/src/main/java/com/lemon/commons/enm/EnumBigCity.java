package com.lemon.commons.enm;

public enum EnumBigCity implements IEnum{
    Beijing         (110000, "北京市"),
    Tianjin         (120000, "天津市"),
    Shanghai   		(310000, "上海市"),
    Chongqing       (500000, "重庆市")
    ;

	private int ch;
	private String value;
	private EnumBigCity(int ch, String value) {
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
		for(EnumBigCity e : EnumBigCity.values()) {
			EnumCenter.registe(EnumBigCity.class, e);
            EnumCenter.registeValues(EnumBigCity.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumBigCity.class, code);
	}

	public static EnumBigCity explain(int code) {
		IEnum ie = EnumCenter.explain(EnumBigCity.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumBigCity)ie;
		}
	}

    /**
     * 请注意对应的在上面static里面注册registeValues
     * @param val
     * @return
     */
    public static EnumBigCity explainValue(String val) {
        IEnum ie = EnumCenter.explainValue(EnumBigCity.class, val);
        if(ie == null) {
            return null;
        } else {
            return (EnumBigCity)ie;
        }
    }

}
