package com.lemon.commons.enm;



/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月7日 下午9:18:18
 *
 */
public enum EnumWatchDetailStatus implements IEnumChinese {
	//------------------fields -------------------------
	Type_Playing  	(1, "p", "播放"),
	Type_Draging  	(2, "d", "拖动"),
	Type_Solving	(3, "s", "答题"),
	Type_Review		(4, "r", "回顾/复习");

	//------------------instance methods -------------------------
	private int code;
	private String value;
	private String chineseName;
	private EnumWatchDetailStatus(int code, String value, String chineseName) {
		this.code = code;
		this.value = value;
		this.chineseName = chineseName;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getChineseName() {
		return chineseName;
	}

	//------------------static methods -------------------------
	static {
		for(EnumWatchDetailStatus e : EnumWatchDetailStatus.values()) {
			EnumCenter.registe(EnumWatchDetailStatus.class, e);
			EnumCenter.registeValues(EnumWatchDetailStatus.class, e);
		}
	}

	public static EnumWatchDetailStatus explain(int code) {
		IEnum ie = EnumCenter.explain(EnumWatchDetailStatus.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumWatchDetailStatus)ie;
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumWatchDetailStatus.class, code);
	}

	/**
	 * 请注意对应的在上面static里面注册registeValues
	 * @param val
	 * @return
	 */
	public static EnumWatchDetailStatus explainValue(String val) {
		IEnum ie = EnumCenter.explainValue(EnumWatchDetailStatus.class, val);
		if(ie == null) {
			return null;
		} else {
			return (EnumWatchDetailStatus)ie;
		}
	}
}