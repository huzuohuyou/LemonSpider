package com.lemon.commons.enm;

/**
 * Created by ywp on 15/9/24.
 */
public enum EnumProductType implements IEnum {
	Booking       (1,"预售商品",""),
	AnswerService (2,"答疑类服务",""),
	Media         (3,"收费视频","com.lemon.ds.t.service.pingplushooks.ProcessMediaProductService"),
	Clazz         (4,"收费班级","com.lemon.ds.t.service.pingplushooks.ProcessClazzProductService"),
	;
	private EnumProductType(int code,String value,String classname){
		this.code = code;
		this.value = value;
		this.classname = classname;
	}
	private int code;
	private String value;
	private String classname;
	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getValue() {
		return value;
	}

	public String getClassname(){
		return classname;
	}

	//------------------static methods -------------------------
	static {
		for(EnumProductType e : EnumProductType.values()) {
			EnumCenter.registe(EnumProductType.class, e);
		}
	}

	public static EnumProductType explain(int code) {
		IEnum ie = EnumCenter.explain(EnumProductType.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumProductType)ie;
		}
	}
}