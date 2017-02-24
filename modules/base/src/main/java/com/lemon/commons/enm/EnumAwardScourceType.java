package com.lemon.commons.enm;

public enum EnumAwardScourceType implements IEnum  {
	Activity (1,"com.lemon.ds.a.service.ActivityService"),
	Video(2,"com.lemon.ds.a.service.ActivityProductService")
	;
	private int code;
	private String value;
	private EnumAwardScourceType(int code,String value){
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
		for(EnumAwardScourceType e : EnumAwardScourceType.values()) {
			EnumCenter.registe(EnumAwardScourceType.class, e);
		}
	}

	public static EnumAwardScourceType explain(int code) {
		return (EnumAwardScourceType)EnumCenter.explain(EnumAwardScourceType.class, code);
	}
}