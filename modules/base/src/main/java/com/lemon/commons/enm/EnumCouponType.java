package com.lemon.commons.enm;

/**
 * Created by ywp on 15/9/10.
 */
public enum EnumCouponType implements IEnum {
	Coupon(9,"优惠券"),
	DiscountCode(8,"优惠码"),
	ThirdCode(7,"第三方优惠码");
	;
	private int code;
	private String value;
	private EnumCouponType(int code, String value) {
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
		for(EnumCouponType e : EnumCouponType.values()) {
			EnumCenter.registe(EnumCouponType.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumCouponType.class, code);
	}
}