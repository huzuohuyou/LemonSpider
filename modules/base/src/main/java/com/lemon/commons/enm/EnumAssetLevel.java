package com.lemon.commons.enm;

/**
 * Created by ywp on 15/10/7.
 */
public enum EnumAssetLevel implements IEnum {
	Default(-1,"未申请"),
	Applied(-2,"已申请"),
	BadAsset(5999,"被拒视频"),
	GoodAsset(6000,"精选视频")
	;

	int code;
	String name;
	private EnumAssetLevel(int code,String name){
		this.code = code;
		this.name = name;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getValue() {
		return name;
	}

	//------------------static methods -------------------------
	static {
		for(EnumAssetLevel e : EnumAssetLevel.values()) {
			EnumCenter.registe(EnumAssetLevel.class, e);
		}
	}

	public static EnumAssetLevel explain(int assetLevel){
		EnumAssetLevel enumAssetLevel = (EnumAssetLevel)EnumCenter.explain(EnumAssetLevel.class, assetLevel);

		if(enumAssetLevel != null){
			return enumAssetLevel;
		}

		if(assetLevel > EnumAssetLevel.GoodAsset.getCode()){
			return EnumAssetLevel.GoodAsset;
		}
		return EnumAssetLevel.BadAsset;
	}
}