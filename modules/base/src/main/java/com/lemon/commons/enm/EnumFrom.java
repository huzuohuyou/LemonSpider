package com.lemon.commons.enm;

/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月7日 下午9:18:18
 *
 */
public enum EnumFrom implements IEnumChinese {
	//------------------fields -------------------------
	From_My  		(1, "my", 		"我的资源"),
	From_Global  	(2, "global", 	"精品资源"),
	From_School 	(3, "school",   "校内资源");

	//------------------instance methods -------------------------
	private int ch;
	private String value;
	private String chineseName;
	private EnumFrom(int ch, String value, String chineseName) {
		this.ch = ch;
		this.value = value;
		this.chineseName = chineseName;
	}


	@Override
	public String getValue() {
		return value;
	}

	@Override
	public int getCode() {
		return ch;
	}

	@Override
	public String getChineseName() {
		return chineseName;
	}

	//------------------static methods -------------------------
	static {
		for(EnumFrom e : EnumFrom.values()) {
			EnumCenter.registe(EnumFrom.class, e);
			EnumCenter.registeValues(EnumFrom.class, e);
		}
	}

	public static EnumFrom explain(int code) {
		IEnum ie = EnumCenter.explain(EnumFrom.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumFrom)ie;
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumFrom.class, code);
	}

	/**
	 * 请注意对应的在上面static里面注册registeValues
	 * @param val
	 * @return
	 */
	public static EnumFrom explainValue(String val) {
		IEnum ie = EnumCenter.explainValue(EnumFrom.class, val);
		if(ie == null) {
			return null;
		} else {
			return (EnumFrom)ie;
		}
	}
}
