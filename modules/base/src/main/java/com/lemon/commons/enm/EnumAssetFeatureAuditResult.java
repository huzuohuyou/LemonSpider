package com.lemon.commons.enm;


/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月7日 下午9:18:18
 *
 */
public enum EnumAssetFeatureAuditResult implements IEnum{
	//------------------fields -------------------------
	Unsubmit		(0, "未提交"),
	Pending  		(1, "提交待处理"),
	FeatureMedia  	(2, "已是精品"),
	NonFeatureMedia	(3, "拒绝加精"),
	HiddenMedia		(4, "测试视频");

	//------------------instance methods -------------------------
	private int ch;
	private String value;
	private EnumAssetFeatureAuditResult(int ch, String value) {
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
		for(EnumAssetFeatureAuditResult e : EnumAssetFeatureAuditResult.values()) {
			EnumCenter.registe(EnumAssetFeatureAuditResult.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumAssetFeatureAuditResult.class, code);
	}
}