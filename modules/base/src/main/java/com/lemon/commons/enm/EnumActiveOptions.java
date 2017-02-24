package com.lemon.commons.enm;

public enum EnumActiveOptions implements IEnum{
	Login   			(1,"login","登录",1),
	Upload_Asset   		(2,"uploadAsset","上传资源",1),
	Create_Node   		(3,"createNode","创建节点",1),
	Watch_Media   		(4,"createNode","创建节点",1),
	Not_Login   		(5,"notLogin","未登录",-1),
	Not_Upload_Asset   	(6,"notUploadAsset","未上传资源",-1),
	Not_Create_Node   	(7,"notCreateNode","未创建节点",-1),
	Not_Watch_Media   	(8,"createNode","未创建节点",-1);

	private int ch;
	private String value;
	private String name;
	private int sorce;//用于活跃积分计算

	private EnumActiveOptions(int ch, String value,String name,int sorce) {
		this.ch = ch;
		this.value = value;
		this.name=name;
		this.sorce = sorce;
	}

	@Override
	public int getCode() {
		return ch;
	}

	@Override
	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public int getSorce() {
		return sorce;
	}


	//------------------static methods -------------------------
	static {
		for(EnumActiveOptions e : EnumActiveOptions.values()) {
			EnumCenter.registe(EnumActiveOptions.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumActiveOptions.class, code);
	}

	public static EnumActiveOptions explain(int code) {
		IEnum ie = EnumCenter.explain(EnumActiveOptions.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumActiveOptions)ie;
		}
	}


}
