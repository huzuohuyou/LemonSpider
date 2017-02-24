package com.lemon.commons.enm;

/**
 * Created by ywp on 15/8/31.
 */
public enum  EnumGiftType implements IEnum {
	PointGift(1,"com.lemon.ds.a.service.UserCoinGiftTypeService"),
	CouponGif(2,"com.lemon.ds.a.service.UserCouponGiftTypeService"),
	OffLineGift(3,"")
	;
	private int code;
	private String value;
	private EnumGiftType(int code, String value) {
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
		for(EnumGiftType e : EnumGiftType.values()) {
			EnumCenter.registe(EnumGiftType.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumGiftType.class, code);
	}
}