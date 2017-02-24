package com.lemon.commons.enm;

public enum EnumAssetSourceType implements IEnum {
	Normal (0,"普通"),
	Activity (1,"活动"),
	YangCong(2,"洋葱"),
	;

	private int code;
	private String value;
	private EnumAssetSourceType(int code,String value){
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
}