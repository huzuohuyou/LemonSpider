package com.lemon.commons.enm;

public enum EnumBlogType implements IEnum{
    Normal              (0,"暂无"),
    Version   			 (1,"版本信息"),
    Blog                (2,"博客信息");

	private int ch;
	private String value;


	private EnumBlogType(int ch, String value) {
		this.ch = ch;
		this.value = value;

	}

	@Override
	public int getCode() {
		return ch;
	}

	@Override
	public String getValue() {
		return value;
	}


	//------------------static methods -------------------------
	static {
		for(EnumBlogType e : EnumBlogType.values()) {
			EnumCenter.registe(EnumBlogType.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumBlogType.class, code);
	}

	public static EnumBlogType explain(int code) {
		IEnum ie = EnumCenter.explain(EnumBlogType.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumBlogType)ie;
		}
	}



}
