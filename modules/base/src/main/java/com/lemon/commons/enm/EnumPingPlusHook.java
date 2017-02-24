package com.lemon.commons.enm;

/**
 * Created by ywp on 15/9/23.
 */
public enum  EnumPingPlusHook implements IEnumProp {
	ChargeSuccessed("charge.succeeded","com.lemon.ds.t.service.pingplushooks.ChargeSuccessHookService"),
	RefundSuccessed("refund.succeeded","com.lemon.ds.t.service.pingplushooks.RefundSuccessHookService"),
	SummaryDaily("summary.daily.available",""),
	SummaryWeekly("summary.weekly.available",""),
	SummaryMonthly("summary.monthly.available","");

	private EnumPingPlusHook(String name,String classname){
		//this.code = code;
		this.name = name;
		this.classname = classname;
	}

	private String name;
	private String classname;


	@Override
	public String getName() {
		return name;
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
		return name.hashCode();
	}

	@Override
	public String getValue() {
		return classname;
	}

	//------------------static methods -------------------------
	static {
		for(EnumPingPlusHook e : EnumPingPlusHook.values()) {
			EnumCenter.registe(EnumPingPlusHook.class, e);
		}
	}

	public static EnumPingPlusHook explain(String name) {
		IEnum ie = EnumCenter.explain(EnumPingPlusHook.class,name.hashCode());
		if(ie == null) {
			return null;
		} else {
			return (EnumPingPlusHook)ie;
		}
	}
}