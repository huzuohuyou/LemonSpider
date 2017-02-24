package com.lemon.commons.enm;

public enum EnumSchoolTag implements IEnum{
	PrimarySchoolTag   		(1,"小学",10),
	MiddleSchoolTag   		(2,"初中",18),//初高中、职教公用一个tag
	HighSchoolTag   		(3,"高中",18),//初高中、职教公用一个tag
	VocationTag   			(4,"职教",18),//初高中、职教公用一个tag
	UniversityTag   		(5,"大学",66),
	AgencyTag   			(6,"企业",514);

	private int ch;
	private String value;
	private int tag;//数据库学校类别标志

	private EnumSchoolTag(int ch, String value,int tag) {
		this.ch = ch;
		this.value = value;
		this.tag = tag;
	}

	@Override
	public int getCode() {
		return ch;
	}

	@Override
	public String getValue() {
		return value;
	}

	public int getTag() {
		return tag;
	}


	//------------------static methods -------------------------
	static {
		for(EnumSchoolTag e : EnumSchoolTag.values()) {
			EnumCenter.registe(EnumSchoolTag.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumSchoolTag.class, code);
	}

	public static EnumSchoolTag explain(int code) {
		IEnum ie = EnumCenter.explain(EnumSchoolTag.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumSchoolTag)ie;
		}
	}

}
