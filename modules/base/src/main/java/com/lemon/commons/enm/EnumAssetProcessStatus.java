package com.lemon.commons.enm;

public enum EnumAssetProcessStatus implements IEnum{

	Normal 			(0, "尚未处理"),
	Managed			(1, "文档已处理或视频audio已转码"),
	MP4  			(2, "视频转成MP4"),
	ERROR			(999,"出错");

	private int ch;
	private String value;
	private EnumAssetProcessStatus(int ch, String value) {
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
		for(EnumAssetProcessStatus e : EnumAssetProcessStatus.values()) {
			EnumCenter.registe(EnumAssetProcessStatus.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumAssetProcessStatus.class, code);
	}
}