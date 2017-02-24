package com.lemon.commons.enm;



/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月7日 下午9:18:18
 *
 */
public enum EnumQuest implements IEnum{
	//------------------fields -------------------------
	M_Chinese  		(1, "初中语文",90199000000000000l),
	M_Math  		(2, "初中数学",90399000000000000l),
	M_English		(3, "初中英语",90299000000000000l),
	M_Physis		(4, "初中物理",90499000000000000l),
	M_Chemical		(5, "初中化学",90599000000000000l),
	M_Biology		(6, "初中生物",90799000000000000l),
	M_Politics		(7, "初中政治",90999000000000000l),
	M_History		(8, "初中历史",90699000000000000l),
	M_Geography		(9, "初中地理",90899000000000000l),

	H_Chinese		(10, "高中语文",310199000000000000l),
	H_Math			(11, "高中数学",310299000000000000l),
	H_English		(12, "高中英语",310399000000000000l),
	H_Physis		(13, "高中物理",310499000000000000l),
	H_Chemical		(14, "高中化学",310599000000000000l),
	H_Biology		(15, "高中生物",310799000000000000l),
	H_Politics		(16, "高中政治",310999000000000000l),
	H_History		(17, "高中历史",310699000000000000l),
	H_Geography		(18, "高中地理",310899000000000000l);

	//------------------instance methods -------------------------
	private int ch;
	private String value;
	private Long wenkuId;
	private EnumQuest(int ch, String value,Long wenkuId) {
		this.ch = ch;
		this.value = value;
		this.wenkuId = wenkuId;
	}


	@Override
	public String getValue() {
		return value;
	}

	@Override
	public int getCode() {
		return ch;
	}

	public Long getWenkuId(){return wenkuId;}

	//------------------static methods -------------------------
	static {
		for(EnumQuest e : EnumQuest.values()) {
			EnumCenter.registe(EnumQuest.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumQuest.class, code);
	}

	public static EnumQuest explain(int code) {
		IEnum ie = EnumCenter.explain(EnumQuest.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumQuest)ie;
		}
	}
}