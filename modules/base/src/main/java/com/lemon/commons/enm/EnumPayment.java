package com.lemon.commons.enm;


/**
 * Created by ywp on 15/10/27.
 */
public enum  EnumPayment implements IEnumProp {
	APP_WeiXin("wx","微信",""),
	APP_AliPay("alipay","支付宝",""),
	Web_WebXin_QR("wx_pub_qr","微信扫一扫",""),
	WeXin_Pub("wx_pub","微信公众号支付","");

	private String payment;
	private String payName;
	private String payIco;

	private EnumPayment(String payment,String payName,String payIco){
		this.payment = payment;
		this.payName = payName;
		this.payIco = payIco;
	}

	@Override
	public String getName() {
		return payment;
	}

	@Override
	public int getValueAsInt() {
		return 0;
	}

	@Override
	public long getValueAsLong() {
		return 0;
	}

	@Override
	public int getCode() {
		return payment.hashCode();
	}

	@Override
	public String getValue() {
		return payName;
	}

	public String getPayIco(){
		return payIco;
	}

	//------------------static methods -------------------------
	static {
		for(EnumPayment e : EnumPayment.values()) {
			EnumCenter.registe(EnumPayment.class, e);
		}
	}

	public static EnumPayment explain(String payment) {
		if(payment == null)
			return  null;
		IEnum ie = EnumCenter.explain(EnumPayment.class,payment.hashCode());
		if(ie == null) {
			return null;
		} else {
			return (EnumPayment)ie;
		}
	}
}