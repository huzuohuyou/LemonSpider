package com.lemon.commons.enm;

/**
 * Created by ywp on 15/9/21.
 */

public enum EnumUserCoinRuleType implements IEnum {
	Coin        (1,"积分"),
	Coupon      (2,"优惠券"),
	SpaceSize   (3,"资料库空间"),

	ThirdCoupon (9,"第三方优惠")
	;
	//------------------instance methods -------------------------
	private int code;
	private String value;
	private EnumUserCoinRuleType(int code, String value) {
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
		for(EnumUserCoinRuleType e : EnumUserCoinRuleType.values()) {
			EnumCenter.registe(EnumUserCoinRuleType.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumUserCoinRuleType.class, code);
	}
}